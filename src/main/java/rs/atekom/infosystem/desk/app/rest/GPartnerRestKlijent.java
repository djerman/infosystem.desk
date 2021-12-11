package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.g.GPartner;
import rs.atekom.infosystem.baza.g.GPartnerOdgovor;
import rs.atekom.infosystem.baza.g.GPartnerOdgovorPodaci;
import rs.atekom.infosystem.desk.PrijavaController;

public class GPartnerRestKlijent extends OpstiRest{

	public GPartnerRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<GPartnerOdgovor> lista(String pretraga, DPretplatnik pretplatnik, Boolean kupac){
		if(pretplatnik != null) {
			try {
				String putanja = PrijavaController.adresa + "/partneri?pretplatnikId=" + pretplatnik.getId() + (pretraga == null ? "" : "&pretraga=" + pretraga) 
						+ (kupac == null ? "" : "&kupac=" + kupac);
				ResponseEntity<GPartnerOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, GPartnerOdgovor.class);
				return odgovor;
				}catch (HttpStatusCodeException e) {
					GPartnerOdgovor odgovor = null;
					try {
						odgovor = mapper.readValue(e.getResponseBodyAsString(), GPartnerOdgovor.class);
						}catch (JsonProcessingException ee) {
							//
							}
					return ResponseEntity.status(e.getRawStatusCode())
							.headers(e.getResponseHeaders())
							.body(odgovor);
					}
			}else {
				return new ResponseEntity<GPartnerOdgovor>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
	
	public ResponseEntity<GPartnerOdgovor> snimi(GPartnerOdgovorPodaci partner, Boolean kupac){
		try {
			String putanja = PrijavaController.adresa + "/partner/snimi" + (kupac == null ? "" : "?kupac=" + kupac);
			HttpEntity<Object> zahtev = new HttpEntity<Object>(partner, ServisRest.getHeaders());
			ResponseEntity<GPartnerOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, GPartnerOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				GPartnerOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), GPartnerOdgovor.class);
					}catch (JsonProcessingException ee) {
						//
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<GPartnerOdgovor> brisi(GPartner partner, Boolean kupac){
		try {
			String putanja = PrijavaController.adresa + "/partner/brisi?id=" + partner.getId() + (kupac == null ? "" : "&kupac=" + kupac);
			ResponseEntity<GPartnerOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, GPartnerOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				GPartnerOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), GPartnerOdgovor.class);
					}catch (JsonProcessingException ee) {
						//
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	}
