package com.example.tour_travel.entity;

import com.example.tour_travel.enums.FlightType;
import com.example.tour_travel.enums.TravelClass;
import com.example.tour_travel.enums.TravelType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightSearchForm {

    private TravelType travelType;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private LocalDate returnDate;

    private int adults;
    private int children;
    private int infants;
    private FlightType flightType;
    private TravelClass travelClass;
}



















