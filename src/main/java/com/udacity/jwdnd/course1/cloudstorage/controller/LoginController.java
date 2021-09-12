package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller bean for result page. Contains method for handling GET requests to login.html
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String loginView(){
        return "login";
    }

}
