package com.ioxmentor.filter;

import com.ioxmentor.entity.Login;
import com.ioxmentor.repo.LoginRepo;
import org.apache.catalina.servlet4preview.http.HttpFilter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
@WebFilter(urlPatterns = "/ac/*")
@Component
public class filter extends HttpFilter {

    @Autowired
    private LoginRepo loginRepo;

    @Override
    public void destroy() {

    }

    private String getToken(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            System.out.println("cookie name " + cookie.getName() + " " + cookie.getValue());
            if (cookie.getName().equals("token")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Boolean redirectToLogin = true;
        if (request.getRequestURI().contains("/user/")) {
            String token = getToken(request.getCookies());
            if (token != null) {
                Login login = loginRepo.findByToken(token);
                if (login != null && login.getExpAt().getTime() > Calendar.getInstance().getTimeInMillis()) {
                    redirectToLogin = false;
                }
            }
            if (redirectToLogin) {
                response.sendRedirect("http://localhost:8080/doLogin");
            } else {
                super.doFilter(request, response, chain);
            }
        } else {
            super.doFilter(request, response, chain);
        }

    }
}
