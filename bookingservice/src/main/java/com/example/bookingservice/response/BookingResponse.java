package com.example.bookingservice.response;

import jakarta.validation.constraints.*;
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
    /*@NotNull(message = "Booking ID is required")
    @Positive(message = "Booking ID must be a positive number")
    private Long bookingId;*/

    @NotNull(message = "Event ID is required")
    @Positive(message = "Event ID must be a positive number")
    private Long eventId;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "Number of tickets is required")
    @Min(value = 1, message = "At least 1 ticket must be booked")
    @Max(value = 10, message = "Cannot book more than 10 tickets at once")
    private Long numberOfTickets;

    @NotNull(message = "Booking date is required")
    @PastOrPresent(message = "Booking date cannot be in the future")
    private LocalDateTime bookingDate;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(CONFIRMED|CANCELLED|PENDING)$", 
             message = "Status must be one of: CONFIRMED, CANCELLED, PENDING")
    private String status;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", inclusive = false, 
               message = "Total price must be greater than 0")
    @Digits(integer = 10, fraction = 2, 
           message = "Total price must have up to 10 integer and 2 fraction digits")
    private BigDecimal totalPrice;

    @NotBlank(message = "Confirmation number is required")
    @Size(min = 6, max = 20, 
         message = "Confirmation number must be between 6 and 20 characters")
    private String confirmationNumber;
}
