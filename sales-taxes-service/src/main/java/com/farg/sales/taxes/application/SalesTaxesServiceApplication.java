package com.farg.sales.taxes.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class Application Loader
 * @author fernandoreb
 *
 */
@ComponentScan("com.farg.*")
@EnableJpaRepositories("com.farg.sales.taxes.repository")
@EntityScan("com.farg.sales.taxes.model")
@SpringBootApplication
public class SalesTaxesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesTaxesServiceApplication.class, args);
	}
	/**
	 * Add cors to enable requests from angular app.
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

}
