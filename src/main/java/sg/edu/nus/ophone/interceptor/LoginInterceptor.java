package sg.edu.nus.ophone.interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


/**
 *
 * Set permissions for users who are not logged in
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws IOException {

        LOGGER.info("Intercepting: " + request.getRequestURI());

        HttpSession session=request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        
        // Check if user is not logged in
        if (username == null) {
        	System.out.println("username is null");
            // Check if the request is already for the home page
            if (request.getRequestURI().equals("/orangestore/home")) {
                return false;
            }
            // Redirect to the home page for other pages
            response.sendRedirect("/login");
            return false; // Prevent further handling of the request
        }
        return true; // Continue with the request if logged in
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            // Check if user is logged in
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            boolean isLoggedIn = username != null;
            // Debug log
            LOGGER.info("User logged in: " + isLoggedIn);

            // Add the isLoggedIn attribute to the model
            modelAndView.addObject("isLoggedIn", isLoggedIn);
        }
    }
}