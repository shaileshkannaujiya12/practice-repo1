package com.example.event_reminder.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;


@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String imageUrl;
    private OffsetDateTime startAt;
    private boolean completed;


    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}