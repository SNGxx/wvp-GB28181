package com.genersoft.iot.vmp;

import java.util.logging.LogManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WvpApplication extends LogManager {
	public static void main(String[] args) {
		SpringApplication.run(WvpApplication.class, args);
	}
}
