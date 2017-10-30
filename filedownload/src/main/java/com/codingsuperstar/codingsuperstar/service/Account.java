package com.codingsuperstar.codingsuperstar.service;

import com.codingsuperstar.codingsuperstar.enums.LoginStatus;
import com.codingsuperstar.codingsuperstar.dto.LoginDTO;
import com.codingsuperstar.codingsuperstar.entity.Login;
import com.codingsuperstar.codingsuperstar.entity.User;
import com.codingsuperstar.codingsuperstar.enums.SignUpStatus;
import com.codingsuperstar.codingsuperstar.repo.LoginRepo;
import com.codingsuperstar.codingsuperstar.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
@Component
public class Account {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LoginRepo loginRepo;

    public LoginDTO doLogin(String email, String password) {
        LoginDTO loginDTO = new LoginDTO();
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
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
                userRepo.save(user);
                return SignUpStatus.DONE;
            } else {
                return SignUpStatus.EMAIL_EXIST;
            }
        } catch (Exception er) {
            return SignUpStatus.FAILED;
        }
    }
}
