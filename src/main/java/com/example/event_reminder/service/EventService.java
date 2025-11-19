package com.example.event_reminder.service;

import com.example.event_reminder.model.Event;
import com.example.event_reminder.model.User;
import com.example.event_reminder.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepo;

    public EventService(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Event createEvent(Event e) {
        return eventRepo.save(e);
    }

    public List<Event> getEventsForUser(User owner) {
        return eventRepo.findByOwner(owner);
    }

    public List<Event> getEventsForUserByStatus(User owner, boolean completed) {
        return eventRepo.findByOwnerAndCompleted(owner, completed);
    }

    public Optional<Event> findById(Long id) {
        return eventRepo.findById(id);
    }
}
