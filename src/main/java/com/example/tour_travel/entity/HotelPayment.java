package com.example.tour_travel.entity;

import com.example.tour_travel.enums.BookingStatus;
import com.example.tour_travel.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class HotelPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_booking_id")
    private HotelBooking hotelBooking;

    private double amount;
    private LocalDate paymentDate;
    private PaymentStatus status;
}




















