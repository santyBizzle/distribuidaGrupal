package com.distribuida.proxy;

import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.distribuida.dto.Customer;

public interface CustomerProxy {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> obtenerCustomer();
	
}
