package com.ioxmentor.controller;

import com.ioxmentor.entity.Alert;
import com.ioxmentor.enums.AlertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by nabeelabdullah on 12/10/17.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/alert")
public class HomeController {

    @Autowired
    private AlertRepo alertRepo;

    @RequestMapping(value = "/save")
    public String alert(@RequestParam String name, @RequestParam String email, @RequestParam String contact, @RequestParam String message) {
        Alert alert = new Alert();
        alert.setContact(contact);
        alert.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        alert.setEmail(email);
        alert.setMessage(message);
        alert.setName(name);
        alertRepo.save(alert);
        return "done";
    }

}
