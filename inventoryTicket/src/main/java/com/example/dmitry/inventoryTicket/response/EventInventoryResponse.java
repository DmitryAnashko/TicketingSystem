package com.example.dmitry.inventoryTicket.response;

import com.example.dmitry.inventoryTicket.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private String eventName;
    private Long capacity;
    private Venue venue;
}
