package gui.kontroler;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import domen.Konverzija;
import domen.Valuta;
import gui.MenjacnicaGUI;
import util.SerijalizacijaIUcitavanje;
import util.URLConnectionUtil;

/**
 * 
 * @author Nikola Bakic
 *
 */

public class GUIKontroler {
	private static MenjacnicaGUI glavniProzor;
	public static LinkedList<Valuta> lista;
	public static LinkedList<Konverzija> konverzije;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ucitajListu();
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
					konverzije = new LinkedList<Konverzija>();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		if (konverzije != null && konverzije.size() > 0)
			upisiJsonULog();
		System.exit(0);
	}
	
	private static void ucitajListu() {
		lista = URLConnectionUtil.getAllCountries();
		if (lista == null) {
			JOptionPane.showMessageDialog(glavniProzor, "Neuspesno ucitavanje zemalja. Probajte ponovo!", "Greska!",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}

	}
	
	public static String[] vratiNaziveSvihDrzava() {
		String[] nazivi = new String[lista.size()];
		int brojac = 0;
		for (Valuta valuta : lista) {
			nazivi[brojac++] = valuta.getNaziv();
		}
		return nazivi;
	}
	
	public static double konvertuj(String drzavaIz, String drzavaU, String vrednost) {
		double iznos = -1;
		try {
			iznos = Double.parseDouble(vrednost);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(glavniProzor, "Nepravilno unet iznos!", "Greska!", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		String from = "", to = "";
		for (Valuta valuta : lista) {
			if (valuta.getNaziv().equals(drzavaIz)) {
				from = valuta.getId();
			}
			if (valuta.getNaziv().equals(drzavaU)) {
				to = valuta.getId();
			}
		}

		double rate;
		try {
			rate = URLConnectionUtil.getKurs(from, to);
			if (rate == -1) {
				JOptionPane.showMessageDialog(glavniProzor, "Ne postoji kurs za unete drzave!", "Greska!",
						JOptionPane.ERROR_MESSAGE);
				return -1;
			}

			zabeleziKonverziju(from, to, rate);

			return rate * iznos;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(glavniProzor, "Komunikacija sa serverom neuspesna!", "Greska!",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}

	}
	
	private static void upisiJsonULog() {
		JsonArray konverzijeJson = SerijalizacijaIUcitavanje.serijalizujKonverzije(konverzije);

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/log.json")));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String konverzijeString = gson.toJson(konverzijeJson);

			out.println(konverzijeString);
			out.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(glavniProzor, "Neuspesno upisivanje konverzija u log!", "Greska!",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private static void zabeleziKonverziju(String from, String to, double rate) {
		Konverzija konverzija = new Konverzija(from, to, rate);
		konverzije.add(konverzija);
	}
}
