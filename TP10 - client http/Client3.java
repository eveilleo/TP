// Client3.java

import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import javax.json.*;

public class Client3
{
	public static void main( String[] args )
	{
		try 
		{
			CloseableHttpClient client = HttpClients.createDefault();
			String url = "http://" + args[0];
			HttpGet request = new HttpGet(url);
			System.out.println("Executing request" + request.getRequestLine() );
			CloseableHttpResponse resp = client.execute(request);
			System.out.println("Response Line: " + resp.getStatusLine() );
			System.out.println("Response Code: " + resp.getStatusLine().getStatusCode() );
			InputStreamReader isr = new InputStreamReader(resp.getEntity().getContent() );
			JsonReader reader = Json.createReader(isr);
			JsonObject jsonObject = reader.readObject();
			System.out.println("duree: " + jsonObject.getString("Runtime") );
			JsonArray tab = jsonObject.getJsonArray("Ratings");
			for (int i = 0; i < tab.size(); i++) {
				JsonObject ji = tab.getJsonObject(i);
				if ("Rotten Tomatoes".equals(ji.getString("Source"))) {
					String rottenTomatoesValue = ji.getString("Value");
					System.out.println("Rotten Tomatoes Value: " + rottenTomatoesValue);
					displayScoreMention(rottenTomatoesValue);
				}
			}
			reader.close();
			isr.close();
		}
		catch( Exception ex )
		{
			System.out.println( "erreur !" );
			ex.printStackTrace();
		}
	}
	private static void displayScoreMention(String score) 
	{
		try 
		{
			double scoreValue = Double.parseDouble(score.replaceAll("[^0-9]", ""));
			System.out.print("Score Mention: ");
			if (scoreValue < 20) {
				System.out.println("Nul");
			} else if (scoreValue >= 20 && scoreValue < 50) {
				System.out.println("Bof");
			} else if (scoreValue >= 50 && scoreValue < 70) {
				System.out.println("Bien");
			} else {
				System.out.println("Très bien");
			}
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("Score non disponible ou mal formaté.");
		}
	}
}	