package com.example.demo.config;

import com.example.demo.filter.LatestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LatestFilter> registrationBean() {
        FilterRegistrationBean<LatestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LatestFilter());
        registrationBean.addUrlPatterns("/customers/*");
        return registrationBean;
    }
}
