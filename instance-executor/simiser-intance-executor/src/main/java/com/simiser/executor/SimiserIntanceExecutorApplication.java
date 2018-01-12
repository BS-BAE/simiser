package com.simiser.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.simiser.executor")
public class SimiserIntanceExecutorApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimiserIntanceExecutorApplication.class, args);
	}
}
