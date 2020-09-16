package service;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.util.EntityUtils;

import model.Orders;

public class ordersServiceImpl implements ordersService {
	// Se define la url que apunta la api customer
	public String API_ORDERS = "http://localhost:8181/orders";
	// Se define una variable de tipo httpCliente para realizar las peticiones rest
	private HttpClient httpClient = HttpClientBuilder.create().build();
	private Gson gson = new Gson();

	/**
	 * Funcion para insertar orders segun la URL de la api definida se obtiene la
	 * respuesta de la peticion POST y nos regresa la entidad
	 */

	@Override
	public Orders insertar(Orders o) throws IOException, JAXBException {
		HttpPost post = new HttpPost(API_ORDERS + "/insertar");
		StringEntity postingString = new StringEntity(gson.toJson(o));
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(post);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 500) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}
		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		System.out.println(apiOutput);
		JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Orders) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
	}

	/**
	 * Funcion para actualizar orders se obtiene la respuesta de la peticion PUT que
	 * recibe una entidad Orders y nos regresa un booleano que confirma la
	 * actulizacion
	 */
	@Override
	public boolean actualizar(Orders o) throws IOException, JAXBException {

		HttpPut put = new HttpPut(API_ORDERS + "/actualizar");
		StringEntity postingString = new StringEntity(gson.toJson(o));
		put.setEntity(postingString);
		put.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(put);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 500) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}
		return true;
	}

	/**
	 * Funcion para eliminar orders se obtiene la respuesta de la peticion tipo
	 * DELETE que recibe el id del order y nos regresa un booleano que confirma la
	 * eliminacion del registro
	 */

	@Override
	public boolean borrar(int idOrder) throws IOException, JAXBException {
		HttpDelete delete = new HttpDelete(API_ORDERS + "/eliminar/" + idOrder);
		delete.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(delete);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 500) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);

		}
		return true;
	}

	/**
	 * Funcion para listar todos los orders se obtiene la respuesta de la peticion
	 * tipo GET retorna una lista seteada con los datos de todas las orders de la
	 * base
	 */

	@Override
	public List<Orders> findAllOrders() throws IOException, JAXBException {
		HttpGet get = new HttpGet(API_ORDERS + "/OrderCustomer");
		HttpResponse response = httpClient.execute(get);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		System.out.println(apiOutput);
		Type userListType = new TypeToken<ArrayList<Orders>>() {
		}.getType();
		return gson.fromJson(apiOutput, userListType);
	}

	/**
	 * Funcion para listar order por id se obtiene la respuesta de la peticion tipo
	 * GET retorna un las order segun el parametro id enviado
	 */

	@Override
	public Orders findOrder(int id) throws IOException, JAXBException {
		HttpGet get = new HttpGet(API_ORDERS + "/order/" + id);
		HttpResponse response = httpClient.execute(get);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);
		Gson gson = new Gson();
		Orders o = gson.fromJson(apiOutput, Orders.class);
		return o;
	}

}
