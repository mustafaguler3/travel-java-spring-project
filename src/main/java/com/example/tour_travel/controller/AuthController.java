package com.example.tour_travel.controller;

import com.example.tour_travel.dto.UserDto;
import com.example.tour_travel.entity.User;
import com.example.tour_travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user",new UserDto());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new UserDto());
        return "register";
    }
    /*

    @GetMapping("/uploads/{fileType}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename,
                                             @PathVariable String fileType) {

        Resource file = fileStorageService.loadFile(filename,fileType);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
     */

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult bindingResult,
                               Model model){
        if (bindingResult.hasErrors()){
            return "register";
        }
        userService.createUser(userDto);
        model.addAttribute("success","User registered successfully");
        return "redirect:/login";
    }
}


























