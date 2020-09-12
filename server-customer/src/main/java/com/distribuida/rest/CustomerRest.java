package com.distribuida.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.distribuida.dto.Customer;
import com.distribuida.service.ServicioCustomer;

@Path("/customers")
@ApplicationScoped
public class CustomerRest {
	
	@Inject
	private ServicioCustomer servicio;	
	
	@GET @Path("/")
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Customer> find(){
		return servicio.listar();
	}
	
	@GET @Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Customer findById(@PathParam("id") Integer id){
		return servicio.buscarPorId(id);
	}
	
	@POST @Path("/insertar")
	@Transactional
	@Produces(value=MediaType.APPLICATION_JSON)
	public void insert(Customer c){
		servicio.insertar(c);
	}
	
	@PUT @Path("/actualizar")
	@Transactional
	@Produces(value=MediaType.APPLICATION_JSON)
	public void update(Customer c){
		servicio.actualizar(c);
	}
	
	@DELETE @Path("/eliminar/{id}")
	@Transactional
	public void delete(@PathParam("id") Integer id){
		servicio.eliminar(id);
	}
	
}
