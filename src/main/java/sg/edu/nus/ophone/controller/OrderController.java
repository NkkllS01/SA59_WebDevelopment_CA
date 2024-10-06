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
import sg.edu.nus.ophone.model.Payment;
import sg.edu.nus.ophone.model.Shipping;
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
//        int username = (int) session.getAttribute("username");
//        List<Order> orders = orderService.findByUserId(username);
        List<Order> orders = orderService.findByUserId(1);
        model.addAttribute("orders", orders);
        return "order-history";
    }

    @GetMapping("/orders/{id}")
    public String displayOrderDetails(Model model, @PathVariable("id") Long orderId) {
        model.addAttribute("orderId", orderId);

        Order order = orderService.findByOrderId(orderId);
        model.addAttribute("order", order);
        Payment payment = order.getPayment();
        model.addAttribute("payment", payment);
        Shipping shipping = order.getShipping();
        model.addAttribute("shipping", shipping);
        
        List<OrderDetails> orderDetails = orderService.findByOrder(order);
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }

}
