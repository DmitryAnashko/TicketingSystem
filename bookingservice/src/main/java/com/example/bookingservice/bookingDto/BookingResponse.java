package com.example.bookingservice.bookingDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for booking response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Long bookingId;
    private Long eventId;
    private Long userId;
    private Integer numberOfTickets;
    private LocalDateTime bookingDate;
    private String status; // e.g., CONFIRMED, CANCELLED, PENDING
    private BigDecimal totalPrice;
    private String confirmationNumber;
}
