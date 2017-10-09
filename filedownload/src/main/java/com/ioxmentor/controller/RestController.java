package com.ioxmentor.controller;


import com.ioxmentor.entity.Enroll;
import com.ioxmentor.enums.LoginStatus;
import com.ioxmentor.dto.LoginDTO;
import com.ioxmentor.enums.PaymentStatus;
import com.ioxmentor.enums.SignUpStatus;
import com.ioxmentor.repo.EnrollRepo;
import com.ioxmentor.service.Account;
import com.ioxmentor.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ioxmentor.repo.UserRepo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created By @Nabeel 08-Oct-2017
 **/

@Controller
@RequestMapping(value = "/user")
public class RestController {

    @Autowired
    private EnrollService enrollService;

    @RequestMapping(value = "{cId}/enroll")
    public String login(Model view, HttpServletRequest request, @PathVariable Long cId) {
        Enroll enroll = enrollService.enroll(Long.parseLong(request.getAttribute("userId").toString()), cId);
        view.addAttribute("result", "You have Added for the course .Find details below");
        view.addAttribute("Id", enroll.getId());
        view.addAttribute("cId", enroll.getCourseId());
        view.addAttribute("amount", enroll.getAmountPaid());
        view.addAttribute("cAt", enroll.getCreatedAt());
        view.addAttribute("pAt", enroll.getPaidAt());
        view.addAttribute("paidStatus", enroll.getPaymentStatus().name());
        return "enroll";
    }


}
