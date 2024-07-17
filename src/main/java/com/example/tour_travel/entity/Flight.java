package com.example.tour_travel.entity;

import com.example.tour_travel.enums.TravelClass;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airline;
    private String flightNumber;
    private String departFrom;
    private String arrivalTo;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    @Enumerated(EnumType.STRING)
    private TravelClass travelClass;
    private double price;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;

}



















