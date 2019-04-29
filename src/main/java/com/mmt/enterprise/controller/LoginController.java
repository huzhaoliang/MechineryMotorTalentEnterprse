package com.mmt.enterprise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController 
{
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	@RequestMapping(value="/enterprise/login")
	public String manageLogin() {
		logger.info("++++++++login action++++++++++");
		return "enterprise/login";
	}
	
	@RequestMapping("/enterprise/login-error")
	public String manageLoginError(Model model) {
		model.addAttribute("loginError", true);
		return "enterprise/login";
	}
	
	@RequestMapping("/enterprise/404")
	public String manageNotFound(Model model) {
		return "enterprise/404";
	}
}
