package com.snack.config;

import com.snack.interceptor.AdminInterceptor;
import com.snack.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("com.snack.business.controller")
@EnableWebMvc
public class ServletConfiguration implements WebMvcConfigurer {

    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }

    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(adminInterceptor());
        interceptorRegistration.addPathPatterns("/shoppingCar/**");
        interceptorRegistration.addPathPatterns("/userCenter/**");
        interceptorRegistration.addPathPatterns("/message/**");
        InterceptorRegistration registration = registry.addInterceptor(userInterceptor());
        registration.addPathPatterns("/order/**");
        registration.addPathPatterns("/admin/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("/WEB-INF/templates/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode("HTML5");
        springResourceTemplateResolver.setCharacterEncoding("utf-8");
        return springResourceTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(springResourceTemplateResolver());
        return springTemplateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setTemplateEngine(springTemplateEngine());
        return viewResolver;
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");

        registry.addViewController("/adminLogin").setViewName("adminLogin");

        registry.addViewController("/register").setViewName("register");

        registry.addViewController("/shoppingCar").setViewName("shoppingCar");

        registry.addViewController("/order").setViewName("order");

        registry.addViewController("/userCenter").setViewName("userCenter");
    }

}
