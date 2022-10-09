package com.example.demo;

import com.example.demo.Controllers.UserController;
import com.example.demo.Models.User;
import com.example.demo.Repositories.HackNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Autowired
	private HackNewsRepository hackNewsRepository;
	@GetMapping
	public List<User> hello(){
		return hackNewsRepository.findAll();
	}

}
