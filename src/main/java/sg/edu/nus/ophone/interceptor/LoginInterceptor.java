package sg.edu.nus.ophone.interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 *
 * Set permissions for users who are not logged in
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */

public class LoginInterceptor implements HandlerInterceptor{

    private static final Logger LOGGER =LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{

        LOGGER.info("Intercepting: " + request.getRequestURI());

        HttpSession session=request.getSession();

        String loggedInUser = (String) session.getAttribute("loggedInUser");

        if(loggedInUser==null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}