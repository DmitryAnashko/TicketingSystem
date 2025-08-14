package com.example.dmitry.inventoryTicket.controller;

import com.example.dmitry.inventoryTicket.response.EventInventoryResponse;
import com.example.dmitry.inventoryTicket.response.VenueInventoryResponse;
import com.example.dmitry.inventoryTicket.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/events")
    public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents(){
        return inventoryService.getAllEvents();

    }

    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse inventoryResponse(@PathVariable("venueId") Long venueId) {
        return inventoryService.getVenueById(venueId);
    }

    @GetMapping("/inventory/events/{eventId}")
    public  @ResponseBody EventInventoryResponse inventoryForEvent(@PathVariable("eventId") Long eventId ) {
        return inventoryService.getEventInventory(eventId);

    }

}
