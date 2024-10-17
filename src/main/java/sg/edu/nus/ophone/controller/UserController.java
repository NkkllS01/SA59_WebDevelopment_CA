package sg.edu.nus.ophone.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.service.UserServiceImp;

import java.util.List;

import static sg.edu.nus.ophone.interceptor.LoginInterceptor.LOGGER;


@Controller
public class UserController {

    @Autowired
    private UserServiceImp u;


	@GetMapping("/login")
    public String Loginpage() {
        return "login";

    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
    		@RequestParam String password,@RequestParam String userType, Model model, HttpSession session) {
        boolean loginsuccess=u.login(username, password);
        if(loginsuccess) {
            session.setAttribute("username", username);
            session.setAttribute("userType", userType);
			LOGGER.info("Session Username after login: " + session.getAttribute("username"));
            if(userType.equalsIgnoreCase("customer")) {
                return "redirect:/orangestore/home";
            }else if(userType.equalsIgnoreCase("staff")){
				return"redirect:/orangestore/Staff";
            }else {
            	return "login";
            }
        }else {
            model.addAttribute("error","Invalid username or password.");
            return "login";
        }
    }
    @GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session
		}
		return "redirect:/login?logout=true";
	}
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute ("user") User user, BindingResult bindingResult, Model model) {
		// Check for password length before any validation
		if (user.getPassword() != null && (user.getPassword().length() < 8 || user.getPassword().length() > 20)) {
			model.addAttribute("error", "Password must be between 8-20 characters");
			return "register";
		}
		if (bindingResult.hasErrors()) {
			return "register";
		}
		if(u.usernameExists(user.getName())) {
			model.addAttribute("error","Username already exists");
			return "register";
		} 
		if (!user.getPassword().equals(user.getConfirmPassword())) {
		        model.addAttribute("error", "Passwords do not match");
		        return "register";
		  }
		User newUser = new User();
		newUser.setName(user.getName());  // Name is now part of user
		newUser.setPassword(user.getPassword());// Password is from user object
		newUser.setEmail(user.getEmail());
		newUser.setUserType("customer");
		newUser.setAddress(user.getAddress());
		newUser.setCity(user.getCity());
		newUser.setPostalCode(user.getPostalCode());
		u.saveUser(newUser);
		return "redirect:/login";
	}


	@GetMapping("/myaccount/profile/update")
	public String updateProfile(Model model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		User user = u.findByName(username);
		model.addAttribute("user", user);
		return "profile-update";
	}
	@PostMapping("/myaccount/profile/save")
	public String saveProfile(@ModelAttribute("user") @Valid User user,
							  BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "profile-update";
		} else {
			u.saveUser(user);
			model.addAttribute("successMsg", "Your details have been saved.");
			return "profile-update";
		}
	}


}