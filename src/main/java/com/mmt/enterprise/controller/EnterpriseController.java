package com.mmt.enterprise.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.service.CityService;
import com.mmt.enterprise.support.CustomerConfig;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EnterpriseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private CityService cityService;

	@Autowired
	private CustomerConfig config;
	
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

	@RequestMapping(value="/enterprise/enter_material")
	public String enterMaterial(Model model) {
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
		model.addAttribute("enter", user);
		return "enterprise/enter_material";
	}

	@RequestMapping(value="/enterprise/enter_submit", method=RequestMethod.POST)
	public String submit(@ModelAttribute(value="submitForm") EnterpriseUser enter, @RequestParam("file") MultipartFile file) {
		logger.info("++++++++enterprise material submit++++++++++");
		String licensePath = config.getLicensePath();
		File dir = new File(licensePath);
		if(!dir.exists()){
			dir.mkdir();
		}
		System.out.println(licensePath);
		try{
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
			licensePath += "/"+ enter.getId() + "."+ suffix;
			File tmpF = new File(licensePath);
			if(tmpF.exists()){
				tmpF.delete();
			}
			tmpF.createNewFile();
			Path path = Paths.get(licensePath);
			Files.write(path, bytes);
		}catch (Exception e){
			e.printStackTrace();
		}
		enter.setLicense(licensePath);
		enter.setStatus(1l);//未审核状态
		enterpriseService.saveEnterprise(enter);
		return "redirect:enter_material";
	}
}
