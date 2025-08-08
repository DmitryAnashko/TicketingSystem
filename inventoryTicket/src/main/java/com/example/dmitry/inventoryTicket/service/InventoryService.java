package com.example.dmitry.inventoryTicket.service;

import com.example.dmitry.inventoryTicket.repository.EventRepository;
import com.example.dmitry.inventoryTicket.response.EventInventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public  InventoryService(EventRepository eventRepository, VenueRepository venueRepository){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }
    public List<EventInventoryResponse> getAllEvents(){

    }
}
