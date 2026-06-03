package com.suz.springboot.employee;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.suz"
//		, includeFilters = @Filter(type = FilterType.REGEX, 
//			pattern = ".*Stub.*Repository"),
		//excludeFilters = @Filter(Repository.class)
	)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer{
	public AppConfig() {
		System.out.println("LOADING APP CONFIG...");
	}
	
	
//	@Override
//	public void extendMessageConverters(
//	        List<HttpMessageConverter<?>> converters) {
//	    converters.add(0, new JsonbHttpMessageConverter(
//	    ) {
//	    	public boolean canRead(java.lang.Class<?> clazz, org.springframework.http.MediaType mediaType) {
//	    		System.out.println("CAN READ JSONB");
//	    		return true;
//	    	};
//	    	
//	    	@Override
//	    	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
//	    		System.out.println("CAN WRITE JSONB");
//	    		return true;
//	    	}
//	    	
//	    	
//	    }
//	    		);
//	  }
}
