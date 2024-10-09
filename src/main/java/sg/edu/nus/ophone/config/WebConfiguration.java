package sg.edu.nus.ophone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sg.edu.nus.ophone.interceptor.LoginInterceptor;

//code by Team3.Chen Sirui
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //need to fill in the excluded urls
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/orangestore/product/*");
    }
}
