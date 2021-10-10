package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.baza.b.BOpstinaOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class BOpstinaRestKlijent extends OpstiRest{

	public BOpstinaRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	/**
	 * vraća listu opština po izabranoj državi
	 * @param drzava
	 * @return
	 */
	public ResponseEntity<BOpstinaOdgovor> poDrzavi(ADrzava drzava){
		try {
			String putanja = PrijavaController.adresa + "/opstine/drzava?drzavaId=" + drzava.getId();
			ResponseEntity<BOpstinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, BOpstinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				BOpstinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), BOpstinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * lista opština po pretrazi
	 * @param pretraga
	 * @return
	 */
	public ResponseEntity<BOpstinaOdgovor> pretraga(String pretraga){
		try {
			String putanja = PrijavaController.adresa + "/opstine/pretraga" + (pretraga == null ? "" : "?pretraga=" + pretraga);
			ResponseEntity<BOpstinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, BOpstinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				BOpstinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), BOpstinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * snimanje opštine i vraćanje liste 
	 * @param opstina
	 * @return
	 */
	public ResponseEntity<BOpstinaOdgovor> snimi(BOpstina opstina){
		try {
			String putanja = PrijavaController.adresa + "/opstina/snimi";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(opstina, ServisRest.getHeaders());
			ResponseEntity<BOpstinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, BOpstinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				BOpstinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), BOpstinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	/**
	 * brisanje opštine i vraćanje liste prema državi kojoj je pripadala
	 * @param opstina
	 * @return
	 */
	public ResponseEntity<BOpstinaOdgovor> brisi(BOpstina opstina){
		try {
			String putanja = PrijavaController.adresa + "/opstina/brisi/" + opstina.getId();
			ResponseEntity<BOpstinaOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, servis.request, BOpstinaOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				BOpstinaOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), BOpstinaOdgovor.class);
					}catch (JsonProcessingException ee) {
						ee.printStackTrace();
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	}
