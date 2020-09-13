package com.distribuida.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.conf.PersistenceHelper;
import com.distribuida.dto.Customer;
import com.distribuida.dto.Pedidos;
import com.distribuida.proxy.CustomerProxy;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

	@Inject
	PersistenceHelper helper;
	
	@Inject
	private CustomerProxy serProxy;
	
	@Override
	public List<Pedidos> obtenerPedidos() {
		List<Pedidos> pedidos;
		pedidos = helper.getEntityManager().createNamedQuery("Pedidos.findAll", Pedidos.class).getResultList();
		return pedidos;
	}

	@Override
	public Pedidos insertPedidos(Pedidos ped) {
		helper.getEntityManager().persist(ped);
		return ped;
	}

	@Override
	public Pedidos getPedidoById(Long id) {
		List<Pedidos> pedidos;
		pedidos = helper.getEntityManager().createNamedQuery("Pedidos.getById", Pedidos.class).setParameter("id", id).getResultList();
		return pedidos.get(0);
	}

	@Override
	public Pedidos editPedidos(Pedidos ped) {
		helper.getEntityManager().merge(ped);
		return ped;
	}

	@Override
	public void deletePedidos(Long id) {
		Pedidos pedidos = helper.getEntityManager().createNamedQuery("Pedidos.deleteById", Pedidos.class).setParameter("id", id).getResultList().get(0);
		helper.getEntityManager().remove(pedidos);
	}

	@Override
	public List<Customer> obtenerCustomer() {
		return serProxy.obtenerCustomer();
	}

	@Override
	public List<Pedidos> getOrderCustomer() {
		List<Pedidos> listOrd = obtenerPedidos();
		List<Customer> listCus = obtenerCustomer();
		
		List<Pedidos> listOrdCust = new ArrayList<>();
		for(Pedidos pedidos : listOrd) {
			for(Customer customer : listCus) {
				if(pedidos.getCustomer_id() == customer.getId()) {
					pedidos.setDatos(customer.getName()+"-"+customer.getSurname());
					listOrdCust.add(pedidos);
				}
			}
		}
		return listOrdCust;
	}

}
