package com.example.tour_travel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String destination;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    @OneToMany(mappedBy = "holiday")
    private List<HolidayBooking> bookings;
}



















