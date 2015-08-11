package se.tasteFinder.resources;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.tasteFinder.ejb.SearchBeanLocal;
import se.tasteFinder.representation.SearchRepresentation;
import se.tasteFinder.search.HttpSearch;
import se.tasteFinder.utils.BeanFetcher;

@Path("/searchAndInsert")
public class Search {
	
	@GET
	@Path("/insert")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response insert(){
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
	
	@GET
	@Path("search/{query}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<SearchRepresentation> search(@PathParam("query") String query){
		List<SearchRepresentation> result = null;
		try {
			result = ((SearchBeanLocal) BeanFetcher.getInstance().getBean("java:/global/tasteFinderWeb/SearchBean")).search(query);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if(result != null){
			return result;
		}
		/*TODO:Add fault handling*/
		return null;
	}
}
