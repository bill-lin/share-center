package com.linguo.sharecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
//@EnableSwagger2
public class ShareCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareCenterApplication.class, args);
	}
}
