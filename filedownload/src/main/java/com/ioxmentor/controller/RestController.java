package com.ioxmentor.controller;


import com.ioxmentor.enums.LoginStatus;
import com.ioxmentor.dto.LoginDTO;
import com.ioxmentor.enums.SignUpStatus;
import com.ioxmentor.service.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ioxmentor.repo.UserRepo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * created By @Nabeel 08-Oct-2017
 **/

@Controller
@RequestMapping(value = "/user")
public class RestController {


}
