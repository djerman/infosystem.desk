package rs.atekom.infosystem.desk.paneli.f.objekat;

import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.springframework.http.ResponseEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.baza.f.objekat.FObjekat;
import rs.atekom.infosystem.baza.f.objekat.FObjekatOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.FObjekatRestKlijent;

public class FObjekatPanel extends OsnovniPanel{

	private FObjekatTabela objekti;
	private TableViewSelectionModel<FObjekat> izborObjekta;
	private FObjekatRestKlijent restObjekat;
	private FObjekatPregled pregled;
	private HBox pregledi;
	private Button poslovanje, lager, artikli, dokumenti, kepu;
	private ResourceBundle resource;
	
	public FObjekatPanel(OsnovniLayout ol) {
		super(ol);
	}

	@Override
	public void postaviElemente() {
		resource = vratiOsnovniLayout().vratiResource();
		restObjekat = new FObjekatRestKlijent(vratiRestServis());
		
		postaviInfo();
		postaviPregled();
		postaviKomandeSaPretragom();
		postaviPreglede();
		
		izbrisi.setOnAction(e -> {
			if(izborObjekta.getSelectedItem() != null) {
				azurirajTabelu(false);
			}else {
				pokaziGresku(vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje"), vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje.poruka"));
			}
		});
		
		sacuvaj.setOnAction(e -> {
			if(pregled.proveraUnosa()) {
				azurirajTabelu(true);
			}else {
				pokaziObaveznaPolja(vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja.obavestenje"));
			}
		});
		
		novo.setOnAction(e -> {
			pregled.postaviNovo();
			izborObjekta.clearSelection();
		});
		
		vratiPretragu().setOnAction(e -> {
			popuniTabeluPretragom(vratiPretragu().getText().trim());
		});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, pregledi, objekti, info);
		VBox.setVgrow(objekti, Priority.ALWAYS);
		setContent(vratiRoot());
	}
	
	private void postaviPregled() {
		pregled = new FObjekatPregled(this, resource);
	}
	
	private void postaviPreglede() {
		pregledi = new HBox(5.0);
		pregledi.getStyleClass().add("komande-za-pregled");
		
		Color boja = Color.BROWN;
		
		poslovanje = new Button(resource.getString("lbl.poslovanje"), new Glyph("FontAwesome", FontAwesome.Glyph.BAR_CHART).color(boja));
		poslovanje.setStyle("-fx-font-weight: bold;");
		Region region = new Region();
		lager = new Button(resource.getString("lbl.lager"), new Glyph("FontAwesome", FontAwesome.Glyph.PUZZLE_PIECE).color(boja));
		lager.setStyle("-fx-font-weight: bold;");
		artikli = new Button(resource.getString("lbl.artikli"), new Glyph("FontAwesome", FontAwesome.Glyph.SHOPPING_CART).color(boja));
		artikli.setStyle("-fx-font-weight: bold;");
		dokumenti = new Button(resource.getString("lbl.dokumenti"), new Glyph("FontAwesome", FontAwesome.Glyph.PENCIL_SQUARE).color(boja));
		dokumenti.setStyle("-fx-font-weight: bold;");
		kepu = new Button(resource.getString("lbl.kepu"), new Glyph("FontAwesome", FontAwesome.Glyph.BOOK).color(boja));
		kepu.setStyle("-fx-font-weight: bold;");
		
		pregledi.getChildren().addAll(poslovanje, region, lager, artikli, dokumenti, kepu);
		HBox.setHgrow(region, Priority.ALWAYS);
	}
	
	private void postaviTabelu() {
		objekti = new FObjekatTabela(vratiOsnovniLayout().vratiResource());
		izborObjekta = objekti.getSelectionModel();
		izborObjekta.setSelectionMode(SelectionMode.SINGLE);
		izborObjekta.selectedItemProperty().addListener(new ChangeListener<FObjekat>() {
			@Override
			public void changed(ObservableValue<? extends FObjekat> observable, FObjekat oldValue, FObjekat newValue) {
				if(newValue != null) {
					pregled.postaviObjekat(newValue);
				}else {
					pregled.postaviNovo();
				}
			}
		});
	}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<FObjekatOdgovor> odgovor = null;
		try {
			odgovor = restObjekat.pretraga(null, vratiPretplatnika().getId());
			statusOdgovora(odgovor);
			if(odgovor != null && odgovor.getBody() != null) {
				pregled.setKonta(odgovor.getBody().getKonta());
				pregled.setMesta(odgovor.getBody().getMesta());
				pregled.setOrganizacije(odgovor.getBody().getOrganizacije());
			}
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}

	private void popuniTabeluPretragom(String pojam) {
		ResponseEntity<FObjekatOdgovor> odgovor = null;
		try {
			odgovor = restObjekat.pretraga(pojam, vratiPretplatnika().getId());
			statusOdgovora(odgovor);
			if(odgovor != null && odgovor.getBody() != null) {
			pregled.setKonta(odgovor.getBody().getKonta());
			pregled.setMesta(odgovor.getBody().getMesta());
			pregled.setOrganizacije(odgovor.getBody().getOrganizacije());
			}
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	public void osveziTabelu(List<FObjekat> listaObjekata) {
		if(objekti.getItems() != null)
			objekti.getItems().clear();
		ObservableList<FObjekat> lista = null;
		if(listaObjekata != null) {
			lista = FXCollections.observableArrayList(listaObjekata);
			postaviUkupno(listaObjekata.size());
		}else {
			postaviUkupno(0);
		}
		objekti.getSortOrder().clear();
		objekti.setItems(lista);
		objekti.refresh();
	}
	
	public void azurirajTabelu(Boolean snimi) {
		ResponseEntity<FObjekatOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restObjekat.snimi(pregled.preuzmiObjekat());
			}else {
				odgovor = restObjekat.brisi(izborObjekta.getSelectedItem());
			}
			statusOdgovora(odgovor);
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<FObjekatOdgovor> odgovor = (ResponseEntity<FObjekatOdgovor>)odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			pregled.postaviNovo();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
