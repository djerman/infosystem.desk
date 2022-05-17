package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMereOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class AJedinicaMereRestKlijent extends OpstiRest{

	public AJedinicaMereRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<AJedinicaMereOdgovor> lista(){
		try {
			String putanja = PrijavaController.adresa + "/jedinice";
			ResponseEntity<AJedinicaMereOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, AJedinicaMereOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AJedinicaMereOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AJedinicaMereOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<AJedinicaMereOdgovor> snimi(AJedinicaMere jedinica){
		try {
			String putanja = PrijavaController.adresa + "/jedinica/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(jedinica, ServisRest.getHeaders());
			ResponseEntity<AJedinicaMereOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, AJedinicaMereOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AJedinicaMereOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AJedinicaMereOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<AJedinicaMereOdgovor> brisi(AJedinicaMere jedinica){
		try {
			String putanja = PrijavaController.adresa + "/jedinica/brisi/" + jedinica.getId();
			ResponseEntity<AJedinicaMereOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, AJedinicaMereOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AJedinicaMereOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AJedinicaMereOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	}
