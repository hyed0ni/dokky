package com.mcp.semi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mcp.semi.*")
@EnableScheduling
public class SemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemiApplication.class, args);
	}

}
