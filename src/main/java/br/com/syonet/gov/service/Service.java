package br.com.syonet.gov.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.syonet.gov.entity.City;
import br.com.syonet.gov.entity.Region;
import br.com.syonet.gov.entity.State;
import br.com.syonet.gov.persistence.Persistence;

@Path("/api")
public class Service {

	private final Persistence persistence = new Persistence();

	@POST
	@Path("/state")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createState(State state) {
		persistence.saveState(state);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("/state")
	@Produces(MediaType.APPLICATION_JSON)
	public List<State> getStates() {
		return persistence.getStates();
	}

	@PUT
	@Path("/state/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateState(@PathParam("id") int id, State state) {
		state.setId(id);
		persistence.updateState(state);
		return Response.ok().build();
	}
	
	@DELETE
    @Path("/state/{id}")
    public Response deleteState(@PathParam("id") int id) {
        persistence.deleteStateById(id);
        return Response.noContent().build();
    }

	@POST
	@Path("/city")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCity(City city) {
		persistence.saveCity(city);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("/city/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> getCities(@PathParam("id") int id) {
		return persistence.getCitiesByState(id);
	}

	@PUT
	@Path("/city/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCity(@PathParam("id") int id, City city) {
		city.setId(id);
		persistence.updateCity(city);
		return Response.ok().build();
	}
	
	@DELETE
    @Path("/city/{id}")
    public Response deleteCity(@PathParam("id") int id) {
        persistence.deleteCityById(id);
        return Response.noContent().build();
    }

	@POST
	@Path("/region")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRegion(Region region) {
		persistence.saveRegion(region);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("/region")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Region> getRegions(@PathParam("id") int id) {
		return persistence.getRegionsByCity(id);
	}

	@PUT
	@Path("/region/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRegion(@PathParam("id") int id, Region region) {
		region.setId(id);
		persistence.updateRegion(region);
		return Response.ok().build();
	}
	
	@DELETE
    @Path("/region/{id}")
    public Response deleteRegion(@PathParam("id") int id) {
        persistence.deleteRegionById(id);
        return Response.noContent().build();
    }
}
