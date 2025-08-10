package com.example.bookingservice.bookingDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for creating a new booking request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    private Long eventId;
    private Long userId;
    private Integer numberOfTickets;
    private LocalDateTime bookingDate;
}
