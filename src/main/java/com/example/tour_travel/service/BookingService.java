package com.example.tour_travel.service;

import java.time.LocalDate;

public interface BookingService {
    boolean isRoomAvailable(Long hotelId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfRooms);
}
