package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rs.atekom.infosystem.baza.e.konto.EKontoOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class EKontoRestKlijent extends OpstiRest{

	public EKontoRestKlijent(ServisRest rest) {
		super(rest);
		// TODO Auto-generated constructor stub
		}
	
	public ResponseEntity<EKontoOdgovor> pretraga(String pretraga, Long podgrupaId, Long pretplatnikId){
		try {
			String putanja = PrijavaController.adresa + "/konta" + (pretraga == null ? "" : "?pretraga=" + pretraga)
					+ (pretraga == null ? (podgrupaId == null ? "" : "?podgrupaId=" + podgrupaId) : (podgrupaId == null ? "" : "&podgrupaId=" + podgrupaId))
					+ ((pretraga == null && podgrupaId == null) ? (pretplatnikId == null ? "" : "?pretplatnikId=" + pretplatnikId) : (pretplatnikId == null ? "" : "&pretplatnikId=" + pretplatnikId));
			ResponseEntity<EKontoOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, EKontoOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				e.printStackTrace();
				EKontoOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), EKontoOdgovor.class);
					}catch (Exception ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
