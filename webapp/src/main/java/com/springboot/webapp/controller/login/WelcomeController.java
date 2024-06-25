package com.springboot.webapp.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@GetMapping("/")
	public String gotologin(ModelMap model) {
		model.put("name", getLoggerinUsername());
		return "welcome";
	}

	private String getLoggerinUsername() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
		}
}
