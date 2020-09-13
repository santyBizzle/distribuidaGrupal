package com.distribuida.proxyproducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import com.distribuida.proxy.CustomerProxy;

@ApplicationScoped
public class ProxyCustomerProducer {
		
	@Produces //Esta anotación indica a JAX-RS que valor tiene la cabecera HTTP  
	@ApplicationScoped //ambito de aplicacion
	public CustomerProxy getProxy() throws URISyntaxException {
		
	URI apiUri = new URI("http://localhost:8182/customer");
	
	CustomerProxy proxy = RestClientBuilder.newBuilder()
			.baseUri(apiUri)
			.build(CustomerProxy.class);
	return proxy;
	}
}
