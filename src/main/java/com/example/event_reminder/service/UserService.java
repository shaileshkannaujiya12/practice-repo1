package com.example.event_reminder.service;

import com.example.event_reminder.model.User;
import com.example.event_reminder.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepo;
    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    public User createUser(String username, String rawPassword, String fullName) {
        User u = User.builder()
                .username(username)
                .password(encoder.encode(rawPassword))
                .fullName(fullName)
                .build();
        return userRepo.save(u);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
    }
}