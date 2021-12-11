package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.agencija.AAgencija;
import rs.atekom.infosystem.baza.a.agencija.AAgencijaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class AAgencijaRestKlijent extends OpstiRest{

	public AAgencijaRestKlijent(ServisRest rest) {
		super(rest);
		}

	public ResponseEntity<AAgencijaOdgovor> pretraga(String pretraga){
		try {
			String putanja = PrijavaController.adresa + "/agencije" + (pretraga == null ? "" : "?pretraga=" + pretraga);
			ResponseEntity<AAgencijaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, AAgencijaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AAgencijaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AAgencijaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<AAgencijaOdgovor> snimi(AAgencija agencija){
		try {
			String putanja = PrijavaController.adresa + "/agencija/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(agencija, ServisRest.getHeaders());
			ResponseEntity<AAgencijaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, AAgencijaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AAgencijaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AAgencijaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<AAgencijaOdgovor> izmeni(AAgencija agencija){
		try {
			String putanja = PrijavaController.adresa + "/agencija/izmeni";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(agencija, ServisRest.getHeaders());
			ResponseEntity<AAgencijaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, AAgencijaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AAgencijaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AAgencijaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<AAgencijaOdgovor> brisi(AAgencija agencija){
		try {
			String putanja = PrijavaController.adresa + "/agencija/brisi/" + agencija.getId();
			ResponseEntity<AAgencijaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, AAgencijaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				AAgencijaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), AAgencijaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
