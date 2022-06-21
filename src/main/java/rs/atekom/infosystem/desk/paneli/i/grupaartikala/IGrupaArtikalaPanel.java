package rs.atekom.infosystem.desk.paneli.i.grupaartikala;

import java.util.List;

import org.springframework.http.ResponseEntity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikalaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.IGrupaArtikalaRestKlijent;

public class IGrupaArtikalaPanel extends OsnovniPanel{

	private IGrupaArtikalaTabela grupe;
	private TableViewSelectionModel<IGrupaArtikala> izborGrupe;
	private IGrupaArtikalaRestKlijent restGrupa;
	private IGrupaArtikalaPregled pregled;
	
	public IGrupaArtikalaPanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
	}

	private void postaviElemente() {
		restGrupa = new IGrupaArtikalaRestKlijent(vratiRestServis());
		
		postaviInfo();
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborGrupe.getSelectedItem() != null) {
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
			izborGrupe.clearSelection();
			});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, grupe, info);
		VBox.setVgrow(grupe, Priority.ALWAYS);
		setContent(vratiRoot());
	}
	
	private void postaviPregled() {
		pregled = new IGrupaArtikalaPregled(this, vratiOsnovniLayout().vratiResource());
	}
	
	private void postaviTabelu() {
		grupe = new IGrupaArtikalaTabela(vratiOsnovniLayout().vratiResource());
		izborGrupe = grupe.getSelectionModel();
		izborGrupe.setSelectionMode(SelectionMode.SINGLE);
		izborGrupe.selectedItemProperty().addListener(new ChangeListener<IGrupaArtikala>() {
			@Override
			public void changed(ObservableValue<? extends IGrupaArtikala> observable, IGrupaArtikala oldValue,
					IGrupaArtikala newValue) {
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
		ResponseEntity<IGrupaArtikalaOdgovor> odgovor = null;
		try {
			odgovor = restGrupa.lista(vratiPretplatnika());
			statusOdgovora(odgovor);
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	public void osveziTabelu(List<IGrupaArtikala> listaGrupa) {
		if(grupe.getItems() != null) {
			grupe.getItems().clear();
		}
		ObservableList<IGrupaArtikala> lista = null;
		if(listaGrupa != null) {
			lista = FXCollections.observableArrayList(listaGrupa);
			postaviUkupno(listaGrupa.size());
		}else {
			postaviUkupno(0);
		}
		grupe.getSortOrder().clear();
		grupe.setItems(lista);
		grupe.refresh();
	}
	
	public void azurirajTabelu(Boolean snimi) {
		ResponseEntity<IGrupaArtikalaOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restGrupa.snimi(pregled.preuzmiObjekat());
			}else {
				odgovor = restGrupa.brisi(izborGrupe.getSelectedItem());
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
			ResponseEntity<IGrupaArtikalaOdgovor> odgovor = (ResponseEntity<IGrupaArtikalaOdgovor>) odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			pregled.postaviNovo();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
