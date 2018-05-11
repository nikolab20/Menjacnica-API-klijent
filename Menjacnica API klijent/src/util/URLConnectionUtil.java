package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import domen.Valuta;

public class URLConnectionUtil {
	
	public static LinkedList<Valuta> getAllCountries() {
		String url = "http://free.currencyconverterapi.com/api/v3/countries";

		try {
			String result = getContent(url);
			Gson gson = new GsonBuilder().create();
			JsonObject jsonResult = gson.fromJson(result, JsonObject.class);
			JsonObject resultObject = (JsonObject) jsonResult.get("results");

			Set<Entry<String, JsonElement>> set = resultObject.entrySet();
			LinkedList<Valuta> lista = new LinkedList<Valuta>();

			for (Entry<String, JsonElement> entry : set) {
				JsonObject jsonObj = (JsonObject) entry.getValue();
				Valuta v = new Valuta(jsonObj.get("name").getAsString(), jsonObj.get("currencyId").getAsString());
				lista.add(v);
			}
			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getContent(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		boolean endReading = false;
		String response = "";
		
		while (!endReading) {
			String s = in.readLine();
			
			if (s != null) {
				response += s;
			} else {
				endReading = true;
			}
		}
		in.close();
 
		return response.toString();
	}
	
	public static double getKurs(String from, String to) throws IOException {
		String url = "http://free.currencyconverterapi.com/api/v3/convert?q=" + from + "_" + to;

		String result = getContent(url);
		Gson gson = new GsonBuilder().create();
		JsonObject jsonResult = gson.fromJson(result, JsonObject.class);
		int queryNumber = (((JsonObject) jsonResult.get("query")).get("count")).getAsInt();
		JsonObject kursObjekat = (JsonObject) (((JsonObject) jsonResult.get("results")).get(from + "_" + to));
		double kurs = kursObjekat.get("val").getAsDouble();
		if (queryNumber == 0) {
			return -1;
		}
		return kurs;
	}
}
