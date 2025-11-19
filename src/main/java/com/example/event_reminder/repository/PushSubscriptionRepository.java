package com.example.event_reminder.repository;


import com.example.event_reminder.model.PushSubscription;
import com.example.event_reminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PushSubscriptionRepository extends JpaRepository<PushSubscription, Long> {

    List<PushSubscription> findByOwner(User owner);

}
