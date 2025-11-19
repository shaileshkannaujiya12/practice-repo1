package com.example.event_reminder.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "push_subs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String endpoint;
    @Lob
    private String keysJson; // store p256dh and auth keys as JSON


    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
