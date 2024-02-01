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
			while (true) 
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Entrez le nom du film (ou 'exit' pour quitter) : ");
				String title = reader.readLine();
				if ("exit".equalsIgnoreCase(title)) 
				{
					System.out.println("Programme quitter.");
					break;
				}
				CloseableHttpClient client = HttpClients.createDefault();

				String url = "http://" + args[0] + title;
				HttpGet request = new HttpGet(url);

				System.out.println("Executing request" + request.getRequestLine() );
				CloseableHttpResponse resp = client.execute(request);

				System.out.println("Response Line: " + resp.getStatusLine() );
				System.out.println("Response Code: " + resp.getStatusLine().getStatusCode() );

				InputStreamReader isr = new InputStreamReader(resp.getEntity().getContent() );
				JsonReader jsonReader = Json.createReader(isr);
				JsonObject jsonObject = jsonReader.readObject();
				
				String releaseDate = getJsonValue(jsonObject, "Released");
				System.out.println("Date de sortie : " + releaseDate);

				String actors = getJsonValue(jsonObject, "Actors");
				System.out.println("Acteurs principaux : " + actors);

				JsonArray tab = getJsonArrayValue(jsonObject, "Ratings");
				String rottenTomatoesScore = "N/A";

				for (int i = 0; i < tab.size(); i++) {
					JsonObject ji = tab.getJsonObject(i);
					if ("Rotten Tomatoes".equals(ji.getString("Source"))) {
						String rottenTomatoesValue = ji.getString("Value");
					}
				}

				System.out.println("Score Rotten Tomatoes : " + rottenTomatoesScore);

				int numericScore = parseNumericScore(rottenTomatoesScore);
				String critiqueMention = getCritiqueMention(numericScore);
				System.out.println("Critique : " + critiqueMention);

				jsonReader.close();
				isr.close();
			}
		}		
		catch( Exception ex )
		{
			System.out.println( "erreur !" );
			ex.printStackTrace();
		}
	}
	private static String getJsonValue(JsonObject jsonObject, String key) {
		return jsonObject.containsKey(key) && !jsonObject.isNull(key) ?
			jsonObject.getString(key) :
			"Non disponible";
	}
	private static JsonArray getJsonArrayValue(JsonObject jsonObject, String key) {
		return jsonObject.containsKey(key) && !jsonObject.isNull(key) ?
			jsonObject.getJsonArray(key) :
			Json.createArrayBuilder().build();
	}
	private static int parseNumericScore(String score) {
		try {
			return Integer.parseInt(score.replaceAll("[^0-9]", ""));
		} catch (NumberFormatException e) {
			return -1; // Retourne -1 en cas d'échec de la conversion
		}
	}
	private static String getCritiqueMention(int score) {
		if (score < 0) {
			return "Non disponible";
		} else if (score < 20) {
			return "Nul";
		} else if (score <= 50) {
			return "Bof";
		} else if (score <= 70) {
			return "Bien";
		} else {
			return "Très bien";
		}
	}
}	