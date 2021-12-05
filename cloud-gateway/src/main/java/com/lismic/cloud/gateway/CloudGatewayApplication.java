package com.lismic.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

/**
 * API Gateway Server for Sensorrecord Application
 */
@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin
public class CloudGatewayApplication extends CorsConfiguration {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    /**
     * Defining routes for API Gateway
     * @param rlb route locator builder
     * @return changed route locator
     */
    @Bean
    public RouteLocator apiGatewayRouteLocator(RouteLocatorBuilder rlb) {
        return rlb.routes()
                .route(p -> p.path("/sensorrecords/**")
                        .uri("lb://SENSORRECORD-SERVICE"))
                .route(p -> p.path("/test/**")
                        .uri("lb://TEST-SERVICE"))
                //.route(p -> p.path("/swagger-ui/**,/swagger-resources/**,/swagger-ui.html,/v2/api-docs,/webjars/**,/view/**")
                //        .uri("lb://swagger-ui"))
                .build();
    }
}