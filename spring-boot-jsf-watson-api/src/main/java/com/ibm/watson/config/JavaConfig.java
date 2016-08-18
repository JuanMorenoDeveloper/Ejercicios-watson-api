package com.ibm.watson.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api-watson-keys.properties")
@ComponentScan(basePackages = { "com.ibm.watson" })
public class JavaConfig {

}
