package org.uniovi.i3a.agents_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class Service {
    public static void main(String[] args) {
	SpringApplication.run(Service.class, args);
    }
}
