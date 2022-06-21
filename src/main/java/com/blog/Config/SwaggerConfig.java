package com.blog.Config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.context.SecurityContext;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKey()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private List<springfox.documentation.spi.service.contexts.SecurityContext> contexts()
	{
		return Arrays.asList(springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(references()).build()) ;
	}
	
	private List<SecurityReference> references()
	{
		AuthorizationScope scopes=new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
	}
	
	@Bean
	public Docket api() {
		
		

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo())
				.securityContexts(contexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo getInfo() {
		return new ApiInfo("Blogging Backend Application", "This project developed by Jenil Patel","1.0", "Terms of Services",new Contact("jenil patel", " ","pateljenil717@gmail.com"), "License of APIS", "API LICENSE URL",Collections.emptyList());
	}

}
