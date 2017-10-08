package com.ioxmentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** created By @Nabeel 08-Oct-2017 **/

@Controller
public class BaseController {
	
	@RequestMapping(value = "/home")
    public String homePage() {
        return "index";
    }
	
}
