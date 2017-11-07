package com.envista.msi.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultiPartConfiguration {

	private final Logger log = LoggerFactory.getLogger(MultiPartConfiguration.class);

	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize("10000MB");
		multipartConfigFactory.setMaxRequestSize("50000MB");
		multipartConfigFactory.setFileSizeThreshold(100);
		return multipartConfigFactory.createMultipartConfig();
	}

}
