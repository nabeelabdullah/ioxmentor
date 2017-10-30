package com.codingsuperstar.codingsuperstar.filter;

import com.codingsuperstar.codingsuperstar.entity.Login;
import com.codingsuperstar.codingsuperstar.repo.LoginRepo;
import org.apache.catalina.servlet4preview.http.HttpFilter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        return null;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Boolean redirectToLogin = true;

        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String host = url.substring(0, url.indexOf(uri));
        String token = getToken(request.getCookies());
        Login login = null;
        if (token != null) {
            login = loginRepo.findByToken(token);
            if (login != null) {
                request.setAttribute("userId", login.getUserId());
                request.setAttribute("tokenId", login.getId());
            }
        }
        if (request.getRequestURI().contains("/user/")) {
            String redirect = "";
            if (token != null) {
                if (login != null && login.getExpAt().getTime() > Calendar.getInstance().getTimeInMillis()) {
                    redirectToLogin = false;
                }
            }
            if (redirectToLogin) {
                String red = request.getParameter("redirect");
                if (red != null && red.equals("self")) {
                    redirect = "redirect=" + request.getRequestURL();
                }
                response.sendRedirect(host + "/doLogin?" + redirect);
            } else {
                super.doFilter(request, response, chain);
            }
        } else {
            super.doFilter(request, response, chain);
        }

    }
}
