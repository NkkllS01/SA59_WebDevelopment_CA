package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sg.edu.nus.ophone.interfacemethods.OrderInterface;
import sg.edu.nus.ophone.interfacemethods.ProductInterface;
import sg.edu.nus.ophone.interfacemethods.ReviewInterface;
import sg.edu.nus.ophone.interfacemethods.UserService;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.service.OrderImplementation;
import sg.edu.nus.ophone.service.ProductImplementation;
import sg.edu.nus.ophone.service.ReviewImplementation;
import sg.edu.nus.ophone.service.UserServiceImp;


import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/orangestore")
public class ProductController {
    @Autowired
    private ProductInterface pservice;

    @Autowired
    private ReviewInterface rservice;

    @Autowired
    private OrderInterface orderService;

    @Autowired
    private UserService userService;

    @Autowired
    public void setProductService(ProductImplementation pserviceImpl) {
        this.pservice = pserviceImpl;
    }

    @Autowired
    public void setReviewService(ReviewImplementation rserviceImpl) {
        this.rservice = rserviceImpl;
    }

    @Autowired
    public void setOrderService(OrderImplementation orderImp) {
        this.orderService = orderImp;
    }

    @Autowired
    public void setUserService(UserServiceImp userImp) {
        this.userService = userImp;
    }

    // index page --- common components
    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }

    // display the home page and get products for displaying
    @GetMapping("/home")
    public String getLandingPage(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "8") int size,
                                 HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("username") != null);
        model.addAttribute("isLoggedIn", isLoggedIn);

        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = pservice.getProduct(pageable);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
//        return "landingPage";
        return "landingPage-jm";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    // display all products searched by name
    @PostMapping("/all/products/searching")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = pservice.searchProductByKey(keyword);
        model.addAttribute("products", products);
        //model.addAttribute("avgRating", rservice.GetAverageRatingByKeyword(keyword));
        return "searchResults";
    }


    // display the product which is clicked via picture
    @GetMapping("/products/details/{id}")
    public String displayProducts(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("product", pservice.searchProductById(id));
        model.addAttribute("reviews", rservice.SearchReviewByProductId(id));
        model.addAttribute("avgrating", rservice.GetAverageRatingByPid(id));
        return "displayProduct";
    }

    // add to cart
    @PostMapping("products/details/{id}/addtocart")
    public String addToCart(@PathVariable("id") Long id, @RequestParam("quantity") Integer quantity, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        User loggedInUser = userService.findByName(username);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        System.out.println("adding to cart");

        Product product = pservice.getProductById(id);

        Long userId = (long) loggedInUser.getId();
        Order cart = orderService.getCartByUserId(userId);
        if (cart == null) {
            cart = new Order();
            cart.setUser(loggedInUser);
            cart.setOrderDetails(Arrays.asList(new OrderDetails(cart, product, quantity)));
            cart.setOrderStatus("cart");
            cart.setOrderDate(LocalDate.now());
        } else {
            cart.getOrderDetails().add(new OrderDetails(cart, product, quantity));
        }
        orderService.save(cart);

        return "redirect:/orangestore/products/details/" + id;
    }

    @PostMapping("/create")
    public String createProduct(Product product, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors.");
            return "redirect:/orangestore/Staff";
        }
        pservice.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
        return "redirect:/orangestore/Staff";

    }

    @GetMapping("/Staff")
    public String findAllProducts(Model model) {
        List<Product> products = pservice.findAllProducts();
        model.addAttribute("products", products);
        return "Staff";
    }

    @PostMapping("/edit")
    public String updateProduct (Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors.");
            return "redirect:/orangestore/Staff";
        }
            pservice.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
            return "redirect:/orangestore/Staff";
        }




}
