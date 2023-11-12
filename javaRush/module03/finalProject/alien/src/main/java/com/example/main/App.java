package com.example.main;

import com.example.main.alien.commands.Command;
import com.example.main.alien.commands.service.CommandService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		try {
			Command command = CommandService.getCommandByName("next_question");
			System.out.println("success");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		SpringApplication.run(App.class, args);
	}

}
