package com.ioxmentor.controller;

import com.ioxmentor.dto.LoginDTO;
import com.ioxmentor.dto.PayUMoneyDTO;
import com.ioxmentor.entity.Transaction;
import com.ioxmentor.entity.User;
import com.ioxmentor.enums.LoginStatus;
import com.ioxmentor.enums.SignUpStatus;
import com.ioxmentor.enums.TransanctionRepo;
import com.ioxmentor.repo.UserRepo;
import com.ioxmentor.service.Account;
import com.ioxmentor.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created By @Nabeel 08-Oct-2017
 **/

@Controller
public class BaseController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Account account;

    @Autowired
    private TransanctionRepo transanctionRepo;

    @Autowired
    private EnrollService enrollService;

    public void homeHeader(Model model, HttpServletRequest request) {
        model.addAttribute("isLogin", "0");
        if (request.getAttribute("userId") != null) {
            Long userId = Long.parseLong(request.getAttribute("userId").toString());
            model.addAttribute("isLogin", "1");
            User user = userRepo.findOne(userId);
            model.addAttribute("name", user.getUserName());
            model.addAttribute("userId", userId);
        }
    }

    @RequestMapping(value = "/home")
    public String homePage(Model model, HttpServletRequest request) {
        homeHeader(model, request);
        return "index";
    }


    @RequestMapping(value = "/doSignup")
    public String signup(HttpServletRequest request, Model model) {
        homeHeader(model, request);
        return "signup";
    }

    @RequestMapping(value = "/doLogin")
    public String login(HttpServletRequest request, Model model, @RequestParam(required = false) String redirect) {
        model.addAttribute("redirect", redirect);
        homeHeader(model, request);
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(Model modelAndView, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String contact) {
        SignUpStatus signUpStatus = account.signUp(name, email, password, contact);
        if (signUpStatus == SignUpStatus.DONE) {
            modelAndView.addAttribute("result", "You have successfully signed up.");
            LoginDTO loginDTO = account.doLogin(email, password);
            return "home";
        } else {
            if (signUpStatus == SignUpStatus.EMAIL_EXIST) {
                modelAndView.addAttribute("result", "Email already exists");
            } else {
                modelAndView.addAttribute("result", "Sign up failed");
            }
            return "signup";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGet(HttpServletRequest request, Model model) {
        homeHeader(model, request);
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, Model model) {
        homeHeader(model, request);
        return "login";
    }

    @RequestMapping(value = "{cid}/course", method = RequestMethod.GET)
    public String course(HttpServletRequest request, Model model, @PathVariable String cid) {
        homeHeader(model, request);
        model.addAttribute("cid", cid);
        if (cid.equals("1"))
            return "course";
        else if (cid.equals("2")) {
            return "devops";
        } else if (cid.equals("3")) {
            return "fullstack";
        }
        return "";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, Model modelAndView, @RequestParam String email, @RequestParam String password, @RequestParam(required = false) String redirect) {
        LoginDTO loginDTO = account.doLogin(email, password);
        if (loginDTO.getLoginMGS() == LoginStatus.LOGGED_IN) {
            Cookie cookie = new Cookie("token", loginDTO.getToken());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            modelAndView.addAttribute("result", "Login successful");
            if (redirect != null && !redirect.equals("")) {
                return "redirect:" + redirect;
            }
            return "redirect:/home";
        } else {
            modelAndView.addAttribute("result", "Email/Password not correct");
        }
        modelAndView.addAttribute("redirect", redirect);
        return "login";
    }

    @RequestMapping(value = "/payufailed", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String paymentCallbackFailed(HttpServletRequest request, Model modelAndView, PayUMoneyDTO payUMoneyDTO) {
        System.out.println("payUMoneyDTO " + payUMoneyDTO);
        modelAndView.addAttribute("result", "payment failed");
        homeHeader(modelAndView, request);
        return "result";
    }

    @RequestMapping(value = "/payusuccess", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String paymentCallback(HttpServletRequest request, Model modelAndView, PayUMoneyDTO payUMoneyDTO) {
        if (payUMoneyDTO.getStatus().equals("success")) {
            User user = userRepo.findByEmail(payUMoneyDTO.getEmail());
            if (user != null) {
                Transaction tr = new Transaction();
                tr.setUserId(user.getId());
                tr.setAdditionalCharges(payUMoneyDTO.getAdditionalCharges());
                tr.setAmount(payUMoneyDTO.getAmount());
                tr.setCustomerEmail(payUMoneyDTO.getEmail());
                tr.setCustomerName(payUMoneyDTO.getFirstname());
                tr.setPaymentId(payUMoneyDTO.getPaymentId());
                tr.setPaymentMode(payUMoneyDTO.getMode());
                tr.setStatus(payUMoneyDTO.getStatus());
                tr.setMerchantTransactionId(payUMoneyDTO.getTxnid());
                tr.setNotificationId(payUMoneyDTO.getNotificationId());
                tr.setCustomerPhone(payUMoneyDTO.getMobile());
                tr.setError_Message(payUMoneyDTO.getError_Message());
                if (transanctionRepo.save(tr) != null) {
                    String productInfo = payUMoneyDTO.getProductinfo();
                    Long enId = Long.parseLong(productInfo);
                    enrollService.enrollPayment(enId, Float.parseFloat(tr.getAmount()));
                }
            }
            modelAndView.addAttribute("result", "payment successful");
        } else {
            modelAndView.addAttribute("result", "payment failed");
        }
        homeHeader(modelAndView, request);
        return "result";
    }
}
