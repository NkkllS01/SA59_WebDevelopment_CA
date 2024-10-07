package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        int userId = (int) session.getAttribute("userId");
        List<Order> orders = orderService.findByUserId(userId);
        orders = orderService.findByUserId(1);
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
//Cancel order
    @PostMapping("/orders/cancel/{id}")
    public String cancelOrder(Model model, @PathVariable("id") Long orderId, HttpSession session) {
        int userId = (int) session.getAttribute("userId");

        Order order = orderService.findByOrderIdAndUserId(orderId, userId);

    if (order!=null) {
        if (!order.getShipping().equals("Shipped") &&
                !order.getShipping().equals("Delivered")) {


            order.setOrderStatus("Cancelled");
            order.setPaymentStatus("Pending refund");
            order.setShippingStatus("Cancelled");

            orderService.save(order);
            model.addAttribute("message", "Order successfully cancelled.");
        } else {
            model.addAttribute("error", "Order cannot be cancelled as it has already been shipped or delivered.");
        }
    } else {
        model.addAttribute("error", "Order not found.");
    }

        return "order-confirm-cancel";
}
        }

