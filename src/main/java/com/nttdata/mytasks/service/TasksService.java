package com.nttdata.mytasks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.mytasks.controller.Tasks;
import com.nttdata.mytasks.repository.TasksRepository;

@Service
public class TasksService
{
	@Autowired
	TasksRepository repository;
	
	public boolean checkTaskAlreadyExists(String id)
	{
		Optional<Tasks> task=repository.findById(id);
				if(task.isPresent())
				{
					return true;
				}
				else
				{
					return false;
				}
	}

}
