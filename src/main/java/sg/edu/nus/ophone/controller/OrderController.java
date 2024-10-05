package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sg.edu.nus.ophone.interfacemethods.OrderInterface;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.service.OrderImplementation;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderInterface orderService;

    @Autowired
    public void setOrderService(OrderImplementation orderImp) {
        this.orderService = orderImp;
    }

    @GetMapping ("/orders")
    // Http session data to store userId as an attribute
    public String displayOrders(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        List<Order> orders = orderService.findByUserId(userId);
        model.addAttribute("orders", orders);
        return "order-history";
    }

    @GetMapping("/orders/{id}")
    public String displayOrderDetails(Model model, @PathVariable("id") int orderId) {
        Order order = orderService.findByOrderId(orderId);
        List<OrderDetails> orderDetails = orderService.findByOrder(order);
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }

}
