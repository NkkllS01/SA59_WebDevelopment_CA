package sg.edu.nus.ophone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.service.ProductService;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ManagementProductController {

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
        return "redirect:/products/list";
    }


    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "searchResults";
    }


    @GetMapping("/edit")
    public String editProductForm( Model model) {
        model.addAttribute("product", new Product());
        return "editProduct";
    }

    @GetMapping("/products/edit")
    public String editProduct(Model model) {
        // 这里处理逻辑，添加到模型
        model.addAttribute("product", new Object()); // 模拟一个空产品对象
        return "editProduct"; // 返回视图 editProduct.html
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editProduct";
        }
        productService.saveProduct(product);
        return "redirect:/products/list";
    }

    @GetMapping("/list")
    public String searchProducts(Model model) {
        List<Product> products = productService.findAllProducts();  // 使用非静态方法
        model.addAttribute("products", products);
        return "product-list";
    }
    @GetMapping("/delete")
    public String showDeleteProductPage(Model model) {
        model.addAttribute("product", new Product());  // 传递空的 Product 对象到模板
        return "deleteProduct";  // 返回 deleteProduct.html
    }


    @PostMapping("deleteConfirm")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.deleteProduct(product);  // 删除产品
        return "redirect:/products/list";  // 删除后重定向回产品列表页面
    }

}



