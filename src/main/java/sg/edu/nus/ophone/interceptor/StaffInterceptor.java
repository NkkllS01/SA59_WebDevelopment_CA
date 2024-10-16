package sg.edu.nus.ophone.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 *
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

        // Check if the user role is not Staff
        if (userType == null || !"Staff".equals(userType)) {
            LOGGER.warn("Unauthorized access to Staff page");
            // Redirect to access denied page or home page
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
