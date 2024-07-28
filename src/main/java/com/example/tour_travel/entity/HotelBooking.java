package com.example.tour_travel.entity;

import com.example.tour_travel.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class HotelBooking extends Booking{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfRooms;
    private int numberOfAdults;
    private int numberOfChildren;

    private BookingStatus status;

    @OneToMany(mappedBy = "hotelBooking")
    private List<HotelPayment> payments;

}



















