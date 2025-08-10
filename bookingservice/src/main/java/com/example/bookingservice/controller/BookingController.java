package com.example.bookingservice.controller;

import com.example.bookingservice.bookingDto.BookingRequest;
import com.example.bookingservice.bookingDto.BookingResponse;
import com.example.bookingservice.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @PostMapping("/booking")
    public BookingResponse createBooking(@Valid @RequestBody BookingRequest request){
        return bookingService.createBooking(request);
    }
}
