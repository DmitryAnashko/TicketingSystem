package com.example.dmitry.inventoryTicket.repository;

import com.example.dmitry.inventoryTicket.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
