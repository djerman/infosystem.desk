package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rs.atekom.infosystem.baza.da.kontogrupa.DAKontoGrupaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class DAKontoGrupaRestKlijent extends OpstiRest{

	public DAKontoGrupaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<DAKontoGrupaOdgovor> pretraga(String pretraga, Long klasaId){
		try {
			String putanja = PrijavaController.adresa + "/grupekonta" + (pretraga == null ? "" : "?pretraga=" + pretraga) 
					+ (pretraga == null ? klasaId == null ? "" : "?klasaId=" + klasaId : klasaId == null ? "" : "&klasaId=" + klasaId);
			ResponseEntity<DAKontoGrupaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, DAKontoGrupaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				DAKontoGrupaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), DAKontoGrupaOdgovor.class);
					}catch (Exception ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
