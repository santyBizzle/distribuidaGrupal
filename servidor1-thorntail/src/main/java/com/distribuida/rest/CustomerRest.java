package com.distribuida.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.distribuida.dto.Customer;
import com.distribuida.service.CustomerService;

@Path("/customer")
@ApplicationScoped
public class CustomerRest {
	
	@Inject
	private CustomerService servicio;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> obtenerCustomer(){
		List<Customer> listAll = servicio.obtenerCustomer(); //llamada al metodo que nos lista todos los customer implementado en el CRUD
		return listAll; //retorna dicha lista obtenida
	}
	
	@POST //anotacion POST para enviar datos
	@Transactional
	@Path("/insertar") //path creado para ingresar un nuevo pedido
	@Consumes(MediaType.APPLICATION_JSON) //formato de datos que consumira el servidor
	@Produces(MediaType.APPLICATION_JSON) //formato de datos que producira el servidor
	public Customer insertCustomer(Customer customer) {
		return servicio.insertCustomer(customer);		
	}
	
	@GET //anotacion GET para obtener datos del servicio
	@Path("/customer/{id}") //definimos un nuevo path que nos proporciona los datos del pedido buscados por su id 
	@Produces(MediaType.APPLICATION_JSON) //formato de datos que producira el servidor
	public Customer getCustomerById(@PathParam("id") Long id) {
		Customer cust = servicio.getCustomerById(id); //llamada al metodo que nos devuelve los datos del customer buscado por su id 
		return cust; //retorna los datos del customer buscado
	}
	
	@PUT //anotacion PUT para modificar datos del customer
	@Transactional //Anotacion transactional ya que se va a modificar la BDD
	@Path("/actualizar") //path creado para editar un customer
	@Consumes(MediaType.APPLICATION_JSON) //formato de datos que consumira el servidor
	@Produces(MediaType.APPLICATION_JSON)  //formato de datos que producira el servidor
	public Customer editCustomer(Customer customer) {	
		return servicio.editCustomer(customer); //metodo que modificara los datos del customer segun lo indicado en un JSON 
	}
	
	@DELETE //anotacion DELETE que eliminara datos de la BDD por debajo
	@Transactional //Anotacion transactional ya que se va a modificar la BDD
	@Path("/eliminar/{id}") //path creado para eliminar un customer por su id
	public void deleteCustomer(@PathParam ("id") Long id) {
		servicio.deleteCustomer(id); //metodo que eliminara el customer de la BDD proporcionado	
	}
}