package gui.kontroler;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import domen.Valuta;
import gui.MenjacnicaGUI;
import util.URLConnectionUtil;

public class GUIKontroler {
	private static MenjacnicaGUI glavniProzor;
	public static LinkedList<Valuta> lista;
	
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
}
