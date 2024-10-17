package sg.edu.nus.ophone.config;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import sg.edu.nus.ophone.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sg.edu.nus.ophone.interceptor.StaffInterceptor;


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
    @Autowired
    private StaffInterceptor staffInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry Registry) {
        Registry.addInterceptor(LoginInterceptor).addPathPatterns(
                "/orangestore/home","/cart","/orders/**","/order_submitted",
                "/myaccount/**", "/product/**");
        Registry.addInterceptor(staffInterceptor).addPathPatterns("/orangestore/Staff");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许来自所有路径的跨域请求
        registry.addMapping("/api/**")
                // 允许指定的前端域名，如 http://localhost:3000 (ReactJS 默认运行在此端口)
                .allowedOrigins("http://localhost:3000")
                // 允许的请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的请求头
                .allowedHeaders("*")
                // 是否允许凭证（如 Cookies）跨域发送
                .allowCredentials(true)
                // 跨域请求缓存时间，减少浏览器的预检请求次数
                .maxAge(3600);
    }
}

