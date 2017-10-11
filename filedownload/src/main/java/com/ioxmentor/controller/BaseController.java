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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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

    @RequestMapping(value = "/home")
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = "/doSignup")
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/doLogin")
    public String login(Model view, @RequestParam(required = false) String redirect) {
        view.addAttribute("redirect", redirect);
        System.out.println("redirect " + redirect);
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(Model modelAndView, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String contact) {
        SignUpStatus signUpStatus = account.signUp(name, email, password, contact);
        if (signUpStatus == SignUpStatus.DONE) {
            modelAndView.addAttribute("result", "You have successfully signed up.");
        } else {
            if (signUpStatus == SignUpStatus.EMAIL_EXIST) {
                modelAndView.addAttribute("result", "Email already exists");
            } else {
                modelAndView.addAttribute("result", "Sign up failed");
            }
            return "signup";
        }
        return "result";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGet() {
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String course() {
        return "course";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, Model modelAndView, @RequestParam String email, @RequestParam String password, @RequestParam(required = false) String redirect) {
        LoginDTO loginDTO = account.doLogin(email, password);
        if (loginDTO.getLoginMGS() == LoginStatus.LOGGED_IN) {
            Cookie cookie = new Cookie("token", loginDTO.getToken());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            modelAndView.addAttribute("result", "Login successful");
            if (redirect != null && !redirect.equals("")) {
                return "redirect:" + redirect;
            }
        } else {
            modelAndView.addAttribute("result", "Email/Password not correct");
        }
        modelAndView.addAttribute("redirect", redirect);
        return "login";
    }

    @RequestMapping(value = "/payufailed", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String paymentCallbackFailed(Model modelAndView, PayUMoneyDTO payUMoneyDTO) {
        modelAndView.addAttribute("result", "payment failed");
        return "result";
    }

    @RequestMapping(value = "/payusuccess", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String paymentCallback(Model modelAndView, PayUMoneyDTO payUMoneyDTO) {
        User user = userRepo.findByEmail(payUMoneyDTO.getCustomerEmail());
        if (user != null) {
            Transaction tr = new Transaction();
            tr.setUserId(user.getId());
            tr.setAdditionalCharges(payUMoneyDTO.getAdditionalCharges());
            tr.setAmount(payUMoneyDTO.getAmount());
            tr.setCustomerEmail(payUMoneyDTO.getCustomerEmail());
            tr.setMerchantTransactionId(payUMoneyDTO.getMerchantTransactionId());
            tr.setNotificationId(payUMoneyDTO.getNotificationId());
            tr.setCustomerPhone(payUMoneyDTO.getCustomerPhone());
            tr.setError_Message(payUMoneyDTO.getError_Message());
            tr.setProductInfo(payUMoneyDTO.getProductInfo());
            transanctionRepo.save(tr);
        }
        modelAndView.addAttribute("result", "payment successful");
        return "result";
    }
}
