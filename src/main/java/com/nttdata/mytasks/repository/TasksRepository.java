package com.nttdata.mytasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.mytasks.controller.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, String> {

}
