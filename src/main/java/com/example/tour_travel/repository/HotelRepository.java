package com.example.tour_travel.repository;

import com.example.tour_travel.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    Page<Hotel> findAll(Pageable pageable);

    @Query("SELECT h FROM Hotel h WHERE "
            + "(:country IS NULL OR h.country = :country) AND "
            + "(:location IS NULL OR h.location = :location) AND "
            + "(:checkInDate IS NULL OR :checkOutDate IS NULL OR "
            + "NOT EXISTS (SELECT b FROM Booking b WHERE b.hotel.id = h.id AND "
            + "(b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate)))")
    List<Hotel> searchHotels(@Param("country") String country,
                             @Param("location") String location,
                             @Param("checkInDate") LocalDate checkInDate,
                             @Param("checkOutDate") LocalDate checkOutDate);
}
