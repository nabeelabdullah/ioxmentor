package com.codingsuperstar.codingsuperstar.controller;

import com.codingsuperstar.codingsuperstar.entity.Alert;
import com.codingsuperstar.codingsuperstar.enums.AlertRepo;
import com.codingsuperstar.codingsuperstar.mail.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by nabeelabdullah on 12/10/17.
 */
@RestController
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
        SendMail sendMail = new SendMail();
        sendMail.sendmail("<table><tr><td>Name</td><td>Email</td><td>Mobile</td><td>Message</td></tr>" + getContent(name, email, contact, message) + "</table>", "CodingSuperStar", null, "contact.nabeelabdullah@gmail.com", "CodingSuperStar", "New Alerts");
        return "done";
    }

    private String getContent(String name, String email, String mobile, String messge) {
        return "<tr><td>" + name + "</td>td>" + email + "</td>td>" + mobile + "</td><td>" + messge + "</td></tr>";
    }

}
