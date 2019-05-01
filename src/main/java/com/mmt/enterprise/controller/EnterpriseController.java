package com.mmt.enterprise.controller;

import java.util.List;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmt.enterprise.entity.EnterpriseUser;
import com.mmt.enterprise.service.EnterpriseService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EnterpriseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/enterprise/enter_info")
	public String info(Model model) {
		logger.info("++++++++enterprise info++++++++++");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		logger.info("username is "+ username);
		EnterpriseUser user = enterpriseService.getEnterpriseUserByName(username);
		City city = cityService.getCityById(user.getCityId());
		model.addAttribute("city", city);
		model.addAttribute("enter", user);
		return "enterprise/enter_info";
	}

	@RequestMapping(value="/enterprise/enter_save", method=RequestMethod.POST)
	public String save(@ModelAttribute(value="enterForm") EnterpriseUser enter) {
		logger.info("++++++++enterprise save++++++++++");
		enterpriseService.saveEnterprise(enter);
		return "redirect:enter_info";
	}
}
