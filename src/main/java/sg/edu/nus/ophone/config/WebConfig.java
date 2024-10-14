package sg.edu.nus.ophone.config;
import org.springframework.stereotype.Component;
import sg.edu.nus.ophone.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * Interceptor register(which pages cannot be access when the user are not log in)
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */

@Component
public class WebConfig implements WebMvcConfigurer{																			

    @Autowired
    private LoginInterceptor LoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry Registry) {
        Registry.addInterceptor(LoginInterceptor).addPathPatterns(
                "/cart","/orders/**","/order_submitted",
                "/myaccount/**", "/product/**");
    }
}
