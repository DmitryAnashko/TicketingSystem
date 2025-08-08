package com.example.dmitry.inventoryTicket.repository;

import com.example.dmitry.inventoryTicket.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {
}
