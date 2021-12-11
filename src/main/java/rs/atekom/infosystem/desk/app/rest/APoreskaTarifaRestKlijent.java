package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class APoreskaTarifaRestKlijent extends OpstiRest{

	public APoreskaTarifaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<APoreskaTarifaOdgovor> lista(){
		try {
			String putanja = PrijavaController.adresa + "/poresketarife";
			ResponseEntity<APoreskaTarifaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, APoreskaTarifaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				APoreskaTarifaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), APoreskaTarifaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
