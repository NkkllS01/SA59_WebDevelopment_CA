package sg.edu.nus.ophone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.Shipping;
import sg.edu.nus.ophone.service.ShippingService;

//Team3.Kuo Chi
@Controller
public class ShippingController {
    @Autowired
    private ShippingService shipService;

    @PostMapping("/shipping")
    public void shipping(@ModelAttribute ("order") Order order, @RequestParam String address, @RequestParam String city, @RequestParam String postalCode, Model model) {
        Shipping shippingRecord = shipService.createShipping(order, address, city, postalCode);
        model.addAttribute("shippingRecord", shippingRecord);
    }
}
