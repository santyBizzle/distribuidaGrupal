package com.distribuida.service;

import java.util.List;

import com.distribuida.dto.Pedidos;

public interface PedidoService {
	
	List<Pedidos> obtenerPedidos();
	
	Pedidos insertPedidos(Pedidos ped);
	
	Pedidos getPedidoById(Long id); 

	Pedidos editPedidos(Pedidos ped);
	
	void deletePedidos(Long id); 
}
