package com.distribuida.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.conf.PersistenceHelper;
import com.distribuida.dto.Pedidos;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService  {

	@Inject
	PersistenceHelper helper;
	
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

}
