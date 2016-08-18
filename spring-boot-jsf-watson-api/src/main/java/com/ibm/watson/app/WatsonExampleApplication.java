package com.ibm.watson.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibm.watson.config.JavaConfig;
import com.ibm.watson.config.SecurityConfig;

@SpringBootApplication
public class WatsonExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Arrays.asList(WatsonExampleApplication.class, SecurityConfig.class, JavaConfig.class).toArray(), args);
	}

}
