package com.example.tour_travel.service;

import com.example.tour_travel.dto.UserDto;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    List<UserDto> getUsers();
    boolean verifyUser(String token);

    boolean checkPassword(String password, String encodedPassword);
}
