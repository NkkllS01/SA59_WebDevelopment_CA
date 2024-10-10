package sg.edu.nus.ophone.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.ophone.service.UserServiceImp;


@Controller
public class UserController {

    @Autowired
    private UserServiceImp u;

    @GetMapping("/login")
    public String Loginpage() {
        return "login";

    }


    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password,Model model, HttpSession session) {
        boolean loginsuccess=u.login(username, password);
        if(loginsuccess) {
            session.setAttribute("username", username);
            return "redirect:/product";
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


}