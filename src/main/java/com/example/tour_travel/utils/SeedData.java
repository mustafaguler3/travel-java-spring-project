package com.example.tour_travel.utils;

import com.example.tour_travel.entity.Hotel;
import com.example.tour_travel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        Hotel hotel1 = new Hotel();
        hotel1.setName("Hotel Paradise");
        hotel1.setImageUrl("hotel-paradise.jpg");
        hotel1.setCity("Paris");
        hotel1.setLocation("Central Paris");
        hotel1.setCountry("France");
        hotel1.setPricePerNight(150.0);
        hotel1.setDescription("A beautiful hotel in the heart of Paris.");

        Hotel hotel2 = new Hotel();
        hotel2.setName("Ocean View");
        hotel2.setImageUrl("miami.jpg");
        hotel2.setCity("Miami");
        hotel2.setCountry("USA");
        hotel2.setLocation("Beachside");
        hotel2.setPricePerNight(200.0);
        hotel2.setDescription("A luxurious hotel with an ocean view.");

        // Add 8 more hotels similarly
        Hotel hotel3 = new Hotel();
        hotel3.setName("Mountain Retreat");
        hotel3.setImageUrl("denver.jpeg");
        hotel3.setCity("Denver");
        hotel3.setCountry("USA");
        hotel3.setLocation("Near the mountains");
        hotel3.setPricePerNight(180.0);
        hotel3.setDescription("A cozy retreat near the mountains.");

        Hotel hotel4 = new Hotel();
        hotel4.setName("City Center Inn");
        hotel4.setImageUrl("newyork.jpg");
        hotel4.setCity("New York");
        hotel4.setCountry("USA");
        hotel4.setLocation("Manhattan");
        hotel4.setPricePerNight(250.0);
        hotel4.setDescription("An elegant inn located in the heart of Manhattan.");

        Hotel hotel5 = new Hotel();
        hotel5.setName("Desert Oasis");
        hotel5.setImageUrl("lastvegas.jpg");
        hotel5.setCity("Las Vegas");
        hotel5.setCountry("USA");
        hotel5.setLocation("The Strip");
        hotel5.setPricePerNight(300.0);
        hotel5.setDescription("A luxurious oasis in the desert.");

        Hotel hotel6 = new Hotel();
        hotel6.setName("Lakeview Lodge");
        hotel6.setImageUrl("lake.webp");
        hotel6.setCity("Lake Tahoe");
        hotel6.setCountry("USA");
        hotel6.setLocation("Lakeside");
        hotel6.setPricePerNight(220.0);
        hotel6.setDescription("A beautiful lodge with a view of the lake.");

        Hotel hotel7 = new Hotel();
        hotel7.setName("Urban Stay");
        hotel7.setImageUrl("sanfran.jpg");
        hotel7.setCity("San Francisco");
        hotel7.setCountry("USA");
        hotel7.setLocation("Downtown");
        hotel7.setPricePerNight(190.0);
        hotel7.setDescription("A modern stay in the urban center.");

        Hotel hotel8 = new Hotel();
        hotel8.setName("Countryside Inn");
        hotel8.setImageUrl("nashville.jpg");
        hotel8.setCity("Nashville");
        hotel8.setLocation("Countryside");
        hotel8.setCountry("USA");
        hotel8.setPricePerNight(160.0);
        hotel8.setDescription("A peaceful inn in the countryside.");

        Hotel hotel9 = new Hotel();
        hotel9.setName("Island Resort");
        hotel9.setImageUrl("havaii.jpg");
        hotel9.setCity("Hawaii");
        hotel9.setLocation("Beachfront");
        hotel9.setCountry("USA");
        hotel9.setPricePerNight(350.0);
        hotel9.setDescription("A luxurious resort on the island's beachfront.");

        Hotel hotel10 = new Hotel();
        hotel10.setName("Historic Hotel");
        hotel10.setImageUrl("boston.jpg");
        hotel10.setCity("Boston");
        hotel10.setCountry("USA");
        hotel10.setLocation("Historic District");
        hotel10.setPricePerNight(210.0);
        hotel10.setDescription("A charming hotel in the historic district.");

        Hotel hotel11 = new Hotel();
        hotel11.setName("Impera Hotel - Special Category");
        hotel11.setImageUrl("impera.jpg");
        hotel11.setCity("Istanbul");
        hotel11.setCountry("Turkey");
        hotel11.setLocation("Historic District");
        hotel11.setPricePerNight(230.0);
        hotel11.setDescription("Featuring a bar, Impera Hotel - Special Category is set in the centre of Istanbul, 600 metres from Galata Tower.");

        Hotel hotel12 = new Hotel();
        hotel12.setName("Tin Suites");
        hotel12.setImageUrl("tin.jpg");
        hotel12.setCity("Istanbul");
        hotel12.setCountry("Turkey");
        hotel12.setLocation("Historic District");
        hotel12.setPricePerNight(210.0);
        hotel12.setDescription("Conveniently located in the centre of Istanbul, Tin Suites offers continental breakfast and free WiFi throughout the property. ");



        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);
        hotelRepository.save(hotel4);
        hotelRepository.save(hotel5);
        hotelRepository.save(hotel6);
        hotelRepository.save(hotel7);
        hotelRepository.save(hotel8);
        hotelRepository.save(hotel9);
        hotelRepository.save(hotel10);
        hotelRepository.save(hotel11);
        hotelRepository.save(hotel12);
    }
}
