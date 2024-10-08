package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.ophone.interfacemethods.OrderInterface;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.model.Payment;
import sg.edu.nus.ophone.model.Shipping;
import sg.edu.nus.ophone.service.OrderImplementation;

import java.util.List;
import java.util.Locale;

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
    public String displayOrders(Model model, HttpSession session, Locale locale) {
//        int username = (int) session.getAttribute("username");
//        List<Order> orders = orderService.findByUserId(username);
        List<Order> orders = orderService.findByUserId(1);
        model.addAttribute("orders", orders);
        return "order-history";
    }

    @GetMapping("/orders/{id}")
    public String displayOrderDetails(Model model, @PathVariable("id") Long orderId, Locale locale) {
        model.addAttribute("orderId", orderId);

        Order order = orderService.findByOrderId(orderId);
        Double gst = (order.getTotalAmount() / 109) * 9;
        model.addAttribute("order", order);
        model.addAttribute("gst", gst);
        Payment payment = order.getPayment();
        model.addAttribute("payment", payment);
        Shipping shipping = order.getShipping();
        model.addAttribute("shipping", shipping);
        
        List<OrderDetails> orderDetails = orderService.findByOrder(order);
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }

    @PostMapping("/order/cancel")
    public String cancelOrder(@RequestParam("order") Order order, Model model, HttpSession session) {
        Shipping shipping = order.getShipping();
        String shippingStatus = shipping.getShippingStatus();
        if (!shippingStatus.equalsIgnoreCase("Order shipped") &&
                !shippingStatus.equalsIgnoreCase("Delivered")) {
            return "order-cancel";
        } else
            return "redirect:/orders";
    }

}
