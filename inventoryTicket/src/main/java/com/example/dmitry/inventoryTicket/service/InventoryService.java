package com.example.dmitry.inventoryTicket.service;

import com.example.dmitry.inventoryTicket.entity.Event;
import com.example.dmitry.inventoryTicket.entity.Venue;
import com.example.dmitry.inventoryTicket.repository.EventRepository;
import com.example.dmitry.inventoryTicket.repository.VenueRepository;
import com.example.dmitry.inventoryTicket.response.EventInventoryResponse;
import com.example.dmitry.inventoryTicket.response.VenueInventoryResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
