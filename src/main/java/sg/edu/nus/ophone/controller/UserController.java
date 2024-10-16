package sg.edu.nus.ophone.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            	return"editProduct";
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
	public String registerPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(
			@RequestParam String username,
			@RequestParam String password,
			 @RequestParam String confirmPassword,
			@RequestParam String email,
			@RequestParam String address,
			@RequestParam String city,
			@RequestParam String postalCode,
			Model model) {
		if(u.usernameExists(username)) {
			model.addAttribute("error","Username already exists");
			return "register";
		} 
		if (!password.equals(confirmPassword)) {
		        model.addAttribute("error", "Passwords do not match");
		        return "register";
		  }
		User newuser =new User();
		newuser.setName(username);
		newuser.setPassword(password);
		newuser.setEmail(email);
		newuser.setUserType("customer");
		newuser.setAddress(address);
		newuser.setCity(city);
		newuser.setPostalCode(postalCode);
		u.saveUser(newuser);
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