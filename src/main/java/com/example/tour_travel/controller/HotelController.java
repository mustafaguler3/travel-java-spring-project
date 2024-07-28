package com.example.tour_travel.controller;

import com.example.tour_travel.entity.Hotel;
import com.example.tour_travel.repository.HotelRepository;
import com.example.tour_travel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@Controller
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private BookingService bookingService;


    @GetMapping("/hotels")
    public String getHotels(@RequestParam(defaultValue = "0",value = "page",required = false) int pageNumber,
                            @RequestParam(defaultValue = "6",value = "size",required = false) int size,
                            Model model){

        Page<Hotel> hotels = hotelRepository.findAll(PageRequest.of(pageNumber,size));

        if (hotels.isEmpty()){
            model.addAttribute("errorMsg","No hotels found.");
            return "hotel";
        }

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", hotels.getTotalPages());
        model.addAttribute("hotels",hotels.getContent());
        return "hotel";
    }

    @GetMapping("/search")
    public String searchHotels(@RequestParam(required = false) String country,
                               @RequestParam(required = false) String city,
                               @RequestParam(required = false) String checkIn,
                               @RequestParam(required = false) String checkout,
                               @RequestParam int adults,
                               @RequestParam int children,
                               @RequestParam(required = false, defaultValue = "1") int rooms,
                               Model model) {

        LocalDate checkInDate = checkIn != null ? LocalDate.parse(checkIn) : null;
        LocalDate checkoutDate = checkout != null ? LocalDate.parse(checkout) : null;

        List<Hotel> hotels = hotelRepository.searchHotels(country, city, checkInDate, checkoutDate);

        if (checkInDate != null && checkoutDate != null) {
            hotels.removeIf(hotel ->
                    !bookingService.isRoomAvailable(hotel.getId(), checkInDate, checkoutDate, rooms)
            );
        }

        model.addAttribute("hotels", hotels);
        return "hotel-search";
    }
}




















