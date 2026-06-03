package com.suz.springboot.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;

//@EnableAutoConfiguration
@SpringBootApplication(exclude = {CassandraDataAutoConfiguration.class, 
		CassandraReactiveDataAutoConfiguration.class} )
public class SpringBootJpaEmployeeModelApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = 
				SpringApplication.run(
						SpringBootJpaEmployeeModelApplication.class, args);
		ApplicationStartup startup = new ApplicationStartup() {
			
			@Override
			public StartupStep start(String name) {
				System.out.println("==== MY APPLICATION STARTED ====");
				return null;
			}
		};
		appContext.setApplicationStartup(startup );
	}

}
