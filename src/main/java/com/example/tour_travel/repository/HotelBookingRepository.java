package com.example.tour_travel.repository;

import com.example.tour_travel.entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking,Long> {

    @Query("SELECT b FROM HotelBooking b WHERE b.hotel.id = :hotelId AND " +
            "((b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate) OR " +
            "(b.checkInDate >= :checkInDate AND b.checkOutDate <= :checkOutDate))")
    List<HotelBooking> findBookingsByHotelAndDateRange(@Param("hotelId") Long hotelId,
                                                       @Param("checkInDate") LocalDate checkInDate,
                                                       @Param("checkOutDate") LocalDate checkoutDate);

}
























