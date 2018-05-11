package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.kontroler.GUIKontroler;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValuteZemlje;
	private JComboBox comboBoxIz;
	private JComboBox comboBoxU;
	private JLabel lblIznos;
	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		setResizable(false);
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValuteZemlje());
		contentPane.add(getComboBoxIz());
		contentPane.add(getComboBoxU());
		contentPane.add(getLblIznos());
		contentPane.add(getLabel());
		contentPane.add(getTextField());
		contentPane.add(getTextField_1());
		contentPane.add(getBtnNewButton());

	}

	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(70, 70, 100, 14);
		}
		return lblIzValuteZemlje;
	}

	private JLabel getLblUValuteZemlje() {
		if (lblUValuteZemlje == null) {
			lblUValuteZemlje = new JLabel("U valute zemlje:");
			lblUValuteZemlje.setBounds(274, 70, 100, 14);
		}
		return lblUValuteZemlje;
	}

	private JComboBox getComboBoxIz() {
		if (comboBoxIz == null) {
			comboBoxIz = new JComboBox();
			comboBoxIz.setBounds(70, 95, 100, 20);
			for (int i = 0; i < GUIKontroler.lista.size(); i++) {
				comboBoxIz.addItem(GUIKontroler.lista.get(i).getNaziv());
			}
		}
		return comboBoxIz;
	}

	private JComboBox getComboBoxU() {
		if (comboBoxU == null) {
			comboBoxU = new JComboBox();
			comboBoxU.setBounds(274, 95, 100, 20);
			for (int i = 0; i < GUIKontroler.lista.size(); i++) {
				comboBoxU.addItem(GUIKontroler.lista.get(i).getNaziv());
			}
		}
		return comboBoxU;
	}

	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos");
			lblIznos.setBounds(70, 126, 46, 14);
		}
		return lblIznos;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Iznos");
			label.setBounds(274, 126, 46, 14);
		}
		return label;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(70, 151, 100, 20);
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(274, 151, 100, 20);
		}
		return textField_1;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Konvertuj");
			btnNewButton.setBounds(175, 206, 100, 23);
		}
		return btnNewButton;
	}
}
