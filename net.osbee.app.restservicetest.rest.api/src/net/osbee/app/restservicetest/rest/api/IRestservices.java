package net.osbee.app.restservicetest.rest.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.osbee.app.restservicetest.model.dtos.PersonDto;

//The jax-rs path annotation for this service
@Path("/test")
public interface IRestservices {

	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/servicetest")
	public String getServicetest( );

	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/servicetest/{in}")
	public PersonDto  getServicetest( @PathParam("in") String in );

}
