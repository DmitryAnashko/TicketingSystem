package com.example.bookingservice.service;

import com.example.bookingservice.bookingDto.BookingRequest;
import com.example.shared.event.BookingEvent;
import com.example.bookingservice.response.BookingResponse;
import com.example.bookingservice.client.InventoryServiceClient;
import com.example.bookingservice.entity.Customer;
import com.example.bookingservice.repository.CustomerRepository;
import com.example.bookingservice.response.InventoryResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> bookingEventKafkaTemplate;

    @Autowired
    public BookingService(CustomerRepository customerRepository,
                          InventoryServiceClient inventoryServiceClient,
                          KafkaTemplate<String, BookingEvent> bookingEventKafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.bookingEventKafkaTemplate = bookingEventKafkaTemplate;
    }
    public BookingResponse createBooking(final BookingRequest request){

        final Customer customer = customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with Id" + request.getUserId() + " not found"));

        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId()); //call inventory service
        log.info("inventoryResponse: {} ", inventoryResponse);

        if(inventoryResponse.getCapacity() < request.getNumberOfTickets())
        {
            throw new RuntimeException("Not enough tickets");
        }
        final BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);

        bookingEventKafkaTemplate.send("bookingEvent", bookingEvent);
        log.info("Booking Event sent to Kafka: {}", bookingEvent);

        //Call inventory service. Create client, will be used to call inventory service
        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .numberOfTickets(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(final BookingRequest request, final Customer customer, final InventoryResponse inventoryResponse) {
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getNumberOfTickets())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getNumberOfTickets()) ))
                .build();
    }
}
