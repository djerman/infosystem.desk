package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikalaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class IGrupaArtikalaRestKlijent extends OpstiRest{

	public IGrupaArtikalaRestKlijent(ServisRest rest) {
		super(rest);
	}

	public ResponseEntity<IGrupaArtikalaOdgovor> lista(DPretplatnik pretplatnik){
		try {
			String putanja = PrijavaController.adresa + "/grupeartikala?pretplatnikId=" + pretplatnik.getId();
			ResponseEntity<IGrupaArtikalaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, IGrupaArtikalaOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			IGrupaArtikalaOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), IGrupaArtikalaOdgovor.class);
			}catch (JsonProcessingException ee) {
				// TODO: handle exception
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<IGrupaArtikalaOdgovor> snimi(IGrupaArtikala grupa){
		try {
			String putanja = PrijavaController.adresa + "/grupaartikala/";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(grupa, ServisRest.getHeaders());
			ResponseEntity<IGrupaArtikalaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, IGrupaArtikalaOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			IGrupaArtikalaOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), IGrupaArtikalaOdgovor.class);
			}catch (JsonProcessingException ee) {
				// TODO: handle exception
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<IGrupaArtikalaOdgovor> brisi(IGrupaArtikala grupa){
		try {
			String putanja = PrijavaController.adresa + "/grupaartikala/" + grupa.getId();
			ResponseEntity<IGrupaArtikalaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, IGrupaArtikalaOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			IGrupaArtikalaOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), IGrupaArtikalaOdgovor.class);
			}catch (JsonProcessingException ee) {
				// TODO: handle exception
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
}
