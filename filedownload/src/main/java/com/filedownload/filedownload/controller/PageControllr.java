package com.filedownload.filedownload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nabeelabdullah on 01/10/17.
 */
@Controller
public class PageControllr {

    @RequestMapping(value = "/home")
    public String homePage() {
        return "index";
    }
}
