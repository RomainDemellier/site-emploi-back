package com.oc.projets.siteemploiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
}
)*/
@SpringBootApplication
public class SiteEmploiBackApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SiteEmploiBackApplication.class, args);
	}

}
