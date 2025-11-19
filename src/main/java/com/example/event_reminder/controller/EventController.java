package com.example.event_reminder.controller;

import com.example.event_reminder.dto.CreateEventRequest;
import com.example.event_reminder.model.Event;
import com.example.event_reminder.model.User;
import com.example.event_reminder.repository.UserRepository;
import com.example.event_reminder.service.EventService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserRepository userRepo;

    public EventController(EventService eventService, UserRepository userRepo) {
        this.eventService = eventService;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<?> createEvent(
            @RequestBody CreateEventRequest req,
            @AuthenticationPrincipal UserDetails userDetails) {

        User owner = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow();

        Event ev = Event.builder()
                .title(req.getTitle())
                .imageUrl(req.getImageUrl())
                .startAt(req.getStartAt())
                .completed(false)
                .owner(owner)
                .build();

        Event saved = eventService.createEvent(ev);

        return ResponseEntity.created(URI.create("/api/events/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public List<Event> listEvents(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String status) {

        User owner = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow();

        if ("completed".equalsIgnoreCase(status))
            return eventService.getEventsForUserByStatus(owner, true);

        if ("active".equalsIgnoreCase(status))
            return eventService.getEventsForUserByStatus(owner, false);

        return eventService.getEventsForUser(owner);
    }
}
