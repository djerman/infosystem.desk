package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.e.godina.EGodina;
import rs.atekom.infosystem.baza.e.godina.EGodinaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class EGodinaRestKlijent extends OpstiRest{

	public EGodinaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<EGodinaOdgovor> sve(DPretplatnik pretplatnik){
		try {
			String putanja = ""; 
			if(pretplatnik != null) {
				putanja = PrijavaController.adresa + "/godine/pretplatnik?id=" + pretplatnik.getId();
				}else {
					putanja = PrijavaController.adresa + "/godine";
					}
			ResponseEntity<EGodinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, EGodinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				EGodinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), EGodinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<EGodinaOdgovor> snimi(EGodina godina){
		try {
			String putanja = PrijavaController.adresa + "/godina/";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(godina, ServisRest.getHeaders());
			ResponseEntity<EGodinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, EGodinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				EGodinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), EGodinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<EGodinaOdgovor> brisi(EGodina godina){
		try {
			String putanja = PrijavaController.adresa + "/godina/" + godina.getId();
			ResponseEntity<EGodinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request , EGodinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				EGodinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), EGodinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	}
