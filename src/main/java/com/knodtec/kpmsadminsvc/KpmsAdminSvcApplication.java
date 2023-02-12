package com.knodtec.kpmsadminsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class KpmsAdminSvcApplication {


	public static void main(String[] args) {
		SpringApplication.run(KpmsAdminSvcApplication.class, args); 
	}
	

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
