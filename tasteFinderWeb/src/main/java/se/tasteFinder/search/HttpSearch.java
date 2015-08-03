package se.tasteFinder.search;

import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HttpSearch {
	
	ParseSystembolaget parseSystembolaget;
	
	public HttpSearch() throws NamingException{
		parseSystembolaget = new ParseSystembolaget();
	}
	
	private static final String website [] = {"http://www.systembolaget.se/dryck/"};
	
	public void search() throws Exception{
		
	    HttpClient client = new DefaultHttpClient();

	    ArrayList<String> urls = createParseUrls();
	    for(String url : urls){
	    	System.out.println("This is the url: " + url);
		    HttpGet get = new HttpGet(url);
		    HttpResponse response = client.execute(get);
	        byte[] searchResult = IOUtils.toByteArray(response.getEntity().getContent());
	        Document doc = Jsoup.parse(new String(searchResult, Charset.forName("utf-8")));
	        if(url.contains("systembolaget")){
	        	parseSystembolaget.createDrinkFromParse(doc);
	        }
	        Thread.sleep(1000);
	    }
	    
	}
	
	public ArrayList<String> createParseUrls() throws Exception{
		ArrayList<String> urls = new ArrayList<String>();
		for(int i=0; i < website.length; i++){
			if(website[i].contains("systembolaget")){
				/*
				 * Returns a drink url to parse
				 */
				urls = parseSystembolaget.getUrlsToParse(website[i]);
			}	
		}
		return urls;
	}
	
	

}
