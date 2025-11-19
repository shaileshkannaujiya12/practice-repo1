package com.example.event_reminder.controller;

import com.example.event_reminder.model.PushSubscription;
import com.example.event_reminder.model.User;
import com.example.event_reminder.repository.PushSubscriptionRepository;
import com.example.event_reminder.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/push")
public class PushController {


    private final PushSubscriptionRepository pushRepo;
    private final UserRepository userRepo;


    public PushController(PushSubscriptionRepository pushRepo, UserRepository userRepo) {
        this.pushRepo = pushRepo;
        this.userRepo = userRepo;
    }


    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody PushSubscription body, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            User u = userRepo.findByUsername(userDetails.getUsername()).orElseThrow();
            body.setOwner(u);
        }
        PushSubscription saved = pushRepo.save(body);
        return ResponseEntity.ok(saved);
    }
}
