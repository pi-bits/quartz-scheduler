package com.serviceinfotech.schedulers.quartzschedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzSchedulersApplication {
	private static Logger LOG = LoggerFactory
			.getLogger(QuartzSchedulersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QuartzSchedulersApplication.class, args);
	}

}
