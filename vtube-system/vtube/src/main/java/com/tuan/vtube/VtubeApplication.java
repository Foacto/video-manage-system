package com.tuan.vtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VtubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtubeApplication.class, args);
	}

}
