package service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBException;

import model.Orders;

public interface ordersService {
	Orders insertar(Orders o) throws InterruptedException, ExecutionException, TimeoutException, IOException, JAXBException;
	boolean actualizar(Orders o) throws IOException, JAXBException;
	boolean borrar(int idOrder) throws IOException, JAXBException;
	  List<Orders> findAllOrders() throws IOException, JAXBException;
	  public Orders findOrder(int id) throws IOException, JAXBException ;
}
