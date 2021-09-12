package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller bean for result page. Contains method for handling GET requests to result.html
 */

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String resultView() {
        return "result";
    }

}
