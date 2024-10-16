package sg.edu.nus.ophone.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sg.edu.nus.ophone.interfacemethods.ProductInterface;
import sg.edu.nus.ophone.interfacemethods.ReviewInterface;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.service.ProductImplementation;
import sg.edu.nus.ophone.service.ReviewImplementation;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orangestore")
public class ProductController {
    @Autowired
    private ProductInterface pservice;

    @Autowired
    private ReviewInterface rservice;

    @Autowired
    public void setProductService(ProductImplementation pserviceImpl) {
        this.pservice = pserviceImpl;
    }

    @Autowired
    public void setReviewService(ReviewImplementation rserviceImpl) {
        this.rservice = rserviceImpl;
    }

    // index page --- common components
    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }

    // display the home page and get products for displaying
    @GetMapping("/home")
    public String getLandingPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("username") != null);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("products", pservice.getProduct());
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
        if (products.isEmpty()) {
            return "noProductFound";
        }
        model.addAttribute("products", products);
        return "searchResults";
    }
    

    // display the product which is clicked via picture
    @GetMapping("/products/details/{id}")
    public String displayProducts(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("product", pservice.searchProductById((long)id));
        model.addAttribute("reviews", rservice.SearchReviewByProductId(id));
        model.addAttribute("avgrating", rservice.GetAverageRating(id));
        return "displayProduct";
    }

    @PostMapping("/create")
    public String createProduct(Product product, BindingResult result, RedirectAttributes redirectAttributes,Model model) {
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
        List<Product> products= pservice.findAllProducts();
        model.addAttribute("products",products);
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


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Product product = pservice.searchProductById(id);
        if (product != null) {
            pservice.deleteProduct(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found.");
        }
        return "redirect:/orangestore/Staff";
    }

    
    
    
    

}
