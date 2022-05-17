package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.e.grupaprava.EGrupaPrava;
import rs.atekom.infosystem.baza.e.grupaprava.EGrupaPravaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class EGrupaPravaRestKlijent extends OpstiRest{

	public EGrupaPravaRestKlijent(ServisRest rest) {
		super(rest);
	}

	public ResponseEntity<EGrupaPravaOdgovor> lista(DPretplatnik pretplatnik){
		try {
			String putanja = PrijavaController.adresa + "/grupaprava" + (pretplatnik == null ? "" : "?pretplatnikId=" + pretplatnik.getId());
			ResponseEntity<EGrupaPravaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, EGrupaPravaOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			EGrupaPravaOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), EGrupaPravaOdgovor.class);
			}catch (JsonProcessingException ee){
				
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<EGrupaPravaOdgovor> snimi(EGrupaPrava grupa){
		try {
			String putanja = PrijavaController.adresa + "/grupaprava/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(grupa, ServisRest.getHeaders());
			ResponseEntity<EGrupaPravaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, EGrupaPravaOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			EGrupaPravaOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), EGrupaPravaOdgovor.class);
			}catch (JsonProcessingException ee){
				
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<EGrupaPravaOdgovor> brisi(EGrupaPrava grupa){
		try {
			String putanja = PrijavaController.adresa + "/grupaprava/" + grupa.getId();
			ResponseEntity<EGrupaPravaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, EGrupaPravaOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			EGrupaPravaOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), EGrupaPravaOdgovor.class);
			}catch (JsonProcessingException ee){
				
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
}
