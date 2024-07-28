package com.example.tour_travel.service.impl;

import com.example.tour_travel.dto.UserDto;
import com.example.tour_travel.entity.Role;
import com.example.tour_travel.entity.User;
import com.example.tour_travel.entity.VerificationToken;
import com.example.tour_travel.repository.RoleRepository;
import com.example.tour_travel.repository.UserRepository;
import com.example.tour_travel.repository.VerificationTokenRepository;
import com.example.tour_travel.service.EmailService;
import com.example.tour_travel.service.FileStorageService;
import com.example.tour_travel.service.UserService;
import com.example.tour_travel.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(userDto.getConfirmPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEnabled(false);

        if (findByUsername(userDto.getUsername()) != null){
            throw new RuntimeException("Username already taken");
        }

        if (findByEmail(userDto.getEmail()) != null){
            throw new RuntimeException("Email already taken");
        }

        Role role = roleRepository.findByRoleName("ROLE_USER");

        if (role == null){
            Role newRole = new Role();
            newRole.setRoleName("ROLE_USER");
            roleRepository.save(newRole);
        }
        user.setRoles(Collections.singleton(role));

        MultipartFile profilePicture = userDto.getProfilePicture();

        if (profilePicture != null && !profilePicture.isEmpty()){
            try {
                String fileName = fileStorageService.storeFile(profilePicture,"users");
                user.setProfilePicture(fileName);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Failed to upload profile picture");
            }
        }

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60* 1000));
        verificationTokenRepository.save(verificationToken);

        emailService.sendVerificationEmail(user,token);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null){
            return null;
        }

        return dtoConverter.toDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null){
            return null;
        }

        return dtoConverter.toDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> result = users.stream().map(user -> dtoConverter.toDto(user)).toList();

        return result;
    }

    @Override
    public boolean verifyUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken != null){
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userRepository.save(user);
            verificationTokenRepository.delete(verificationToken);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password,encodedPassword);
    }
}























