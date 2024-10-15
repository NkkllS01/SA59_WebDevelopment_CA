package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.Shipping;
import sg.edu.nus.ophone.model.ShipRequest;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.service.ShippingService;
import sg.edu.nus.ophone.service.UserServiceImp;

//Team3.Kuo Chi
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ShippingController {
    @Autowired
    private ShippingService shipService;
    private UserServiceImp userServiceImp;

    @GetMapping("/userShipping")
    public ResponseEntity<User> retrieveUserData (HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            User user = userServiceImp.findByName(username);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/shipping")
    public ResponseEntity<Shipping> createShipping(HttpSession session,
                                                   //When a POST request is submitted to the shipping endpoint, the JSON body is automatically mapped to an instance of the ShippingRequest class
                                                   @RequestBody @Valid ShipRequest shipRequest,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        try {
            Order order = (Order) session.getAttribute("order");
            if (order == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Shipping shippingRecord = shipService.createShipping(order, shipRequest.getAddress(), shipRequest.getCity(), shipRequest.getPostalCode());
            session.setAttribute("order", order);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
