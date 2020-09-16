package service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBException;

import model.Customer;

//Interfaz de los servicios a consumir de la API CUSTOMER
public interface customerService {
	// Funcion para insertar Customers
	Customer insertar(Customer c)
			throws InterruptedException, ExecutionException, TimeoutException, IOException, JAXBException;

	// Funcion Que permitira la actualizacion de entidad Customer
	boolean actualizar(Customer c) throws IOException, JAXBException;

	// Funcion que eliminar registro customer de la base de datos segun su id
	boolean borrar(int idCustomer) throws IOException, JAXBException;

	// Funcion que lista todos los customers de la base de datos
	List<Customer> findAllCustomers() throws IOException, JAXBException;

	// Funcion que lista el customer de la base de datos segun su id
	public Customer findCustomer(int id) throws IOException, JAXBException;
}
