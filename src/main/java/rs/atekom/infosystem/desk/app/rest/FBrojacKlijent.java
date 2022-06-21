package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.f.brojac.FBrojac;
import rs.atekom.infosystem.baza.f.brojac.FBrojacOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class FBrojacKlijent extends OpstiRest{

	public FBrojacKlijent(ServisRest rest) {
		super(rest);
	}

	public ResponseEntity<FBrojacOdgovor> lista(DPretplatnik pretplatnik){
		try {
			String putanja = PrijavaController.adresa + "/brojaci?pretplatnikId=" + pretplatnik.getId();
			ResponseEntity<FBrojacOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, FBrojacOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			FBrojacOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), FBrojacOdgovor.class);
			}catch (Exception ee) {
				// TODO: handle exception
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<FBrojacOdgovor> snimi(FBrojac brojac){
		try {
			String putanja = PrijavaController.adresa + "/brojac/";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(brojac, ServisRest.getHeaders());
			ResponseEntity<FBrojacOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, FBrojacOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			FBrojacOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), FBrojacOdgovor.class);
			}catch (Exception ee) {
				// TODO: handle exception
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
}
