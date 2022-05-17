package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.agencija.AAgencija;
import rs.atekom.infosystem.baza.d.pretplatnik.DPodaciZaPretplatnikaOdgovor;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnikOdgovor;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnikPodaciOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class DPretplatnikRestKlijent extends OpstiRest{

	public DPretplatnikRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<DPodaciZaPretplatnikaOdgovor> podaci(){
		try {
			String putanja = PrijavaController.adresa + "/pretplatnici/podaci";
			ResponseEntity<DPodaciZaPretplatnikaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, 
					servis.request, DPodaciZaPretplatnikaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DPodaciZaPretplatnikaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DPodaciZaPretplatnikaOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<DPretplatnikOdgovor> pretraga(String pretraga, AAgencija agencija){
		try {
			String putanja = PrijavaController.adresa + "/pretplatnici" + (pretraga == null ? "" : "?pretraga=" + pretraga) + (agencija == null ? "" : 
				(pretraga == null ? "?agencijaId=" + agencija.getId(): "&agencijaId=" + agencija.getId()));
			ResponseEntity<DPretplatnikOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, DPretplatnikOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DPretplatnikOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DPretplatnikOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<DPretplatnikOdgovor> snimi(DPretplatnikPodaciOdgovor pretplatnik){
		try {
			String putanja = PrijavaController.adresa + "/pretplatnik/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(pretplatnik, ServisRest.getHeaders());
			ResponseEntity<DPretplatnikOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, DPretplatnikOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DPretplatnikOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DPretplatnikOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<DPretplatnikOdgovor> brisi(DPretplatnik pretplatnik){
		try {
			String putanja = PrijavaController.adresa + "/pretplatnik/brisi/" + pretplatnik.getId();
			ResponseEntity<DPretplatnikOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, DPretplatnikOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DPretplatnikOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DPretplatnikOdgovor.class);
					}catch (JsonProcessingException ee) {
						//ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
