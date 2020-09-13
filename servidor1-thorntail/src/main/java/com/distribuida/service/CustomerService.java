package com.distribuida.service;

import java.util.List;

import com.distribuida.dto.Customer;

public interface CustomerService {
	
	List<Customer> obtenerCustomer();
	
	Customer insertCustomer(Customer customer);
	
	Customer getCustomerById(Long id); 

	Customer editCustomer(Customer customer);
	
	void deleteCustomer(Long id); 

}
