package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.f.objekat.FObjekat;
import rs.atekom.infosystem.baza.f.objekat.FObjekatOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class FObjekatRestKlijent extends OpstiRest{

	public FObjekatRestKlijent(ServisRest rest) {
		super(rest);
	}

	/**
	 * preuzimanje svih objekata sa ili bez pretrage
	 * @param pretraga
	 * @param pretplatnikId
	 * @return
	 */
	public ResponseEntity<FObjekatOdgovor> pretraga(String pretraga, Long pretplatnikId){
		try {
			String putanja = PrijavaController.adresa + "/objekti?pretplatnikId=" + pretplatnikId + (pretraga == null ? "" : "&pretraga=" + pretraga);
			ResponseEntity<FObjekatOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, FObjekatOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			FObjekatOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), FObjekatOdgovor.class);
			}catch (JsonProcessingException ee) {
				ee.printStackTrace();
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}

	/**
	 * snimanje objekta
	 * @param objekat
	 * @return
	 */
	public ResponseEntity<FObjekatOdgovor> snimi(FObjekat objekat){
		try {
			String putanja = PrijavaController.adresa + "/objekat/";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(objekat, ServisRest.getHeaders());
			ResponseEntity<FObjekatOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, FObjekatOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			FObjekatOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), FObjekatOdgovor.class);
			}catch (JsonProcessingException ee) {
				ee.printStackTrace();
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	/**
	 * btrisanje objekta
	 * @param objekat
	 * @return
	 */
	public ResponseEntity<FObjekatOdgovor> brisi(FObjekat objekat){
		try {
			String putanja = PrijavaController.adresa + "/objekat/" + objekat.getId();
			ResponseEntity<FObjekatOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, FObjekatOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			FObjekatOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), FObjekatOdgovor.class);
			}catch (JsonProcessingException ee) {
				ee.printStackTrace();
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
}
