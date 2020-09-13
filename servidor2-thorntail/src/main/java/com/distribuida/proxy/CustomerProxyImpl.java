package com.distribuida.proxy;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.dto.Customer;

@ApplicationScoped
public class CustomerProxyImpl {
	
	@Inject
	private CustomerProxy custproxy;
	
	public List<Customer> obtenerCustomer(){
		return custproxy.obtenerCustomer();
	}

}
