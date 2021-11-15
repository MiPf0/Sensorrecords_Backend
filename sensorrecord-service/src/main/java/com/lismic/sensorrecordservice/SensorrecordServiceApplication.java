package com.lismic.sensorrecordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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

}