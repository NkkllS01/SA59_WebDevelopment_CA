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

        HttpSession session=request.getSession();
        if (session != null) {
            LOGGER.info("(PreHandle) Session exists with ID: " + session.getId());
            LOGGER.info("(Prehandle) Session Username in interceptor: " + session.getAttribute("username"));
        } else {
            LOGGER.info("(Prehandle) Session is null");
        }

        String username = (session != null) ? (String) session.getAttribute("username") : null;
        String requestURI = request.getRequestURI();

        // Allow access to the home page whether logged in or not
        if (requestURI.equals("/orangestore/home")) {
            return true;  // Allow access to /orangestore/home for everyone
        }
        
        // Check if user is not logged in
        if (username == null) {
        	System.out.println("username is null");
            LOGGER.info("User not logged in, redirecting to login page for URL: " + requestURI);
            // Redirect to the home page for other pages
            response.sendRedirect("/login");
            return false; // Prevent further handling of the request
        }
        // If user is logged in, allow the request to proceed
        LOGGER.info("User logged in: " + username + " for URL: " + requestURI);
        return true; // Continue with the request if logged in
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            // Check if user is logged in
            HttpSession session = request.getSession(false);
            if (session != null) {
                LOGGER.info("(PostHandle) Session exists with ID: " + session.getId());
                LOGGER.info("(PostHandle) Session Username in interceptor: " + session.getAttribute("username"));
            } else {
                LOGGER.info("(PostHandle) Session is null");
            }
            String username = null;
            boolean isLoggedIn = false;

            if (session != null) {
                username = (String) session.getAttribute("username");
                isLoggedIn = username != null;
            }

            // Debug log
            LOGGER.info("(PostHandle) User logged in: " + isLoggedIn);

            // Add the isLoggedIn attribute to the model
            modelAndView.addObject("isLoggedIn", isLoggedIn);
        }
    }
}