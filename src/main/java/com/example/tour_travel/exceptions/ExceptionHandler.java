package com.example.tour_travel.exceptions;

import com.example.tour_travel.dto.UserDto;
import com.example.tour_travel.service.UserDetailsImpl;
import com.example.tour_travel.service.UserService;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            UserDto currentUser = userService.findByEmail(userDetails.getUsername());
            if (currentUser != null) {
                model.addAttribute("currentUser", currentUser);
                log.info("User image {}", currentUser.getProfilePictureUrl());
            } else {
                log.warn("User not found: {}", userDetails.getUsername());
            }
        }
    }
}
























