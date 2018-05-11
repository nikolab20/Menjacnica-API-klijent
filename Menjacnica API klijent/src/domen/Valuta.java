package domen;

public class Valuta {
	private String naziv;

	private String id;

	public Valuta(String naziv, String id) {
		this.naziv = naziv;
		this.id = id;
	}

	public Valuta() {
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Valuta [naziv=" + naziv + ", id=" + id + "]";
	}

}
