package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.example.test.config.AppProperties;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableConfigurationProperties(AppProperties.class)
@EnableTransactionManagement
public class TestApplication {

	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestApplication.class);
    }

	public static void main(String[] args) {
		System.setProperty("server.connection-timeout","600000");
        System.setProperty("server.tomcat.max-threads","6");
		SpringApplication.run(TestApplication.class, args);
	}
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
	}

}
