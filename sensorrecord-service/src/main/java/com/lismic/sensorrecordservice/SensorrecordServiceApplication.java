package com.lismic.sensorrecordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Sensorrecord Service for Sensorrecord Application
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class SensorrecordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorrecordServiceApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }

    }

    /*@EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        }

    }*/
}