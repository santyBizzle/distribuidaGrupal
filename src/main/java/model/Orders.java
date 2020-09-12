package model;

import java.io.Serializable;

public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String item;
	private  int precio;
	private int customer_id;

	public Orders(String item, int precio, int customer_id) {
		super();
		this.item = item;
		this.precio = precio;
		this.customer_id = customer_id;
	}



	public Orders(Long id, String item, int precio, int customer_id) {
		super();
		this.id = id;
		this.item = item;
		this.precio = precio;
		this.customer_id = customer_id;
	}



	public Orders() {
		super();
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	

}


