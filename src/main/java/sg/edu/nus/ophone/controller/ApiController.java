package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.ophone.service.OrderImplementation;
import sg.edu.nus.ophone.service.UserServiceImp;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private OrderImplementation orderService;

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        boolean isLoggedIn = session.getAttribute("username") != null;
        response.put("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            response.put("user", userService.findByName(session.getAttribute("username").toString()));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cart")
    public ResponseEntity<Map<String, Object>> getCartItems(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        int userId = userService.findByName(session.getAttribute("username").toString()).getId();
        response.put("items", orderService.findByUserId(userId));
        return ResponseEntity.ok(response);
    }
}
