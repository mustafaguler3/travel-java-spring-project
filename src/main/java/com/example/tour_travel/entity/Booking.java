package com.example.tour_travel.entity;

import com.example.tour_travel.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private LocalDateTime bookingDate;
    private BookingStatus status; // confirmed,pending,cancelled

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;
}




















