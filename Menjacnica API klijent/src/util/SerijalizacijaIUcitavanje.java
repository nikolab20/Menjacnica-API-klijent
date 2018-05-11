package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domen.Konverzija;

public class SerijalizacijaIUcitavanje {

	public static JsonArray serijalizujKonverzije(LinkedList<Konverzija> konverzije) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		JsonArray nizKonverzija = ucitajIzFajla();
		if (nizKonverzija == null) {
			nizKonverzija = new JsonArray();
		}

		for (int i = 0; i < konverzije.size(); i++) {
			Konverzija konv = konverzije.get(i);

			JsonObject konverzijaJson = new JsonObject();
			konverzijaJson.addProperty("datumVreme", sdf.format(konv.getVreme()));
			konverzijaJson.addProperty("izValuta", konv.getIzValuta());
			konverzijaJson.addProperty("uValuta", konv.getuValuta());
			konverzijaJson.addProperty("kurs", konv.getKurs());

			nizKonverzija.add(konverzijaJson);
		}

		return nizKonverzija;

	}
	
	private static JsonArray ucitajIzFajla() {
		JsonArray niz;
		String rezultat = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			BufferedReader br = new BufferedReader(new FileReader("data/log.json"));
			while (true) {
				String a = br.readLine();
				if (a == null) {
					break;
				}
				rezultat += a;
			}
			br.close();
			niz = gson.fromJson(rezultat, JsonArray.class);
			return niz;
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
		return null;
	}

}
