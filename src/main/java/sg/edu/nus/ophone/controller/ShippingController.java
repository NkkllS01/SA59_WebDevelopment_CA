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

    // upon receiving a GET request from client, return a User object
    @GetMapping("/userShipping")
    public ResponseEntity<User> retrieveUserData (HttpSession session) {
        try {
            // obtain the username stored in session
            String username = (String) session.getAttribute("username");
            System.out.println("Retrieved username from session: " + username);
            System.out.println("Session ID: " + session.getId());

            // return HttpStatus.UNAUTHORIZED if username is null
            if (username == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // find the user object from the username
            User user = userServiceImp.findByName(username);

            // return HttpStatus.NOT_FOUND if user is null, else return user object
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        // return HttpStatus.EXPECTATION_FAILED if there's an exception
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    // upon receiving a POST request from client, create a new shipping record
    @PostMapping("/shipping")
    public ResponseEntity<Shipping> createShipping(HttpSession session,
                                                   // the JSON body is automatically mapped to an instance of the ShipRequest class
                                                   @RequestBody @Valid ShipRequest shipRequest,
                                                   BindingResult bindingResult) {

        // return HttpStatus.EXPECTATION_FAILED if there's any issue with the validation
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        try {
            // obtain the username stored in session
            String username = (String) session.getAttribute("username");

            // return HttpStatus.UNAUTHORIZED if username is null
            if (username == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // find the user object from the username
            User user = userServiceImp.findByName(username);

            // return HttpStatus.NOT_FOUND if user is null, else return user object
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // get the userId from the user object
            long userId = user.getId();

            // find the list of orders by userId
            List<Order> orders = orderImplement.findByUserId(userId);
            // filter through the list where order status = "Cart"
            Optional<Order> optionalOrder = orders.stream()
                    .filter( x -> x.getOrderStatus().equals("Cart"))
                    .findFirst();

            // if there is no order fulfilling the criteria, return HttpStatus.NOT_FOUND
            if (!optionalOrder.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No cart found
            }
            // extracts the order object from optionalOrder and create shipping record
            Order order = optionalOrder.get();
            Shipping shippingRecord = shipService.createShipping(order, shipRequest.getAddress(), shipRequest.getCity(), shipRequest.getPostalCode());

            System.out.println("Successfully created shipping record.");

            // set orderId to session
            session.setAttribute("orderId", order.getId());

            System.out.println("Successfully set orderId in session.");

            // return newly created shipping record object and HttpStatus.CREATED
            return new ResponseEntity<>(shippingRecord, HttpStatus.CREATED);

        }
        // return HttpStatus.EXPECTATION_FAILED if there's an exception
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}