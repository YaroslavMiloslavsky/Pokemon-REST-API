

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrapper {
	private final String WEBSITE = "https://pokemondb.net/pokedex/national";
	private ArrayList<String> pokemonNamesArray;
	private Map<String,Element> map;
	
	public WebScrapper() {
		pokemonNamesArray = new ArrayList<String>();
		scanItems();
	}
	
	private void scanItems() {
		Document doc = null;
		try {
			doc = Jsoup.connect(WEBSITE).get();
			
		} catch(MalformedURLException e) {
			//TODO 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements elements = doc.getElementsByClass("ent-name");
		for(Element el: elements) {
			String link = el.attributes().get("href");
			int nameIndex = link.indexOf("x");
			link = link.substring(nameIndex+2, link.length());
			pokemonNamesArray.add(link);
		}
	}
	
	public String[] getPokemonArray() {
		String[] array = new String[pokemonNamesArray.size()];
		array = pokemonNamesArray.toArray(array);
		return array;
	}
	
}
