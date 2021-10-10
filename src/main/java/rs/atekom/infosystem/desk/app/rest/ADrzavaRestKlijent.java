package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class ADrzavaRestKlijent extends OpstiRest{

	public ADrzavaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	/**
	 * pretraga/preuzimanje liste drzava
	 * @param pretraga
	 * @return
	 */
	public ResponseEntity<ADrzavaOdgovor> pretraga(String pretraga){
		try {
			String putanja = PrijavaController.adresa + "/drzave" + (pretraga == null ? "" : "?pretraga=" + pretraga);
			ResponseEntity<ADrzavaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, ADrzavaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				ADrzavaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), ADrzavaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * vraća sve opštine i mesta za izabranu državu
	 * @param drzava
	 * @return
	 */
	public ResponseEntity<ADrzavaOdgovor> liste(ADrzava drzava){
		try {
			String putanja = PrijavaController.adresa + "/drzave/liste?drzavaId=" + drzava.getId();
			ResponseEntity<ADrzavaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, ADrzavaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				ADrzavaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), ADrzavaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * snimanje/izmena podataka o državi
	 * @param drzava
	 * @return
	 */
	public ResponseEntity<ADrzavaOdgovor> snimi(ADrzava drzava){
		try {
			String putanja = PrijavaController.adresa + "/drzava/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(drzava, ServisRest.getHeaders());
			ResponseEntity<ADrzavaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, ADrzavaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				ADrzavaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), ADrzavaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * brisanje drzave
	 * @param drzava
	 * @return
	 */
	public ResponseEntity<ADrzavaOdgovor> brisi(ADrzava drzava){
		try {
			String putanja = PrijavaController.adresa + "/drzava/brisi/" + drzava.getId();
			ResponseEntity<ADrzavaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, ADrzavaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				ADrzavaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), ADrzavaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
