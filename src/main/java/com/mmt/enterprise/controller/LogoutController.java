package com.mmt.enterprise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value="/enterprise/logout")
    public String manageLogout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("++++++++logout action++++++++++");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "enterprise/login";
    }
}
