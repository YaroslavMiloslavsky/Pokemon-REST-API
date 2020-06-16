
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Networking {
	private URL url;
	private HttpsURLConnection connection = null;
	private BufferedReader reader;
	private StringBuffer lineReader;
	
	public Networking(String url) {
		try {
			this.url = new URL(url);
			connection = (HttpsURLConnection) this.url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setReadTimeout(7000);
			connection.setConnectTimeout(7000);
			
			int status = connection.getResponseCode();
			if(status != 200) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				lineReader = new StringBuffer();
				String line = new String();
				while((line = reader.readLine()) != null) {
					lineReader.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				lineReader = new StringBuffer();
				String line = new String();
				while((line = reader.readLine()) != null) {
					lineReader.append(line);
				}
				reader.close();
			}
			
		}catch (MalformedURLException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getJSON() {
		return this.lineReader.toString();
	}
}
