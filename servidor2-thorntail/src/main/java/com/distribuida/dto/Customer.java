package com.distribuida.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //Identificador de la clase customer que usa el EM para persistir el objeto
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//permitira que la BDD genere el nuevo valor subsiguiente al insertar sin que se repita ninguno
	public Long id; //declaramos de forma publica la variable id referente al campo de la BDD
	
	public String name; //declaramos de forma publica la variable name referente al campo de la BDD
	public String surname; //declaramos de forma publica la variable surname referente al campo de la BDD
	
	public Customer() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
