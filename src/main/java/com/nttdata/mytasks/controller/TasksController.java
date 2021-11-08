package com.nttdata.mytasks.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.mytasks.repository.TasksRepository;
import com.nttdata.mytasks.service.TasksService;
import com.nttdata.mytasks.controller.AddResponse;

@RestController
public class TasksController {
	
	@Autowired
	TasksRepository repository;
	//AtomicLong counter=new AtomicLong();
	@Autowired
	TasksService taskService;
	
	private static final Logger logger= LoggerFactory.getLogger(TasksController.class);
	
	@PostMapping("/addTask")
	public ResponseEntity addTaskImplementation(@RequestBody Tasks task)
	{
		AddResponse ad=new AddResponse();
		String id=task.getId();
		
		if(!taskService.checkTaskAlreadyExists(id))
		{
			logger.info("La tarea no existe, creando una");
			task.setId(task.getId());
			task.setDescription(task.getDescription());
			task.setTestado(task.getTestado());
			repository.save(task);
			HttpHeaders headers=new HttpHeaders();//headers para que nos salga en postman en los headers
			headers.add("unique", id);
			ad.setMsg("Se ha añadido una nueva tarea");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad,headers,HttpStatus.CREATED);
		}
		else
		{
			logger.info("Esa tarea ya está escrita, skipeamos");
			ad.setMsg("Esa tarea ya se ha añadido");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad,HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/allTasks")
	public List<Tasks>seeAllTasksImplementation()
	{
		return repository.findAll();
	}

	@DeleteMapping("/deleteTask")
	public ResponseEntity<String> deleteTaskById(@RequestBody Tasks task)
	{
		Tasks taskdelete=repository.findById(task.getId()).get();
		repository.delete(taskdelete);
		logger.info("Task is deleted");
		return new ResponseEntity<>("Task is deleted",HttpStatus.CREATED);
	}
	
	@PutMapping("/updateTaskStatus/{id}")
	public ResponseEntity<Tasks> updateTaskStatus(@PathVariable(value="id")String id, @RequestBody Tasks task)
	{
		Tasks existingtask=repository.findById(id).get();
		
		existingtask.setDescription(existingtask.getDescription());
		existingtask.setId(existingtask.getId());//si queremos que solo cambie una cosa hemos de envia rlos valores ya existentes
		existingtask.setTestado(task.getTestado());
		repository.save(existingtask);
		return new ResponseEntity<Tasks>(existingtask,HttpStatus.OK);
	}
}
