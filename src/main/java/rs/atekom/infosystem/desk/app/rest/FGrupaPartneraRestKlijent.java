package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.f.FGrupaPartnera;
import rs.atekom.infosystem.baza.f.FGrupaPartneraOdgovor;
import rs.atekom.infosystem.desk.PrijavaController;

public class FGrupaPartneraRestKlijent extends OpstiRest{

	public FGrupaPartneraRestKlijent(ServisRest rest) {
		super(rest);
		}
	
	public ResponseEntity<FGrupaPartneraOdgovor> lista(DPretplatnik pretplatnik){
		if(pretplatnik != null) {
			try {
				String putanja = PrijavaController.adresa + "/grupepartnera?pretplatnikId=" + pretplatnik.getId();
				ResponseEntity<FGrupaPartneraOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.GET, servis.request, FGrupaPartneraOdgovor.class);
				return odgovor;
				}catch (HttpStatusCodeException e) {
					FGrupaPartneraOdgovor odgovor = null;
					try {
						odgovor = mapper.readValue(e.getResponseBodyAsString(), FGrupaPartneraOdgovor.class);
						}catch (JsonProcessingException ee) {
							//
							}
					return ResponseEntity.status(e.getRawStatusCode())
							.headers(e.getResponseHeaders())
							.body(odgovor);
					}
			}else {
				return new ResponseEntity<FGrupaPartneraOdgovor>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
	
	public ResponseEntity<FGrupaPartneraOdgovor> snimi(FGrupaPartnera grupa){
		try {
			String putanja = PrijavaController.adresa + "/grupapartnera/";
			HttpEntity<Object> zahtev = new HttpEntity<Object>(grupa, ServisRest.getHeaders());
			ResponseEntity<FGrupaPartneraOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.PUT, zahtev, FGrupaPartneraOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				FGrupaPartneraOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), FGrupaPartneraOdgovor.class);
					}catch (JsonProcessingException ee) {
						//
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	
	public ResponseEntity<FGrupaPartneraOdgovor> brisi(FGrupaPartnera grupa){
		try {
			String putanja = PrijavaController.adresa + "/grupapartnera/" + grupa.getId();
			HttpEntity<Object> zahtev = new HttpEntity<Object>(grupa, ServisRest.getHeaders());
			ResponseEntity<FGrupaPartneraOdgovor> odgovor = new RestTemplate().exchange(putanja, HttpMethod.DELETE, zahtev, FGrupaPartneraOdgovor.class);
			return odgovor;
			}catch (HttpStatusCodeException e) {
				FGrupaPartneraOdgovor odgovor = null;
				try {
					odgovor = mapper.readValue(e.getResponseBodyAsString(), FGrupaPartneraOdgovor.class);
					}catch (JsonProcessingException ee) {
						//
						}
				return ResponseEntity.status(e.getRawStatusCode())
						.headers(e.getResponseHeaders())
						.body(odgovor);
				}
		}
	}
