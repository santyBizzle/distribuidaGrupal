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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.util.EntityUtils;

import model.Customer;

public class customerServiceImpl implements customerService {

	// Se define la url que apunta la api customer
	public String URL_API_CUSTOMER = "http://localhost:8182/customer";

	// Se define una variable de tipo httpCliente para realizar las peticiones rest
	private HttpClient httpClient = HttpClientBuilder.create().build();
	private Gson gson = new Gson();

	/**
	 * Funcion para insertar customers segun la URL de la api definida se obtiene la
	 * respuesta de la peticion POST y nos regresa la entidad
	 */
	@Override
	public Customer insertar(Customer c)
			throws InterruptedException, ExecutionException, TimeoutException, IOException, JAXBException {
		HttpPost post = new HttpPost(URL_API_CUSTOMER + "/insertar");

		StringEntity postingString = new StringEntity(gson.toJson(c));
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
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Customer) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
	}

	/**
	 * Funcion para actualizar customers se obtiene la respuesta de la peticion PUT
	 * que recibe una entidad customers y nos regresa un booleano que confirma la
	 * actulizacion
	 */
	@Override
	public boolean actualizar(Customer c) throws IOException, JAXBException {
		HttpPut put = new HttpPut(URL_API_CUSTOMER + "/actualizar");
		StringEntity postingString = new StringEntity(gson.toJson(c));
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
	 * Funcion para eliminar customers se obtiene la respuesta de la peticion tipo
	 * DELETE que recibe el id del customer y nos regresa un booleano que confirma
	 * la eliminacion del registro
	 */
	@Override
	public boolean borrar(int idCustomer) throws IOException, JAXBException {
		HttpDelete delete = new HttpDelete(URL_API_CUSTOMER + "/eliminar/" + idCustomer);
		delete.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(delete);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 500) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}
		return true;
	}

	/**
	 * Funcion para listar todos los customers se obtiene la respuesta de la
	 * peticion tipo GET retorna una lista seteada con los datos de todas las
	 * customers de la base
	 */
	@Override
	public List<Customer> findAllCustomers() throws IOException, JAXBException {
		HttpGet get = new HttpGet(URL_API_CUSTOMER);
		HttpResponse response = httpClient.execute(get);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		System.out.println(apiOutput);
		Type userListType = new TypeToken<ArrayList<Customer>>() {
		}.getType();
		return gson.fromJson(apiOutput, userListType);
	}

	/**
	 * Funcion para listar customer por id se obtiene la respuesta de la peticion
	 * tipo GET retorna un las customer segun el parametro id enviado
	 */
	@Override
	public Customer findCustomer(int id) throws IOException, JAXBException {
		HttpGet get = new HttpGet(URL_API_CUSTOMER + "/customer/" + id);
		HttpResponse response = httpClient.execute(get);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);
		Gson gson = new Gson();
		Customer p = gson.fromJson(apiOutput, Customer.class);
		return p;
	}

}
