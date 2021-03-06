package com.dg.testbackend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact(
			"Donato Galo",
			"https://www.linkedin.com/in/dgalo88",
			"dgalo@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Test Backend API REST for Ionix",
			"Test Backend API REST for Ionix",
			"1.0",
			"urn:tos",
			DEFAULT_CONTACT,
			"Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<>());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
			new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

}
