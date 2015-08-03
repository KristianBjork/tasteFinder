package se.tasteFinder.resources;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.tasteFinder.search.HttpSearch;

@Path("/searchAndInsert")
public class Search {
	
	@GET
	@Path("/start")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response search(){
		HttpSearch httpSearch;
		try {
			httpSearch = new HttpSearch();
			httpSearch.search();
		} catch (NamingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
}
