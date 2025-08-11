package com.example.bookingservice.service;

import com.example.bookingservice.bookingDto.BookingRequest;
import com.example.bookingservice.bookingDto.BookingResponse;
import com.example.bookingservice.entity.Customer;
import com.example.bookingservice.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;

    @Autowired
    public BookingService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public BookingResponse createBooking(final BookingRequest request){

        final Customer customer = customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with Id" + request.getUserId() + " not found"));

        return BookingResponse.builder().build();
    }
}
