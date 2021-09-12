package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller bean for signup page. Contains methods for handling GET and POST requests to signup.html
 * Uses methods from 'UserService' for validating and creating users on signup.
 */



@Controller()
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;
    public SignupController(UserService userService){
        this.userService = userService;
    }

    // Method to handle GET requests
    @GetMapping
    public String signupView(){
        return "signup";
    }

    // Method to handle POST requests
    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){
        String signupError = null;

        // checking username availability
        if(!userService.isUsernameAvailable(user.getUsername())){
            signupError = "Username already exists. Try a different one!";
        }

        if (signupError == null){
            int rowsAdded = userService.createUser(user); // creating new user
            if (rowsAdded < 0){
                signupError = "Signup failed. Try again later!";
            }
        }

        // updating success or error flags
        if (signupError == null){
            model.addAttribute("signupSuccess",true);
        } else {
            model.addAttribute("signupError",signupError);
        }
        return "signup";

    }
}
