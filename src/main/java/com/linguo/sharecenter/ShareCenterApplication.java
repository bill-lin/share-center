package com.linguo.sharecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
//@EnableSwagger2
public class ShareCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareCenterApplication.class, args);
	}
}
