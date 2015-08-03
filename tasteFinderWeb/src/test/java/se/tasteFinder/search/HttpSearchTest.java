package se.tasteFinder.search;

import org.junit.Test;

public class HttpSearchTest {
	
	@Test
	public void testSearch() throws Exception{
		HttpSearch httpSearch = new HttpSearch();
		httpSearch.search();
	}

}
