package com.oracle.springapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot application main class. It uses JdbcTemplate class which
 * internally uses UCP for connection check-outs and check-ins.
 *
 */
//@DependsOn("springContextUtil")
@SpringBootApplication
public class OracleJdbcApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OracleJdbcApplication.class, args);
	}
}
