package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.baza.c.CMestoOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class CMestoRestKlijent extends OpstiRest{

	public CMestoRestKlijent(ServisRest rest) {
		super(rest);
		// TODO Auto-generated constructor stub
		}
	
	/**
	 * zahtev za listu mesta po izabranoj državi
	 * @param drzava
	 * @return
	 */
	public ResponseEntity<CMestoOdgovor> poDrzavi(ADrzava drzava){
		try {
			String putanja = PrijavaController.adresa + "/mesta/drzava?drzavaId=" + drzava.getId();
			ResponseEntity<CMestoOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, CMestoOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				CMestoOdgovor odgovor = null;
				try{
					odgovor = mapper.readValue(e.getResponseBodyAsString(), CMestoOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * vraća listu mesta po opštini
	 * @param opstina
	 * @return
	 */
	public ResponseEntity<CMestoOdgovor> poOpstini(BOpstina opstina){
		try {
			String putanja = PrijavaController.adresa + "/mesta/opstina?opstinaId=" + opstina.getId();
			ResponseEntity<CMestoOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, CMestoOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				CMestoOdgovor odgovor = null;
				try{
					odgovor = mapper.readValue(e.getResponseBodyAsString(), CMestoOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * pretraga mesta
	 * @param pretraga
	 * @return
	 */
	public ResponseEntity<CMestoOdgovor> pretraga(String pretraga){
		try {
			String putanja = PrijavaController.adresa + "/mesta/pretraga" + (pretraga == null ? "" : "?pretraga=" + pretraga);
			ResponseEntity<CMestoOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, CMestoOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				CMestoOdgovor odgovor = null;
				try{
					odgovor = mapper.readValue(e.getResponseBodyAsString(), CMestoOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * snimanje mesta
	 * @param mesto
	 * @return
	 */
	public ResponseEntity<CMestoOdgovor> snimi(CMesto mesto){
		try {
			String putanja = PrijavaController.adresa + "/mesto/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(mesto, ServisRest.getHeaders());
			ResponseEntity<CMestoOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, CMestoOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				CMestoOdgovor odgovor = null;
				try{
					odgovor = mapper.readValue(e.getResponseBodyAsString(), CMestoOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * brisanje mesta
	 * @param mesto
	 * @return
	 */
	public ResponseEntity<CMestoOdgovor> brisi(CMesto mesto){
		try {
			String putanja = PrijavaController.adresa + "/mesto/brisi/" + mesto.getId();
			ResponseEntity<CMestoOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, CMestoOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				CMestoOdgovor odgovor = null;
				try{
					odgovor = mapper.readValue(e.getResponseBodyAsString(), CMestoOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
