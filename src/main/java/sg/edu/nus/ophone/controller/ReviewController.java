package sg.edu.nus.ophone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.nus.ophone.interfacemethods.ReviewInterface;
import sg.edu.nus.ophone.service.ReviewImplementation;

@Controller
@RequestMapping("/orangestore/products/details")
public class ReviewController {
    @Autowired
    private ReviewInterface rservice;

    @Autowired
    public void setReviewService(ReviewImplementation rserviceImpl) {
        this.rservice = rserviceImpl;
    }

    // display all reviews of product
    @GetMapping("/{id}/reviews")
    public String displayReviews(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("reviews", rservice.SearchReviewByProductId(id));
        return "reviews";
    }
}
