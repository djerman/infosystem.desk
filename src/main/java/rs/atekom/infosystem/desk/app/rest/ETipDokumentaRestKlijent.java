package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rs.atekom.infosystem.baza.e.tipdokumenta.ETipDokumentaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class ETipDokumentaRestKlijent extends OpstiRest{

	public ETipDokumentaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<ETipDokumentaOdgovor> pretraga(String pretraga, Long pretplatnikId){
		try {
			String putanja = PrijavaController.adresa + "/tipdokumenta" +  (pretraga == null ? "" : "?pretraga=" + pretraga)
					+ (pretraga == null ? (pretplatnikId == null ? "" : "?pretplatnikId=" + pretplatnikId) : (pretplatnikId == null ? "" : "&pretplatnikId=" + pretplatnikId));
			ResponseEntity<ETipDokumentaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, ETipDokumentaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				e.printStackTrace();
				ETipDokumentaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), ETipDokumentaOdgovor.class);
					}catch (Exception ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
