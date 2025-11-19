package com.example.event_reminder.dto;
import lombok.Data;
import java.time.OffsetDateTime;


@Data
public class CreateEventRequest {
    private String title;
    private String imageUrl;
    private OffsetDateTime startAt;
}
