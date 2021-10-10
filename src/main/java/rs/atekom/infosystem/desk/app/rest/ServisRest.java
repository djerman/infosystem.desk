package rs.atekom.infosystem.desk.app.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import rs.atekom.infosystem.desk.PrijavaController;
import java.util.Arrays;

public class ServisRest {

	public HttpEntity<String> request;
	public HttpHeaders headers;
	
	public ServisRest() {
		headers = new HttpHeaders();
		headers.setBearerAuth(PrijavaController.token);
		//headers.setBasicAuth(PrijavaController.korisnik, PrijavaController.lozinka);
		//System.out.println(headers);
		request = new HttpEntity<String>(headers);
		}
	
	/**
	 * проследи параметре за приступ
	 * @return
	 */
    public static HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        //headers.setBasicAuth(PrijavaController.korisnik, PrijavaController.lozinka);
        headers.setBearerAuth(PrijavaController.token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
        }
    
    }
