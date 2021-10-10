package rs.atekom.infosystem.desk.a;

import org.controlsfx.control.Notifications;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import rs.atekom.infosystem.baza.d.DPretplatnik;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;
import rs.atekom.infosystem.desk.app.rest.ServisRest;

@Component
public abstract class OsnovniPanel extends ScrollPane{

	private VBoxRoot root;
	//private AlatkeBar alatke;
	private OsnovniLayout osnovniLayout;
	private ServisRest restServis;
	private ObavestenjеPotvrda nemaOdgovoraServera, uspesno, nijeUspesno, obaveznaPolja, podatakPostoji, verzija;
	private ObavestenjeBrisanje obavestenje;
	private String naslov, naslovIzmena, naslovBrisanje;
	public Double razmak = 5.0;
	public Button izbrisi, izmeniSacuvaj, dodaj;
	private Region region;
	public HBox komande;
	//public String putanja;
	//public Boolean pozvanOsvezi;
	//public int strana, velicina;
	
	public OsnovniPanel(OsnovniLayout ol) {
		/*
		putanja = "";
		pozvanOsvezi = true;
		strana = 1;
		velicina = 100;
		
		alatke = new AlatkeBar();
		
		alatke.vratiIzadji().setOnAction(e -> {
			alatke.vratiIzadji().fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.ESCAPE, true, true, true, true));
			});
		
		alatke.getNaPocetak().setOnAction(e -> {
			alatke.getTekucaStrana().setText(String.valueOf(1));
			popuniTabelu();
			});
		
		alatke.getNazad().setOnAction(e -> {
			int str;
			try {
				str = Integer.valueOf(alatke.getTekucaStrana().getText().trim());
				if(str > 1) {
					str -= 1;
					alatke.getTekucaStrana().setText(String.valueOf(str));
					popuniTabelu();
					}else {
						pokaziGresku("BROJ STRANE", "VEĆ STE NA POČETNOJ STRANI!");
					}
				}catch (Exception ex) {
					pokaziGresku("BROJ STRANE", "NIJE ISPRAVAN BROJ STRANE!");
					}
			});
		
		alatke.getNapred().setOnAction(e -> {
			int str;
			try {
				str = Integer.valueOf(alatke.getTekucaStrana().getText().trim());
				if(str < Integer.valueOf(alatke.getUkupnoStrana().getText())) {
					str += 1;
					alatke.getTekucaStrana().setText(String.valueOf(str));
					popuniTabelu();
					}else {
						pokaziGresku("BROJ STRANE", "VEĆ STE NA POSLEDNJOJ STRANI!");
						}
				}catch (Exception ex) {
					pokaziGresku("BROJ STRANE", "NIJE ISPRAVAN BROJ STRANE!");
					}
			});
		
		alatke.getNaKraj().setOnAction(e -> {
			int str;
			try {
				str = Integer.valueOf(alatke.getUkupnoStrana().getText());
				}catch (Exception ex) {
					str = 1;
					}
			alatke.getTekucaStrana().setText(String.valueOf(str));
			popuniTabelu();
			});
		
		alatke.vratiPretraga().setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER) {
				popuniTabelu();
				}
			});
		*/
		root = new VBoxRoot();
		root.setFillWidth(true);
		root.setSpacing(5);
		root.setPadding(new Insets(5));
		//root.setStyle("-fx-background-color: #ffeda2;"); //setStyle("-fx-background-color: lemonchiffon;");
		//root.getChildren().add(alatke);
		osnovniLayout = ol;
		restServis = osnovniLayout.vratiRestServis();
		
		nemaOdgovoraServera = new ObavestenjеPotvrda(AlertType.ERROR, osnovniLayout.vratiResource().getString("obavestenja.nijeuspesno"), 
				osnovniLayout.vratiResource().getString("obavestenja.vezasaserverom"), osnovniLayout.vratiResource().getString("obavestenja.vezasaserverom"), 
				osnovniLayout.vratiResource());
		
	    setFitToHeight(true);
	    setFitToWidth(true);
	    
		setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		}
	/*
	public void postaviFocus() {
		Platform.runLater(() -> {
			int max = 3;
			alatke.vratiPretraga().requestFocus();
			if(!alatke.vratiPretraga().isFocused() && max > 0) {
				alatke.vratiPretraga().requestFocus();
				max = max - 1;
				}
			});
		}
		*/
	
	public void postaviKomande() {
		komande = new HBox(razmak);
		komande.setPadding(new Insets(10));
		izbrisi = new Button(osnovniLayout.vratiResource().getString("btn.izbrisi"), new Glyph("FontAwesome", "TRASH_ALT").color(Color.RED));
		region= new Region();
		izmeniSacuvaj = new Button(osnovniLayout.vratiResource().getString("btn.sacuvaj"), new Glyph("FontAwesome", FontAwesome.Glyph.PENCIL).color(Color.GREEN));
		dodaj = new Button(osnovniLayout.vratiResource().getString("btn.dodaj"), new Glyph("FontAwesome", FontAwesome.Glyph.PLUS).color(Color.BLUE));
		komande.getChildren().addAll(izbrisi, region, dodaj, izmeniSacuvaj);
		HBox.setHgrow(region, Priority.ALWAYS);
		}
	
