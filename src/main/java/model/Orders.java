package model;

import java.io.Serializable;

public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String item;
	private  Double price;
	private int customer_id;
	private String datos;
	
	public Orders(String item, Double price, int customer_id) {
		super();
		this.item = item;
		this.price = price;
		this.customer_id = customer_id;
	}
	
	



	public Orders(Long id, String item, Double price, int customer_id) {
		super();
		this.id = id;
		this.item = item;
		this.price = price;
		this.customer_id = customer_id;
	}




	public Orders(Long id, String item, Double price, int customer_id, String datos) {
		super();
		this.id = id;
		this.item = item;
		this.price = price;
		this.customer_id = customer_id;
		this.datos = datos;
	}





	public String getDatos() {
		return datos;
	}





	public void setDatos(String datos) {
		this.datos = datos;
	}





	public Double getPrice() {
		return price;
	}






	public void setPrice(Double price) {
		this.price = price;
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


	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	

}


