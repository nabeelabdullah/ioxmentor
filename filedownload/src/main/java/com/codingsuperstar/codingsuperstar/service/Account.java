package com.codingsuperstar.codingsuperstar.service;

import com.codingsuperstar.codingsuperstar.entity.Validation;
import com.codingsuperstar.codingsuperstar.enums.*;
import com.codingsuperstar.codingsuperstar.dto.LoginDTO;
import com.codingsuperstar.codingsuperstar.entity.Login;
import com.codingsuperstar.codingsuperstar.entity.User;
import com.codingsuperstar.codingsuperstar.mail.MailRequest;
import com.codingsuperstar.codingsuperstar.mail.SendMail;
import com.codingsuperstar.codingsuperstar.repo.LoginRepo;
import com.codingsuperstar.codingsuperstar.repo.UserRepo;
import com.codingsuperstar.codingsuperstar.repo.ValidationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
@Component
public class Account {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailRequest mailRequest;

    @Autowired
    private ValidationRepo validationRepo;

    @Autowired
    private LoginRepo loginRepo;

    public LoginDTO doLogin(String email, String password) {
        LoginDTO loginDTO = new LoginDTO();
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getActiveStatus() == ActiveStatus.ACTIVE) {
                String token = UUID.randomUUID().toString();
                loginDTO.setToken(token);
                loginDTO.setLoginMGS(LoginStatus.LOGGED_IN);
                Login login = new Login();
                login.setToken(token);
                login.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, 1);
                login.setExpAt(new Timestamp(c.getTimeInMillis()));
                login.setUserId(user.getId());
                loginRepo.save(login);
            } else {
                loginDTO.setLoginMGS(LoginStatus.NOT_ACTIVE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Validation validation = validationCode(user.getId());
                            String link = "http://codingsuperstar.com/validate?id=" + validation.getId() + "&code=" + validation.getCode();
                            Map<String, String> map = new HashMap<>();
                            map.put("name", user.getUserName());
                            map.put("link", link);
                            String content = mailRequest.getContent(Template.SIGN_UP_NAME, map);
                            SendMail sendMail = new SendMail();
                            sendMail.sendmail(content, new String[]{email}, "Email Validation");
                        } catch (Exception er) {
                            er.printStackTrace();
                        }
                    }
                }).start();
            }
        } else {
            loginDTO.setLoginMGS(LoginStatus.NOT_LOGGED_IN);
        }
        return loginDTO;
    }

    public SignUpStatus signUp(String name, String email, String password, String contact) {
        try {
            if (userRepo.findByEmail(email) == null) {
                User user = new User();
                user.setEmail(email);
                user.setIsActive(1);
                user.setPassword(password);
                user.setUserName(name);
                user.setContact(contact);
                user.setActiveStatus(ActiveStatus.EMAIL_VARIFICATION_PENDING);
                userRepo.save(user);
                return SignUpStatus.DONE;
            } else {
                return SignUpStatus.EMAIL_EXIST;
            }
        } catch (Exception er) {
            return SignUpStatus.FAILED;
        }
    }

    public ValidationStatus doValidate(Long id, String code) {
        Validation validation = validationRepo.findOne(id);
        if (validation == null || !validation.getCode().equals(code)) {
            return ValidationStatus.NOT_MATCHED;
        }
        if (validation.getExpAt().getTime() < Calendar.getInstance().getTimeInMillis()) {
            return ValidationStatus.CODE_EXP;
        }
        User user = userRepo.findOne(validation.getUserId());
        user.setActiveStatus(ActiveStatus.ACTIVE);
        userRepo.save(user);
        return ValidationStatus.SUCCESSFUL;
    }

    public Validation validationCode(Long userId) {
        String code = UUID.randomUUID().toString();
        Validation validation = new Validation();
        validation.setCode(code);
        validation.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        validation.setExpAt(new Timestamp(calendar.getTimeInMillis()));
        validation.setUserId(userId);
        return validationRepo.save(validation);
    }
}
