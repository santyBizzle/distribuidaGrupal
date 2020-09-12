package com.distribuida.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.dto.Customer;
import com.distribuida.em.PersistenceHelper;

@ApplicationScoped
public class ServicioCustomerImpl implements ServicioCustomer{

	@Inject
    PersistenceHelper helper;
	
	@Override
	public List<Customer> listar() {
		List<Customer> listaCustomer = new ArrayList<Customer>();
		try {
			listaCustomer = helper.getEntityManager().createNamedQuery("Customer.findAll").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaCustomer;
	}

	@Override
	public void insertar(Customer c) {
		try {
			helper.getEntityManager().persist(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void actualizar(Customer c) {
		try {
			helper.getEntityManager().merge(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void eliminar(Integer id) {
		try {
			helper.getEntityManager().remove(helper.getEntityManager().find(Customer.class, id));;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Customer buscarPorId(Integer id) {
		Customer customer = new Customer();
		try {
			customer = helper.getEntityManager().find(Customer.class, id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return customer;
	}

}
