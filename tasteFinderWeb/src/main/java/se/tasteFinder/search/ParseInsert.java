package se.tasteFinder.search;

import java.util.ArrayList;

import javax.naming.NamingException;

import org.jsoup.nodes.Document;

public interface ParseInsert {
	
	public ArrayList<String> getUrlsToParse(String url) throws Exception;
	
	public void createDrinkFromParse(Document doc) throws NamingException;

}
