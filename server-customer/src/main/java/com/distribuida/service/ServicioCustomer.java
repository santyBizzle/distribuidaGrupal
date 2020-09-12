package com.distribuida.service;

import java.util.List;

import com.distribuida.dto.Customer;

public interface ServicioCustomer {

	List<Customer> listar();
	
	public void insertar(Customer c);
	
	public void actualizar(Customer c);
	
	public void eliminar(Long id);
	
	Customer buscarPorId(Long id);
}
