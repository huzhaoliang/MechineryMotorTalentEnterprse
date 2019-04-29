package com.mmt.enterprise.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmt.enterprise.entity.EnterpriseUser;
import com.mmt.enterprise.service.EnterpriseService;

@Controller
public class EnterpriseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping(value="/enterprise/enter_info")
	public String list(Model model) {
		logger.info("++++++++enterprise info++++++++++");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		EnterpriseUser user = enterpriseService.getEnterpriseUserByEmail(username);
		model.addAttribute("enterprise", user);
		return "enterprise/enter_info";
	}
}
