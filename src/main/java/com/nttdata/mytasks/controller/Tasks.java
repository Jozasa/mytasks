package com.nttdata.mytasks.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//TaskEntity
@Entity
@Table(name="tasks")
public class Tasks {
	@Column(name="description")
	private String description;
	@Id
	@Column(name="id")
	private String id;
	@Column(name="testado")
	private String testado;
	public String getTestado() {
		return testado;
	}
	public void setTestado(String testado) {
		this.testado = testado;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
