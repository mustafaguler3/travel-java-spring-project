package com.example.tour_travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class TourTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourTravelApplication.class, args);
	}



}
