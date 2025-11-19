package com.example.event_reminder.repository;

import com.example.event_reminder.model.Event;
import com.example.event_reminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOwner(User owner);
    List<Event> findByOwnerAndCompleted(User owner, boolean completed);
}
