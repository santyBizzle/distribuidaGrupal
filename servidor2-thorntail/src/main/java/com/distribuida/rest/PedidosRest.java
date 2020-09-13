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

import com.distribuida.dto.Pedidos;
import com.distribuida.service.PedidoService;

@Path("/orders")
@ApplicationScoped
public class PedidosRest {
	
	@Inject
	private PedidoService servicio;
	
	@GET
	@Path("/OrderCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedidos> getOrderCustomer(){ //metodo que devuelve los datos de nombre y apellido de las tablas cruzadas
		List<Pedidos> OrdCust = servicio.getOrderCustomer();
		return OrdCust;
	}
		
	@GET //anotacion GET para obtener datos del servicio
	@Produces(MediaType.APPLICATION_JSON) //formato de datos que producira el servidor
	public List<Pedidos> obtenerPedidos(){
		List<Pedidos> listAll = servicio.obtenerPedidos(); //llamada al metodo que nos lista los datos de los pedidos implementado en el CRUD
		return listAll;	//retorna dicha lista obtenida
	}
	
	@POST //anotacion POST para enviar datos
	@Transactional
	@Path("/insertar") //path creado para ingresar un nuevo pedido
	@Consumes(MediaType.APPLICATION_JSON) //formato de datos que consumira el servidor
	@Produces(MediaType.APPLICATION_JSON) //formato de datos que producira el servidor
	public Pedidos insertPedidos(Pedidos ped) {
		return servicio.insertPedidos(ped); //metodo con el cual se insertara los datos proporcionados del pedido
	}
	
	@GET //anotacion GET para obtener datos del servicio
	@Path("/order/{id}") //definimos un nuevo path que nos proporciona los datos del pedido buscados por su id 
	@Produces(MediaType.APPLICATION_JSON) //formato de datos que producira el servidor
	public Pedidos getPedidoById(@PathParam ("id") Long id) {
		Pedidos ped = servicio.getPedidoById(id); //llamada al metodo que nos devuelve los datos del pedido buscado por su id 
		return ped; //retorna los datos del pedido buscado
	}
	
	@PUT //anotacion PUT para modificar datos del pedido
	@Transactional //Anotacion transactional ya que se va a modificar la BDD 
	@Path("/actualizar") //path creado para ediatr los datos de un pedido
	@Consumes(MediaType.APPLICATION_JSON) //formato de datos que consumira el servidor
	@Produces(MediaType.APPLICATION_JSON) //formato de datos que producira el servidor
	public Pedidos editPedidos(Pedidos ped) {
		return servicio.editPedidos(ped); //metodo que modificara los datos del pedido segun lo indicado en un JSON 
	}
	
	@DELETE //anotacion DELETE que eliminara datos de la BDD por debajo
	@Transactional //Anotacion transactional ya que se va a modificar la BDD
	@Path("/eliminar/{id}") //path creado para eliminar un pedido por su id
	public void deletePedidos(@PathParam ("id") Long id) {
		servicio.deletePedidos(id); //metodo que eliminara el pedido de la BDD proporcionado	
	}

}
