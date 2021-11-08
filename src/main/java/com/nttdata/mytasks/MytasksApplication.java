package com.nttdata.mytasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdata.mytasks.repository.TasksRepository;

@SpringBootApplication
public class MytasksApplication {

	@Autowired
	TasksRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(MytasksApplication.class, args);
	}

}
