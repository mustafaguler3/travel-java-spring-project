package com.example.tour_travel.service;

import com.example.tour_travel.entity.User;

public interface EmailService {
    void sendVerificationEmail(User user, String token);
}
