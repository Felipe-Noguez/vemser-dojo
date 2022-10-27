package br.com.vemser.devdojo.dojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DojoApplication.class, args);
	}

}