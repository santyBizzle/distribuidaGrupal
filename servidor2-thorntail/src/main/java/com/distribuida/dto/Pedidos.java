package com.distribuida.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orders")
@NamedQueries({
	@NamedQuery(name = "Pedidos.findAll", query = "Select p from Pedidos p"),
	@NamedQuery(name = "Pedidos.getById", query = "Select p from Pedidos p where id = :id"),
	@NamedQuery(name = "Pedidos.deleteById", query = "Select p from Pedidos p where id = :id")
})

public class Pedidos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String item; //declaramos de forma publica la variable item referente al campo de la BDD
	private Double price; //declaramos de forma publica la variable price referente al campo de la BDD
	private Long customer_id; //declaramos de forma publica la variable customer_id referente al campo de la BDD que a su vez sera una llave foranea
	
	@Transient // anotacion que usaremos si necesitamos que un campo no se conserve en la BDD
	private String datos;
	//esta la varible contendra los datos de los customer asociados con una orden ya que por su 
	//referencia de tablas necesitariamos saber al implementar la consulta de Customer y Order
	
	public Pedidos() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}
	
	
}
