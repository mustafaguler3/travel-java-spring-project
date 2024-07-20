package com.example.tour_travel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class VerificationToken {

    private Long id;
    private String token;
    @OneToOne(mappedBy = "verificationToken")
    @JoinColumn(name = "user_id")
    private User user;
    private Date expiryDate;

    public VerificationToken(String token, User user, Date expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(24*60);
    }

    public VerificationToken() {
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }
}
























