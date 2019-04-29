package com.mmt.enterprise.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enterprise")
public class MainController {
	
	@RequestMapping(value = "/main")
	public String main(Model model) {
		System.out.println("++++++to main.html+++++");
		return "enterprise/main";
	}
	
	@RequestMapping(value = "/top")
	public String top() {
		System.out.println("++++++to top.html+++++");
		return "enterprise/top";
	}
	
	@RequestMapping(value = "/left")
	public String left() {
		System.out.println("++++++to left.html+++++");
		return "enterprise/left";
	}
	
	
}
