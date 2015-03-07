package se.tasteFinder.search;

import java.io.IOException;





import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.xml.sax.SAXException;

public class HttpSearchTest {
	
	@Test
	public void testSearch() throws ClientProtocolException, IOException, SAXException, ParserConfigurationException, InterruptedException, NamingException{
		HttpSearch httpSearch = new HttpSearch();
		httpSearch.search();
	}

}
