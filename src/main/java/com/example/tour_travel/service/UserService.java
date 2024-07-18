package com.example.tour_travel.service;

import com.example.tour_travel.UserDto;
import com.example.tour_travel.entity.User;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    List<UserDto> getUsers();
}
