package service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBException;

import model.Customer;

public interface customerService {
	Customer insertar(Customer c) throws InterruptedException, ExecutionException, TimeoutException, IOException, JAXBException;
	boolean actualizar(Customer c) throws IOException, JAXBException;
	boolean borrar(int idCustomer) throws IOException, JAXBException;
	  List<Customer> findAllCustomers() throws IOException, JAXBException;
	  public Customer findCustomer(int id) throws IOException, JAXBException ;
}
