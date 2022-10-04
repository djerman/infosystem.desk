package rs.atekom.infosystem.desk.a;

public class Jezik {

	private String ime;
	private String oznaka;
	
	public Jezik(String ime, String oznaka) {
		this.ime = ime;
		this.oznaka = oznaka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	
	@Override
	public String toString() {
		return ime;
	}
}
