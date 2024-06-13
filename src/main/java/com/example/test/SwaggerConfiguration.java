package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration   {
     
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
        			.ignoredParameterTypes(AuthenticationPrincipal.class)
        			.select()
        			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
        			.paths(PathSelectors.any()).build()
        			.securitySchemes(Lists.newArrayList(apiKey()))
        			.securityContexts(Arrays.asList(securityContext()))        			
        			.apiInfo(getApiInfo())
        			.genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false);
        
    }
    
    private ApiKey apiKey() {
    	return new ApiKey("apiKey", "Authorization", "header");
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
            .forPaths(PathSelectors.any()).build();
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
            "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("apiKey",
            authorizationScopes));
        }
    
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("PRUEBA PRACTICA")
                .version("1.0.1")
                .description("API para prueba técnica SOAP Genesis.")
                .contact(new Contact("PRUEBA", "https://prueba.org.gt", "admin@prueba.org.gt"))
                .license("Apache License Version 2.0")
                .build();
    }
    private List<ResponseMessage> getCustomizedResponseMessages(){
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Server has crashed!!").responseModel(new ModelRef("Error")).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("You shall not pass!!").build());
        responseMessages.add(new ResponseMessageBuilder().code(504).message("You shall not pass!!").build());
        return responseMessages;
    }
}