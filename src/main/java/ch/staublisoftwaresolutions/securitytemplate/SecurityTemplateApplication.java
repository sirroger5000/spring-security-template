package ch.staublisoftwaresolutions.securitytemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SecurityTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityTemplateApplication.class, args);
	}

}
