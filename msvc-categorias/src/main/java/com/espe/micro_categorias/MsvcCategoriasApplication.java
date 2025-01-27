package com.espe.micro_categorias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcCategoriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCategoriasApplication.class, args);
	}

}
