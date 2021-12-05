package com.lismic.sensorrecordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sensorrecord Service for Sensorrecord Application
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class SensorrecordServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(SensorrecordServiceApplication.class);

    public static void main(String[] args) {
        SpringBootUtil.setRandomPort(5000, 5500);
        ApplicationContext ctx = SpringApplication.run(SensorrecordServiceApplication.class, args);
        logger.info("Application " + ctx.getApplicationName() + " started");
    }

}