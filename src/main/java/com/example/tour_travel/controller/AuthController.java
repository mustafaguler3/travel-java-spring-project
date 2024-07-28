package com.example.tour_travel.controller;

import com.example.tour_travel.dto.UserDto;
import com.example.tour_travel.entity.User;
import com.example.tour_travel.service.FileStorageService;
import com.example.tour_travel.service.UserService;
import groovy.util.logging.Log4j2;
import groovy.util.logging.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user",new UserDto());
        return "login";
    }
    /*Spring Security’yi kullanıyorsanız, manuel olarak PostMapping("/login") yöntemini kontrol etmeniz gerekmeyebilir çünkü bu işlemi Spring Security zaten yönetir. Bu nedenle, login işlemi için Spring Security’yi kullanmaya özen gösterin ve gerekli yönlendirmeleri yapın.*/
    @PostMapping("/login")
    public String loginPost(@Valid @ModelAttribute("user") UserDto userDto,
                                BindingResult bindingResult,
                                Model model) {

        System.out.println("Login attempt with email: " + userDto.getEmail());

        UserDto user = userService.findByEmail(userDto.getEmail());

        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors found: " + bindingResult.getAllErrors());
            return "login";
        }

        if (user == null) {
            model.addAttribute("errorMsg", "There is no account associated with this email address.");
            return "login";
        }

        if (!user.isEnabled()) {
            model.addAttribute("errorMsg", "Please verify your email address before logging in.");
            return "login";
        }

        if (!userService.checkPassword(userDto.getPassword(),user.getPassword())){
            model.addAttribute("errorMsg","Invalid username or password");
            return "login";
        }

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("user") UserDto userDto,
                               @RequestParam(required = false,value = "success") String success,
                               BindingResult bindingResult,
                               Model model){
        if (bindingResult.hasErrors()){
            return "register";
        }
        userService.createUser(userDto);
        return "redirect:/register?success";
    }
    // http://localhost:8080/verify?token= ourToken
    @GetMapping("/verify")
    public String confirmationUser(@RequestParam String token,
                                   Model model){
        boolean isVerified = userService.verifyUser(token);
        if (isVerified){
            model.addAttribute("success","User verified successfully");
            return "redirect:/login";
        }else{
            model.addAttribute("error","Verification token is invalid or expired");
            return "redirect:/login";
        }
    }

    @GetMapping("/uploads/{fileType}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename,
                                             @PathVariable String fileType) {

        Resource file = fileStorageService.loadFile(filename,fileType);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}


























