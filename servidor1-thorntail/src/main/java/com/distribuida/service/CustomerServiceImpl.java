package com.distribuida.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.conf.PersistenceHelper;
import com.distribuida.dto.Customer;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService{

	@Inject
	PersistenceHelper helper;
	
	@Override
	public List<Customer> obtenerCustomer() {
		List<Customer> customers;
		customers = helper.getEntityManager().createNamedQuery("Customer.findAll", Customer.class).getResultList();
		return customers;
	}

	@Override
	public Customer insertCustomer(Customer customer) {
		helper.getEntityManager().persist(customer);
		return customer;
	}

	@Override
	public Customer getCustomerById(Long id) {
		List<Customer> customers;
		customers = helper.getEntityManager().createNamedQuery("Customer.getById", Customer.class).setParameter("id", id).getResultList();
		return customers.get(0);
	}

	@Override
	public Customer editCustomer(Customer customer) {
		helper.getEntityManager().merge(customer);
		return customer;
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customers = helper.getEntityManager().createNamedQuery("Customer.deleteById", Customer.class).setParameter("id", id).getResultList().get(0);
		helper.getEntityManager().remove(customers);
	}

}