package sg.edu.nus.ophone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.ophone.interfacemethods.ProductService;
import sg.edu.nus.ophone.model.Product;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createProduct";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "searchResults";
    }

    
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "editProduct";
    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editProduct";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        if (product != null) {
            productService.deleteProduct(product);
        }
        return "redirect:/products";
    }
}
