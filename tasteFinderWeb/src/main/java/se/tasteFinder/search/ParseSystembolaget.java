package se.tasteFinder.search;

import java.util.ArrayList;

import javax.naming.NamingException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import se.tasteFinder.ejb.DrinkBeanLocal;
import se.tasteFinder.ejb.DrinkTypeLocal;
import se.tasteFinder.representation.DrinkRepresentation;
import se.tasteFinder.utils.BeanFetcher;

public class ParseSystembolaget implements ParseInsert {

	private DrinkBeanLocal drinkBean;

	private DrinkTypeLocal drinkTypeBean;

	public ParseSystembolaget() throws NamingException {
		 drinkTypeBean = (DrinkTypeLocal) BeanFetcher.getInstance().getBean("java:/global/tasteFinderWeb/DrinkTypeBean");
		 drinkBean =  (DrinkBeanLocal) BeanFetcher.getInstance().getBean("java:/global/tasteFinderWeb/DrinkBean");
	}

	/*
	 * Returns a drink url to parse
	 */
	public ArrayList<String> getUrlsToParse(String url) throws Exception {
		ArrayList<String> urls = new ArrayList<String>();
		String urlDownload = "http://www.systembolaget.se/api/assortment/products/xml";
		urls.add(urlDownload);
		/*
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(urlDownload);
		HttpResponse response = client.execute(get);
		byte[] result = IOUtils.toByteArray(response.getEntity().getContent());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		org.w3c.dom.Document doc = dbf.newDocumentBuilder().parse(
				new ByteArrayInputStream(result));

		 * NodeList childs = doc.getElementsByTagName("Artikelid"); for(int i=0;
		 * i < 10;i++){ StringBuilder sb = new StringBuilder(); sb.append(url);
		 * Node node = childs.item(i);
		 * System.out.println(node.getTextContent());
		 * sb.append(node.getTextContent()); urls.add(sb.toString()); }
		 */
		return urls;
	}

	public void createDrinkFromParse(Document doc) throws NamingException {
		DrinkRepresentation drink = new DrinkRepresentation();
		for(Element element : doc.getElementsByTag("artikel")){
			System.out.println("Inserting artikel from systembolaget: " + element.getElementsByTag("namn").text());
			drinkTypeBean.checkByNameElseInsert(element.getElementsByTag("varugrupp").text(), "sv");
			drink.setDrinkTypeName(element.getElementsByTag("varugrupp").text());
			drink.setLanguageCode("sv");
			drink.setProducer(element.getElementsByTag("producent").text());
			if(element.getElementsByTag("namn2").text() != null)
				drink.setName(element.getElementsByTag("namn").text() + " " + element.getElementsByTag("namn2").text());
			else
				drink.setName(element.getElementsByTag("namn").text());
			drink.setAlcohol(Double.valueOf(element.getElementsByTag("alkoholhalt").text().substring(0, element.getElementsByTag("alkoholhalt").text().length()-1)));
			drink.setColor("");
			drink.setCountry(element.getElementsByTag("Ursprunglandnamn").text());
			drinkBean.checkForDuplicatesElseInsert(drink.getDrink());
		}
	}
}
