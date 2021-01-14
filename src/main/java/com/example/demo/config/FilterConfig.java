package com.example.demo.config;

import com.example.demo.filter.FirstFilter;
import com.example.demo.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<FirstFilter> customerBean() {
        FilterRegistrationBean<FirstFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FirstFilter());
        registrationBean.addUrlPatterns("/customers/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> movieBean() {
        FilterRegistrationBean<SecondFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecondFilter());
        registrationBean.addUrlPatterns("/movies/*");
        return registrationBean;
    }
}
