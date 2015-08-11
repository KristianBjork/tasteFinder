package se.tasteFinder.ejb;

import java.util.List;

import javax.ejb.Local;

import se.tasteFinder.representation.SearchRepresentation;

@Local
public interface SearchBeanLocal {
	
	public List<SearchRepresentation> search(String query);
	
}
