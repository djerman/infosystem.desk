package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rs.atekom.infosystem.baza.db.kontoracun.DBKontoRacunOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class DBKontoRacunRestKlijent extends OpstiRest{

	public DBKontoRacunRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<DBKontoRacunOdgovor> pretraga(String pretraga, Long grupaId){
		try {
			String putanja = PrijavaController.adresa + "/racunikonta" + (pretraga == null ? "" : "?pretraga=" + pretraga) 
					+ (pretraga == null ? grupaId == null ? "" : "?grupaId=" + grupaId : grupaId == null ? "" : "&grupaId=" + grupaId);
			ResponseEntity<DBKontoRacunOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, DBKontoRacunOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DBKontoRacunOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DBKontoRacunOdgovor.class);
					}catch (Exception ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
