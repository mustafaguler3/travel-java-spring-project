package com.example.tour_travel.entity;

import com.example.tour_travel.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class HotelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;

    @OneToMany(mappedBy = "hotelBooking")
    private List<HotelPayment> payments;

}



















