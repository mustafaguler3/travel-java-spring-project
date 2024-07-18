package com.example.tour_travel.entity;

import com.example.tour_travel.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class HolidayPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "holiday_booking_id")
    private HolidayBooking holidayBooking;

    private double amount;
    private LocalDate paymentDate;
    private PaymentStatus status; // paid, pending, failed


}




















