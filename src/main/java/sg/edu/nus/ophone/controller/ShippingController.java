package sg.edu.nus.ophone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

import sg.edu.nus.ophone.interfacemethods.ShippingInterface;
import sg.edu.nus.ophone.service.ShippingImplementation;
import sg.edu.nus.ophone.model.Shipping;

//Team3.Kuo Chi
@Controller
public class ShippingController {
    @Autowired
    private ShippingInterface shipService;

    @Autowired
    public void setShippingService(ShippingImplementation shipServiceImpl) {
        this.shipService = shipServiceImpl;
    }

    @PostMapping("/shipping")
    public String createShipping(@ModelAttribute ("shipping") @Valid Shipping shipping, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "shipping";
        } else {
            shipService.saveShipping(shipping);
            return "payment";
        }
    }
}
