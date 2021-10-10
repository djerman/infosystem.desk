package rs.atekom.infosystem.desk.a;

import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.baza.d.DPretplatnik;
import rs.atekom.infosystem.baza.h.HKontakt;
import rs.atekom.infosystem.desk.app.izbornik.IzbornikBar;
import rs.atekom.infosystem.desk.app.rest.ServisRest;

public class OsnovniLayout extends VBox{

	private TabPane tabovi;
	private IzbornikBar meni;//
	private HKontakt kontakt;
	private AnchorPane meniPane;
	private Label kontaktLabela/*, pozicijaLabela, lokacija*/;
	private ServisRest restServis;
	private PocetniTab pocetniTab;
	private HBox info;
	private Region region;
	private ResourceBundle resource;
	
	public OsnovniLayout(HKontakt kont, ServisRest rest, ResourceBundle resource) {
		kontakt = kont;
		restServis = rest;
		this.resource = resource;
		podesiOsnovniLayout();
		}
	
	public void podesiOsnovniLayout() {
		meniPane = new AnchorPane();
		meni = new IzbornikBar(this);
		
		info = new HBox(2.0);
		info.setAlignment(Pos.CENTER);
		kontaktLabela =  new Label(kontakt.getIme().toUpperCase() + " " + kontakt.getPrezime().toUpperCase() + (kontakt.getPretplatnik() == null ? "" :
			"/" + kontakt.getPretplatnik().getNaziv().toUpperCase()) + (kontakt.getAgencija() == null ? "" :  "/" + kontakt.getAgencija().getNaziv()));
		kontaktLabela.setTextFill(Color.RED);////korisnik.setTextFill(Color.web("#0076a3"));
		region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		
		//pozicijaLabela = new Label("POZICIJA: ");
		//pozicijaLabela.setTextFill(Color.BLUE);
		//lokacija = new Label("PREGLED");
		//lokacija.setTextFill(Color.BLUE);
		
		info.getChildren().addAll(kontaktLabela);
		AnchorPane.setRightAnchor(info, 0.0);
		AnchorPane.setTopAnchor(info, 0.0);
		AnchorPane.setBottomAnchor(info, 0.0);
		//HBox.setHgrow(meniPane, Priority.ALWAYS);
		meniPane.getChildren().addAll(meni, info);

		tabovi = new TabPane();
		pocetniTab = new PocetniTab();
		pocetniTab.setText(resource.getString("tab.pocetni"));
		pocetniTab.setClosable(false);
		tabovi.getTabs().add(pocetniTab);
		
		addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ESCAPE && !tabovi.getSelectionModel().getSelectedItem().equals(pocetniTab)) {
					 tabovi.getTabs().remove(tabovi.getSelectionModel().getSelectedItem());
					 }
				}
			});
		
		tabovi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue != null) {
					azurirajPanel(newValue);
					}
				}
			});
		
		getChildren().addAll(meniPane, tabovi);
		}
	
	public void dodajTab(String naziv, Node content) {
		Tab novi = new Tab(naziv, content); 
		tabovi.getTabs().add(novi);
		tabovi.getSelectionModel().select(novi);
		}
	
	public void dodajTab(Tab tab) {
		tabovi.getTabs().add(tab);
		tabovi.getSelectionModel().select(tab);
		}
	
	public Tab vratiTab() {
		return tabovi.getSelectionModel().getSelectedItem();
		}
	
	public HKontakt vratiKorisnika() {
		return kontakt;
		}
	
	public DPretplatnik vratiPretplatnika() {
		return kontakt.getPretplatnik();
		}
	
	public TabPane vratiTabove() {
		return tabovi;
		}
	
	public ServisRest vratiRestServis() {
		return restServis;
		}
	
	public String vratiPrevod(String kljuc) {
		return resource.getString(kljuc);
		}
	
	public ResourceBundle vratiResource() {
		return this.resource;
		}

	/*
	public Label getLokacija() {
		return lokacija;
		}

	public void setLokacija(Label lokacija) {
		this.lokacija = lokacija;
		}*/
	
	private void azurirajPanel(Tab novi) {
		//
		}
	
	}
