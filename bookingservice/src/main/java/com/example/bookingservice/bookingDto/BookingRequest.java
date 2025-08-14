package com.example.bookingservice.bookingDto;

import jakarta.validation.constraints.*;
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
    @NotNull(message = "Event ID is required")
    @Positive(message = "Event ID must be a positive number")
    private Long eventId;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "Number of tickets is required")
    @Min(value = 0, message = "At least 1 ticket must be booked")
    private Long numberOfTickets;

    /*@NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date must be in the present or future")
    private LocalDateTime bookingDate;*/
}
