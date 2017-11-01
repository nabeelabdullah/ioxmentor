package com.codingsuperstar.codingsuperstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nabeelabdullah on 29/10/17.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin")
    public String adminPanel() {
        return "admin";
    }
}
