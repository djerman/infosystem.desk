package rs.atekom.infosystem.desk.w.izbornikkontroleri;

import java.net.URL;
import java.util.ResourceBundle;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.baza.h.HKontakt;
import rs.atekom.infosystem.desk.a.PocetniTab;
import rs.atekom.infosystem.desk.app.rest.ServisRest;

public class OsnovniController implements Initializable{

	@FXML
	private MenuBar izbornik;
	@FXML
	private Menu podaci, roba, finansije;
	@FXML
	private TabPane taboviPane;
	
	private Tab pocetniTab;
	//private URL location;
	private ResourceBundle resources;
	private ServisRest restServis;
	private HKontakt kontakt;
	public GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
	
	public OsnovniController() {
		
		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		podaci.setGraphic(fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.CORAL));
		roba.setGraphic(fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.BLACK));
		finansije.setGraphic(fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.GREEN));
		this.resources = resources;
		//this.location = location;
		//System.out.println("osnovni kontroler lokacija " + this.location.toString());
		pocetniTab = new PocetniTab();
		pocetniTab.setText(this.resources.getString("tab.pocetni"));
		pocetniTab.setClosable(false);
		taboviPane.getTabs().add(pocetniTab);
		}
	
	@FXML
	public void initialize() {
		
		}

	public ServisRest getRestServis() {
		return restServis;
		}

	public void setRestServis(ServisRest restServis) {
		this.restServis = restServis;
		}

	public HKontakt getKontakt() {
		return kontakt;
		}

	public void setKontakt(HKontakt kontakt) {
		this.kontakt = kontakt;
		}
	
	}
