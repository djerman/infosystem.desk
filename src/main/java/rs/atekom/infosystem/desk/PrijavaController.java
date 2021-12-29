package rs.atekom.infosystem.desk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rs.atekom.infosystem.baza.h.HKontakt;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.app.rest.ServisRest;

@Component
public class PrijavaController implements Initializable{

	@FXML
	public TextField korisnickoTxt;
	@FXML
	public PasswordField lozinkaTxt;
	@FXML
	public Button prijavaBtn;
	@FXML
	public Button otkaziBtn;
	public static String korisnik;
	public static String lozinka;
	public static String token;
	private GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
	public static String adresa = "http://173.212.202.168:8080/infosystem";
	//public static String adresa = "http://192.168.199.5:8080/infosystem.server-0.0.1-SNAPSHOT";
	//public static String adresa = "http://localhost:8080";
	private Stage stage;
	private ResourceBundle resource;
	//private JMetro jMetro;
	
	//@Value("classpath:/fxml/Osnovni.fxml")
	//private Resource resourcefxml;
	
	public PrijavaController() {
		// TODO Auto-generated constructor stub
		//jMetro = new JMetro(Style.LIGHT);
		resource = ResourceBundle.getBundle("prevod", new Locale(""));
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prijavaBtn.setGraphic(fontAwesome.create(FontAwesome.Glyph.CHECK).color(Color.LIMEGREEN));
		otkaziBtn.setGraphic(fontAwesome.create(FontAwesome.Glyph.CLOSE).color(Color.RED));
		}
	
	@FXML
	public void otkazi(ActionEvent event) {
		Platform.exit();
		}
	
	@FXML
	public void prijava(ActionEvent event) {
		try {
		    //HttpHeaders headers = new HttpHeaders();
		    //headers.setBasicAuth(korisnickoTxt.getText().trim(), lozinkaTxt.getText().toString());
		    // create request
		    //HttpEntity<String> request = new HttpEntity<String>(headers);
		    // make a request
			korisnik = korisnickoTxt.getText().trim();
			lozinka = lozinkaTxt.getText().trim().toString();
		    
		    String putanjaPrijave = adresa + "/prijava";
		    String odgovor = "";
		    URL url = new URL(putanjaPrijave);
		    HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type", "application/json; utf-8");
		    con.setRequestProperty("Accept", "application/json");
		    con.setDoOutput(true);
		    String jsonInputString = "{\"username\": \"" + korisnickoTxt.getText().trim() + "\", \"password\": \"" + lozinkaTxt.getText().trim() + "\"}";
		    try(OutputStream os = con.getOutputStream()) {
		        byte[] input = jsonInputString.getBytes("utf-8");
		        os.write(input, 0, input.length);			
		        }
		    
		    try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
		    		    StringBuilder response = new StringBuilder();
		    		    String responseLine = null;
		    		    while ((responseLine = br.readLine()) != null) {
		    		        response.append(responseLine.trim());
		    		        }
		    		    odgovor = response.toString();
		    		    //System.out.println(odgovor);
		    		    }catch (Exception e) {
		    		    	//e.printStackTrace();
		    		    	//pokaziUpozorenje();
		    		    	}
		    
		    if(odgovor.contains("token")) {
		    	String[] delovi = odgovor.split("\"");
		    	token = delovi[3];
		    	//System.out.println(token);
			    String putanja = adresa + "/kontakti" + "?korisnicko=" + korisnickoTxt.getText().trim();
			    ServisRest rest = new ServisRest();
			    ResponseEntity<HKontakt> response = new RestTemplate().exchange(putanja, HttpMethod.GET, rest.request, HKontakt.class);
			    HKontakt kontakt = response.getBody();
				if(response.getStatusCodeValue() == 200 && kontakt != null) {
					korisnik = kontakt.getKorisnicko();
					lozinka = kontakt.getLozinka();
					stage = (Stage) prijavaBtn.getScene().getWindow();
					int width = (int) Screen.getPrimary().getBounds().getWidth();
					int height = (int) Screen.getPrimary().getBounds().getHeight();
					
					//URL urlOsnovni = resource.getURL();
					//FXMLLoader loader = new FXMLLoader(urlOsnovni);
					//loader.setResources(ResourceBundle.getBundle("prevod", new Locale("srb_RS")));
					if(kontakt.getJezik() != null) {
						resource = ResourceBundle.getBundle("prevod", new Locale(kontakt.getJezik()));
						}
					
					Parent root = new OsnovniLayout(kontakt, rest, resource);
					//Parent root = loader.load(getClass().getResource("/Osnovni.fxml").openStream());
					//Parent root = loader.load();
					//OsnovniController osnovniController = (OsnovniController) loader.getController();
					//osnovniController.setRestServis(rest);
					//osnovniController.setKontakt(kontakt);
					
					Scene scene = new Scene(root, width, height);
				    scene.getStylesheets().add("/css/stilovi.css");
				    //jMetro.setScene(scene); 
				    stage = (Stage) prijavaBtn.getScene().getWindow();
					stage.setScene(scene);
					stage.setMaximized(true);
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent event) {
	                        Alert izlaz = new Alert(AlertType.WARNING);
	                		ButtonType da = new ButtonType(resource.getString("upozorenje.otkazi"), ButtonData.OK_DONE);
	                		ButtonType otkazi = new ButtonType(resource.getString("upozorenje.izlaz"), ButtonData.CANCEL_CLOSE);
	                		izlaz.setTitle(resource.getString("upozorenje.title"));
	                		izlaz.setHeaderText(resource.getString("upozorenje.header"));
	                		izlaz.setContentText(resource.getString("upozorenje.content"));
	                		izlaz.getButtonTypes().clear();
	                		izlaz.getButtonTypes().addAll(otkazi, da);
	                		izlaz.showAndWait();
	                        if (izlaz.getResult().getButtonData() == ButtonData.CANCEL_CLOSE) {
	                            System.exit(0);
	                            } else {
	                            	event.consume();
	                            	}
	                        }
						});
					Platform.setImplicitExit(false);
					}else {
						pokaziUpozorenje();
						}
				}else {
					pokaziUpozorenje();
					}
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	pokaziUpozorenje();
		    	}
		}
	
	private void pokaziUpozorenje() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle(resource.getString("obavestenja.greska"));
		errorAlert.setHeaderText(resource.getString("obavestenja.header"));
		errorAlert.setContentText(resource.getString("obavestenja.content"));
		errorAlert.showAndWait();
		}
	
	}
