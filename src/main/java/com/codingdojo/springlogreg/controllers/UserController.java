package com.codingdojo.springlogreg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.springlogreg.models.LoginUser;
import com.codingdojo.springlogreg.models.User;
import com.codingdojo.springlogreg.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	 // Add once service is implemented:
     @Autowired
     private UserService userService;
	
	@GetMapping("/")
	public String logregForm(Model model) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}
	
	@PostMapping("/register")
	public String processRegister(@Valid @ModelAttribute("newUser") User newUser, BindingResult result,
			Model model, HttpSession session
			) {
		//1. get the registeredUser by calling register in service and make use of the binging
		User registeredUser = userService.register(newUser, result);
		//2. Check for result errors
		if(result.hasErrors()) {
			//2.1 if there are errors, put the missing model in and return jsp
			model.addAttribute("newLogin", new LoginUser());
			return "logreg.jsp";
		} else {
			//2.2 if no errors, set userId in session and redirect
			// store the user
			session.setAttribute("userId", registeredUser.getId());
			return "redirect:/dashboard";
		}
		
	}
	@PostMapping("/login")
	public String processLogin(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
			Model model, HttpSession session
			) {
		User loggedIndUser = userService.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "logreg.jsp";
		} else {
			// store the user in session
			session.setAttribute("userId", loggedIndUser.getId());
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/dashboard")
	public String renderDashboard(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		return "dashboard.jsp";
	}
	
	@GetMapping("logout")
	public String processLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
