package com.hmtool.sso.resource.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({"com.hmtool.sso.resource.rest"})
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
    
}
