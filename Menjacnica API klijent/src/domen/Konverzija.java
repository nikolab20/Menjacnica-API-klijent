package domen;

import java.util.Date;

public class Konverzija {

	private Date vreme;
	private String izValuta;
	private String uValuta;
	private double kurs;

	public Konverzija(Date vreme, String izValuta, String uValuta, double kurs) {
		this.vreme = vreme;
		this.izValuta = izValuta;
		this.uValuta = uValuta;
		this.kurs = kurs;
	}

	public Konverzija(String izValuta, String uValuta, double kurs) {
		this.vreme = new Date();
		this.izValuta = izValuta;
		this.uValuta = uValuta;
		this.kurs = kurs;
	}

	public Konverzija() {
	}

	public Date getVreme() {
		return vreme;
	}

	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}

	public String getIzValuta() {
		return izValuta;
	}

	public void setIzValuta(String izValuta) {
		this.izValuta = izValuta;
	}

	public String getuValuta() {
		return uValuta;
	}

	public void setuValuta(String uValuta) {
		this.uValuta = uValuta;
	}

	public double getKurs() {
		return kurs;
	}

	public void setKurs(double kurs) {
		this.kurs = kurs;
	}
}
