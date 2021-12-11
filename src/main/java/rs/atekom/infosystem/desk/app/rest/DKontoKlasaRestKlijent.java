package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import rs.atekom.infosystem.baza.d.kontoklasa.DKontoKlasaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class DKontoKlasaRestKlijent extends OpstiRest{

	public DKontoKlasaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<DKontoKlasaOdgovor> pretraga(String pretraga){
		try {
			String putanja = PrijavaController.adresa + "/klasekonta" + (pretraga == null ? "" : "?pretraga=" + pretraga);
			ResponseEntity<DKontoKlasaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, DKontoKlasaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DKontoKlasaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DKontoKlasaOdgovor.class);
					}catch (Exception ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
