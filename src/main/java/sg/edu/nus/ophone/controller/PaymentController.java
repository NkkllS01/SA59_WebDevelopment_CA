package sg.edu.nus.ophone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.service.OrderImplementation;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Controller
public class PaymentController {
    @Autowired
    private OrderImplementation orderImplementation;

    @GetMapping("/payment")
    public String showPaymentPage(Model model, HttpSession session) {
        Long orderId = (Long) session.getAttribute("orderId");
        Order order = orderImplementation.findByOrderId(orderId);

        model.addAttribute("order", order);
        return "payment";
    }

}
