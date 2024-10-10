package sg.edu.nus.ophone.controller;


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
import sg.edu.nus.ophone.interfacemethods.UserService;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.service.UserServiceImp;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserServiceImp userImp) {
        this.userService = userImp;
    }

    @GetMapping("/login")
    public String Loginpage() {
        return "login";

    }


    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password,Model model, HttpSession session) {
        boolean loginsuccess=userService.login(username, password);
        if(loginsuccess) {
            session.setAttribute("username", username);
//            return "redirect:/product";
            return "redirect:/myaccount/profile/update";
        }else {
            model.addAttribute("error","Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/myaccount/profile/update")
    public String updateProfile(Model model, HttpSession session) {
        String username = session.getAttribute("username").toString();
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        return "profile-update";
    }

    @PostMapping("/myaccount/profile/save")
    public String saveProfile(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "profile-update";
        } else {
            userService.saveUser(user);
            model.addAttribute("successMsg", "Your details have been saved.");
            return "profile-update";
        }
    }


}