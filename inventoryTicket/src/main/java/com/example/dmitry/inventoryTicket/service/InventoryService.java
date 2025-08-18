package com.example.dmitry.inventoryTicket.service;

import com.example.dmitry.inventoryTicket.entity.Event;
import com.example.dmitry.inventoryTicket.entity.Venue;
import com.example.dmitry.inventoryTicket.repository.EventRepository;
import com.example.dmitry.inventoryTicket.repository.VenueRepository;
import com.example.dmitry.inventoryTicket.response.EventInventoryResponse;
import com.example.dmitry.inventoryTicket.response.VenueInventoryResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    
    public  InventoryService(EventRepository eventRepository, VenueRepository venueRepository){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;

    }
    public List<EventInventoryResponse> getAllEvents(){
        final List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventInventoryResponse.builder()
                .eventId(event.getId())
                .eventName(event.getEventName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueById(Long venueId){
        final Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new EntityNotFoundException("Venue with id: " + venueId + " not found"));

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();

    }
    public EventInventoryResponse getEventInventory(Long eventId){
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id: " + eventId + " not found"));

        return EventInventoryResponse.builder()
                .eventName(event.getEventName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .eventId(event.getId())
                .build();
    }
    public void updateEventCapacity(Long eventId, Long ticketsBooked){
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id: " + eventId + " not found"));
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for event id: {} with tickets booked : {}",eventId, ticketsBooked);
    }
}
