package edu.kh.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class DemoProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoProject3Application.class, args);
	}

}
