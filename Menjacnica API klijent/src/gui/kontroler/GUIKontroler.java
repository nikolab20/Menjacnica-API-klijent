package gui.kontroler;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import domen.Konverzija;
import domen.Valuta;
import gui.MenjacnicaGUI;
import util.URLConnectionUtil;

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
	
	private static void ucitajListu() {
		lista = URLConnectionUtil.getAllCountries();
		if (lista == null) {
			JOptionPane.showMessageDialog(glavniProzor, "Neuspesno ucitavanje zemalja. Probajte ponovo!", "Greska!",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}

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

			return rate * iznos;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(glavniProzor, "Komunikacija sa serverom neuspesna!", "Greska!",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}

	}
}
