package com.example.tour_travel.entity;

import com.example.tour_travel.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private FlightBooking booking;

    private double amount;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;
}




















