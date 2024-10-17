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
 * Interceptor to ensure only Staff can access certain pages
 * Created by: LianDa, GaoZijie
 * Created on: 10/16/2024
 */
@Component
public class StaffInterceptor implements HandlerInterceptor {
    public static final Logger LOGGER = LoggerFactory.getLogger(StaffInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        LOGGER.info("Intercepting Staff Access: " + request.getRequestURI());

        HttpSession session = request.getSession(false);
        String userType = (session != null) ? (String) session.getAttribute("userType") : null;

        if (userType == null) {
            LOGGER.warn("User not logged in or session expired.");
            response.sendRedirect("/login");
            return false;
        }

        if (request.getRequestURI().startsWith("/orangestore/Staff")) {
            if (!userType.equalsIgnoreCase("staff")) {
                LOGGER.warn("Access denied for non-staff user: ");
                response.sendRedirect("/login");
                return false;
            }
        }

        // Allow staff to proceed with the request
        return true;
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
            String userType = null;
            boolean isLoggedIn = false;

            if (session != null) {
                username = (String) session.getAttribute("username");
                userType = (String) session.getAttribute("userType");
                isLoggedIn = username != null;
            }

            // Debug log
            LOGGER.info("(PostHandle) User logged in: " + isLoggedIn);

            // Add the isLoggedIn and userType attribute to the model
            modelAndView.addObject("isLoggedIn", isLoggedIn);
            modelAndView.addObject("userType", userType);
        }
    }

}
