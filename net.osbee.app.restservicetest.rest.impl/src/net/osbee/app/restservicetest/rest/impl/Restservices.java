package net.osbee.app.restservicetest.rest.impl;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;

import net.osbee.app.restservicetest.model.dtos.PersonDto;
import net.osbee.app.restservicetest.rest.api.IRestservices;


//The jax-rs path annotation for this service
@Path("/service")
//The OSGi DS (declarative services) component annotation. 
@Component(immediate = true, property = { "service.exported.interfaces=*", 
		"service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs","osgi.basic.timeout=5000000",
		//"ecf.jaxrs.jersey.server.pathPrefix=/helpdeskservice",
		"ecf.jaxrs.jersey.server.alias=osbee"
		})
public class Restservices implements IRestservices {
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(Restservices.class.getName());
	
	public Restservices() {
		log.debug("{} constructed ....",Restservices.class.getName());
	}
	
	public static final String CLICHED_MESSAGE = "Hello World!";
	 
	@Override
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/helloworld")
	public String getServicetest( ) {
		log.debug("{} getServicetest called ....",Restservices.class.getName());
		return CLICHED_MESSAGE;
	}

	@Override
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPerson/{in}")
	public PersonDto getServicetest( @PathParam("in") String in ) {
		
		log.debug("{} getServicetest( {} ) called ....",Restservices.class.getName(), in);
		PersonDto b = new PersonDto();
		b.setLastName(in);
		
		return b;

	}

}
