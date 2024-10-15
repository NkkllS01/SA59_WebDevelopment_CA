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
import sg.edu.nus.ophone.service.OrderImplementation;
import sg.edu.nus.ophone.service.ShippingService;
import sg.edu.nus.ophone.service.UserServiceImp;

import java.util.List;
import java.util.Optional;

//Team3.Kuo Chi
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ShippingController {
    @Autowired
    private ShippingService shipService;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private OrderImplementation orderImplement;

    @GetMapping("/userShipping")
    public ResponseEntity<User> retrieveUserData (HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            System.out.println("Retrieved username from session: " + username);
            System.out.println("Session ID: " + session.getId());
            if (username == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

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
            String username = (String) session.getAttribute("username");
            User user = userServiceImp.findByName(username);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            long userId = user.getId();
            List<Order> orders = orderImplement.findByUserId(userId);
            Optional<Order> optionalOrder = orders.stream()
                    .filter( x -> x.getOrderStatus().equals("Cart")).findFirst();

            if (!optionalOrder.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No cart found
            }
            Order order = optionalOrder.get();
            Shipping shippingRecord = shipService.createShipping(order, shipRequest.getAddress(), shipRequest.getCity(), shipRequest.getPostalCode());
            session.setAttribute("order", order);

            return new ResponseEntity<>(shippingRecord, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
