package com.mindskip.xzs.configuration.spring.mvc;

import com.mindskip.xzs.configuration.property.SystemConfig;
import com.mindskip.xzs.configuration.spring.wx.TokenHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;


@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final TokenHandlerInterceptor tokenHandlerInterceptor;
    private final SystemConfig systemConfig;

    /**
     * Instantiates a new Web mvc configuration.
     *
     * @param tokenHandlerInterceptor the token handler interceptor
     * @param systemConfig            the system config
     */
    @Autowired
    public WebMvcConfiguration(TokenHandlerInterceptor tokenHandlerInterceptor, SystemConfig systemConfig) {
        this.tokenHandlerInterceptor = tokenHandlerInterceptor;
        this.systemConfig = systemConfig;
    }
    //添加视图管理器的重定向规则，设定url路径导向的页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/student/index.html");
        registry.addRedirectViewController("/student", "/student/index.html");
        registry.addRedirectViewController("/admin", "/admin/index.html");
    }

    //处理静态资源处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                //设置静态资源的缓存周期
                .setCachePeriod(31556926);
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> securityIgnoreUrls = systemConfig.getWx().getSecurityIgnoreUrls();
        String[] ignores = new String[securityIgnoreUrls.size()];
        registry.addInterceptor(tokenHandlerInterceptor)
                .addPathPatterns("/api/wx/**")
                .excludePathPatterns(securityIgnoreUrls.toArray(ignores));
        super.addInterceptors(registry);
    }

    //配置全局的跨域资源共享（CORS）设置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }

}
