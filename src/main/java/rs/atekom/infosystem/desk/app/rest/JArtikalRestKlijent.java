package rs.atekom.infosystem.desk.app.rest;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.j.JArtikal;
import rs.atekom.infosystem.baza.j.JArtikalOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class JArtikalRestKlijent extends OpstiRest{

	public JArtikalRestKlijent(ServisRest rest) {
		super(rest);
	}

	public ResponseEntity<JArtikalOdgovor> lista(DPretplatnik pretplatnik, Optional<String> pretraga, Optional<Integer> tip,  int strana){
		try {
			String pojam = null;
			Integer tipArtikla = null;
			if(pretraga != null && pretraga.isPresent()) {
				pojam = pretraga.get();
			}
			if(tip != null && tip.isPresent())
				tipArtikla = tip.get();
			String putanja = PrijavaController.adresa + "/artikli?pretplatnikId=" + pretplatnik.getId() + (pojam == null ? "" : "&pretraga=" + pojam) + (tip == null ? "" : "&tip=" + tipArtikla) + "&strana=" + strana;
			ResponseEntity<JArtikalOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, JArtikalOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			JArtikalOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), JArtikalOdgovor.class);
			}catch (JsonProcessingException ee) {
				ee.printStackTrace();
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<JArtikalOdgovor> snimi(JArtikal artikal){
		try {
			String putanja = PrijavaController.adresa + "/artikal/";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(artikal, ServisRest.getHeaders());
			ResponseEntity<JArtikalOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, JArtikalOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			JArtikalOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), JArtikalOdgovor.class);
			}catch (JsonProcessingException ee) {
				ee.printStackTrace();
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
	public ResponseEntity<JArtikalOdgovor> brisi(JArtikal artikal){
		try {
			String putanja = PrijavaController.adresa + "/artikal/" + artikal.getId();
			ResponseEntity<JArtikalOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, JArtikalOdgovor.class);
			return odgovor;
		}catch (HttpStatusCodeException e) {
			JArtikalOdgovor odgovor = null;
			try {
				odgovor = mapper.readValue(e.getResponseBodyAsString(), JArtikalOdgovor.class);
			}catch (JsonProcessingException ee) {
				ee.printStackTrace();
			}
			return ResponseEntity.status(e.getRawStatusCode())
					.headers(e.getResponseHeaders())
					.body(odgovor);
		}
	}
	
}
