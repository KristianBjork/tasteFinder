package se.tasteFinder.search;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import se.tasteFinder.ejb.DrinkBeanRemote;
import se.tasteFinder.ejb.DrinkTypeRemote;
import se.tasteFinder.representation.DrinkRepresentation;


public class HttpSearch {
	
	private DrinkBeanRemote drinkBean;
	private DrinkTypeRemote drinkTypeBean;

	public HttpSearch() throws NamingException{
		drinkBean = (DrinkBeanRemote) new InitialContext().lookup("DrinkBean");
		drinkTypeBean = (DrinkTypeRemote) new InitialContext().lookup("DrinkTypeBean");

	}
	
	private static final String website [] = {"http://www.systembolaget.se/Sok-dryck/Dryck/?artikelId="};
	private HashMap<String,String> properties = new HashMap<String, String>();


	
	public void search() throws ClientProtocolException, IOException, SAXException, ParserConfigurationException, InterruptedException, NamingException{
		
	    HttpClient client = new DefaultHttpClient();
	    ArrayList<String> urls = createUrls();
	    for(String url: urls){
	    	System.out.println("This is the url: " + url);
		    HttpGet get = new HttpGet(url);
		    HttpResponse response = client.execute(get);
	        byte[] searchResult = IOUtils.toByteArray(response.getEntity().getContent());
	        Document doc = Jsoup.parse(new String(searchResult, Charset.forName("utf-8")));
	        if(url.contains("systembolaget")){
	        	handlebeverageContentSystemBolaget(doc);
	        }
	        Thread.sleep(1000);
	    }

	    
	}
	
	public void handlebeverageContentSystemBolaget(Document doc) throws NamingException{
		DrinkRepresentation drink = new DrinkRepresentation();
		Element beverageContent = doc.select("div.beverageContent").first();
		checkIfDrinkTypeExistsElseInsert(beverageContent.select("span.character").first().text());
		drink.setDrinkTypeName(beverageContent.select("span.character").first().text());
		drink.setProducer(beverageContent.select("span.produktnamnfet").first().text());
		drink.setName(beverageContent.select("span.produktnamnmager").first().text());
		Elements bevergeFacts = beverageContent.select("ul.beverageFacts");
		for(int i=0; i< bevergeFacts.size() ; i++){
			addBevergeFacts(i, bevergeFacts);
		}
		drink.setAlcohol(Integer.valueOf(properties.get("Alkoholhalt").split(" ")[0]));
		drink.setColor(properties.get("Färg"));
		drinkBean.createDrink(drink.getDrink());
		
		/*
		 * Implement this?
		 */
		Element bevergeClocks = bevergeFacts.select("ul.beverageClocks").first();
		/*
		 * Implement this?
		 */
		Element bevergeTaste = bevergeFacts.select("ul.beverageTaste").first();
		
	}
	

	private void checkIfDrinkTypeExistsElseInsert(String drinkType) {
		drinkTypeBean.checkByNameElseInsert(drinkType);
	}

	public void addBevergeFacts(int n, Elements bevergeContent){
		Element bevergeFacts = bevergeContent.select("ul.beverageFacts").get(n);
		for(int i = 0; i < bevergeFacts.select("li").size(); i++){
			System.out.println(bevergeFacts.select("li").get(i).select("span").text());
			System.out.println(bevergeFacts.select("li").get(i).select("strong").text());
			properties.put(bevergeFacts.select("li").get(i).select("span").text(), bevergeFacts.select("li").get(i).select("strong").text());
		}
	}
	
	public ArrayList<String> createUrls() throws ClientProtocolException, IOException, SAXException, ParserConfigurationException{
		ArrayList<String> urls = new ArrayList<String>();
		for(int i=0; i < website.length; i++){
			if(website[i].contains("systembolaget")){
				urls = getSearchArgSystembolagetArtikelNr(website[i]);
			}	
		}
		return urls;
	}
	
	public ArrayList<String> getSearchArgSystembolagetArtikelNr(String url) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException{
		ArrayList<String> urls = new ArrayList<String>();
		String urlDownload = "http://www.systembolaget.se/Assortment.aspx?Format=Xml";

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(urlDownload);
		HttpResponse response = client.execute(get);
	    byte[] result = IOUtils.toByteArray(response.getEntity().getContent());
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	    org.w3c.dom.Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(result));
	    NodeList childs = doc.getElementsByTagName("Artikelid");
	    for(int i=0; i < 10;i++){
			StringBuilder sb = new StringBuilder();
	    	sb.append(url);
	    	Node node = childs.item(i);
	    	System.out.println(node.getTextContent());
	    	sb.append(node.getTextContent());
	    	urls.add(sb.toString());
	    }
		return urls;
	}

}
