package com.example.tour_travel.utils;

import com.example.tour_travel.dto.UserDto;
import com.example.tour_travel.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DTOConverter {


    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEnabled(userDto.isEnabled());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());

        MultipartFile multipartFile = userDto.getProfilePicture();

        user.setProfilePicture();
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEnabled(user.isEnabled());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


}

















