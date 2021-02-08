package com.cg.boot.configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Nilima
 *
 */
@Configuration
@EnableSwagger2
public class ApplicationConfiguration extends WebMvcConfigurationSupport {
	/**
	 * This method define {@link Docket} bean. Its select() method returns an
	 * instance of {@link ApiInfoBuilder}, which provides a way to control the
	 * endpoints of Student group exposed by Swagger.
	 * 
	 * @return {@link Docket}
	 */
	@Bean
	public Docket version1() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cg.boot.student.controller")).paths(regex(".*" + ".*"))
				.build().enable(true).groupName("Student").apiInfo(new ApiInfoBuilder()
						.description("Student Operations").title("Education Management System").build());
	}

	/**
	 * This method define {@link Docket} bean. Its select() method returns an
	 * instance of {@link ApiInfoBuilder}, which provides a way to control the
	 * endpoints of Admin group exposed by Swagger.
	 * 
	 * @return {@link Docket}
	 */
	@Bean
	public Docket version2() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cg.boot.admin.controller")).paths(regex(".*" + ".*"))
				.build().enable(true).groupName("Admin").apiInfo(new ApiInfoBuilder().description("Admin Operations")
						.title("Education Management System").build());
	}

	/**
	 * This method specify static resource location for themes related files i.e
	 * swagger-ui.html using {@link ResourceHandlerRegistry}
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}