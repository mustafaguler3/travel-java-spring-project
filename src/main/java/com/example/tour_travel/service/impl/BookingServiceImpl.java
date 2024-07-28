package com.example.tour_travel.service.impl;

import com.example.tour_travel.entity.Hotel;
import com.example.tour_travel.entity.HotelBooking;
import com.example.tour_travel.repository.HotelBookingRepository;
import com.example.tour_travel.repository.HotelRepository;
import com.example.tour_travel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final HotelBookingRepository hotelBookingRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public BookingServiceImpl(HotelBookingRepository hotelBookingRepository, HotelRepository hotelRepository) {
        this.hotelBookingRepository = hotelBookingRepository;
        this.hotelRepository = hotelRepository;
    }


    @Override
    public boolean isRoomAvailable(Long hotelId, LocalDate checkin, LocalDate checkout, int numberOfRooms) {
        List<HotelBooking> bookings = hotelBookingRepository.findBookingsByHotelAndDateRange(hotelId,checkin,checkout);

        int totalBookedRooms = bookings.stream().mapToInt(HotelBooking::getNumberOfRooms).sum();

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        return (hotel.getTotalRooms()) - totalBookedRooms >= numberOfRooms;
    }
}





















