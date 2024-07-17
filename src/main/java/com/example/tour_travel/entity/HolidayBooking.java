package com.example.tour_travel.entity;

import com.example.tour_travel.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class HolidayBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "holiday_id")
    private Holiday holiday;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate bookingDate;
    private BookingStatus status;

    @OneToMany(mappedBy = "holidayBooking")
    private List<HolidayPayment> payments;
}




