	public void ugasi() {
		Stage stage = (Stage) getScene().getWindow();
		stage.close();
		}
	
	public void pokaziUspesno(String header, String context) {
		uspesno = new ObavestenjеPotvrda(AlertType.INFORMATION, osnovniLayout.vratiResource().getString("obavestenja.uspesno"), header, context, osnovniLayout.vratiResource());
		uspesno.showAndWait();
		}
	
	public void pokaziNijeUspesno(String header, String context) {
		nijeUspesno = new ObavestenjеPotvrda(AlertType.WARNING, osnovniLayout.vratiResource().getString("obavestenja.nijeuspesno"), header, context, osnovniLayout.vratiResource());
		nijeUspesno.showAndWait();
		}
	
	public void pokaziObaveznaPolja(String naslov, String sadrzaj) {
		obaveznaPolja = new ObavestenjеPotvrda(AlertType.WARNING, osnovniLayout.vratiResource().getString("obavestenja.obavezno"), naslov, sadrzaj, osnovniLayout.vratiResource());
		obaveznaPolja.showAndWait();
		}
	
	public void pokaziUpozorenje(String naslov, String sadrzaj) {
		obavestenje = new ObavestenjeBrisanje(AlertType.WARNING, naslov, sadrzaj, osnovniLayout.vratiResource());
		obavestenje.showAndWait();
		}
	
	public void pokaziVerzija(String header) {
		verzija = new ObavestenjеPotvrda(AlertType.WARNING, osnovniLayout.vratiResource().getString("obavestenja.nijeuspesno"), header, 
				osnovniLayout.vratiResource().getString("obavestenja.verzije"), osnovniLayout.vratiResource());
		verzija.showAndWait();
		}
	
	public void pokaziPostoji(String context) {
		podatakPostoji = new ObavestenjеPotvrda(AlertType.WARNING, osnovniLayout.vratiResource().getString("obavestenja.duplikat"), 
				osnovniLayout.vratiResource().getString("obavestenja.snimanjepodataka"), context, osnovniLayout.vratiResource());
		podatakPostoji.showAndWait(); 
		}
	
	public ObavestenjeBrisanje vratiObavestenjeBrisanja() {
		return obavestenje;
		}
	
	public void pokaziGresku(String naslov, String sadrzaj) {
		Notifications.create().title(naslov).text(sadrzaj).hideAfter(Duration.seconds(5)).showError();
		}

	public OsnovniLayout vratiOsnovniLayout() {
		return osnovniLayout;
		}
	
	public void vratiNemaOdgovoraServera() {
		nemaOdgovoraServera.showAndWait();
		}
	
	public DPretplatnik vratiPretplatnika() {
		return osnovniLayout.vratiPretplatnika();
		}
	
	public VBoxRoot vratiRoot() {
		return root;
		}

	/*
	public AlatkeBar vratiAlatke() {
		return alatke;
		}*/

	public ServisRest vratiRestServis() {
		return restServis;
		}
	
	public String vratiNaslov() {
		return naslov;
		}
	
	public void postaviNaslov(String s) {
		naslov = s;
		}
	
	public String vratiNaslovIzmena() {
		return naslovIzmena;
		}
	
	public void postaviNaslovIzmena(String s) {
		naslovIzmena = s;
		}
	
	public String vratiNaslovBrisanje() {
		return naslovBrisanje;
		}
	
	public void postaviNaslovBrisanje(String s) {
		naslovBrisanje = s;
		}
	
	public void popuniTabelu() {
		//
		}
	/*
	public void ukloniStranicenje() {
		vratiAlatke().ukloniDugme(alatke.vratiStranicenje());
		}
	
	public void ukloniAlatke() {
		root.getChildren().remove(alatke);
		}
	*/
	public int strana(int strana) {
		return strana + 1;
		}
	
	public void osveziTabeluTipova() {
		//samo zbog pozivanja metoda kod aktiviranja taba
		}
	
	public void statusOdgovora(ResponseEntity<? extends Object> odgovor) {
		if(odgovor != null) {
			switch(odgovor.getStatusCodeValue()) {
			case Odgovori.IZVRSEN:
				izvrsen(odgovor);
				break;
			case Odgovori.NEMA_ZAPISA:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.nemazapisa"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.NIJE_USPELO:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.nijeuspesno"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.VERZIJA:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.verzija"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.POSTOJI:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.postoji"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.ZABRANJENO_BRISANJE:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.zabranjenobrisanje"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.NIJE_DOZVOLJENO:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.nijedozvoljeno"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.GRESKA_SERVERA:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.greskaservera"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			case Odgovori.PREKO_DOZVOLJENOG:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.prekodozvoljenog"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				break;
			default:
				pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.nijeuspesno"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				}
			}else {
				vratiNemaOdgovoraServera();
				}
		}
	
	public void izvrsen(Object odgovor) {
		//
		}
	
	/*
	public void postaviPageable() {
		try {
			strana = Integer.valueOf(vratiAlatke().getTekucaStrana().getText()).intValue();
			}catch (Exception e) {
			// TODO: handle exception
				}
		
		try {
			velicina = Integer.valueOf(vratiAlatke().getBrojZapisaPoStrani().getValue()).intValue();
			}catch (Exception e) {
			// TODO: handle exception
				}
		}
		*/
	}

