package service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBException;

import model.Orders;

//Interfaz de los servicios a consumir de la API order
public interface ordersService {
	// Funcion para insertar orders
	Orders insertar(Orders o)
			throws InterruptedException, ExecutionException, TimeoutException, IOException, JAXBException;

	// Funcion Que permitira la actualizacion de entidad order
	boolean actualizar(Orders o) throws IOException, JAXBException;

	// Funcion que eliminar registro order de la base de datos segun su id
	boolean borrar(int idOrder) throws IOException, JAXBException;

	// Funcion que lista todos los orders de la base de datos
	List<Orders> findAllOrders() throws IOException, JAXBException;

	// Funcion que lista el order de la base de datos segun su id
	public Orders findOrder(int id) throws IOException, JAXBException;
}
