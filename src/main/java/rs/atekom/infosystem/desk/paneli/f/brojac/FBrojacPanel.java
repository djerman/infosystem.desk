package rs.atekom.infosystem.desk.paneli.f.brojac;

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
import rs.atekom.infosystem.baza.f.brojac.FBrojac;
import rs.atekom.infosystem.baza.f.brojac.FBrojacOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.FBrojacKlijent;

public class FBrojacPanel extends OsnovniPanel{

	private FBrojacTabela brojaci;
	private TableViewSelectionModel<FBrojac> izborBrojaca;
	private FBrojacKlijent restBrojac;
	private FBrojacPregled pregled;
	
	public FBrojacPanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
	}

	private void postaviElemente() {
		restBrojac = new FBrojacKlijent(vratiRestServis());
		
		postaviInfo();
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborBrojaca.getSelectedItem() != null) {
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
			izborBrojaca.clearSelection();
		});
		
		postaviTabelu();
		popuniTabelu();
		vratiRoot().getChildren().addAll(pregled, komande, brojaci, info);
		VBox.setVgrow(brojaci,  Priority.ALWAYS);
		setContent(vratiRoot());
	}
	
	private void postaviPregled() {
		pregled = new FBrojacPregled(vratiOsnovniLayout().vratiResource());
	}
	
	private void postaviTabelu() {
		brojaci = new FBrojacTabela(vratiOsnovniLayout().vratiResource());
		izborBrojaca = brojaci.getSelectionModel();
		izborBrojaca.setSelectionMode(SelectionMode.SINGLE);
		izborBrojaca.selectedItemProperty().addListener(new ChangeListener<FBrojac>() {
			@Override
			public void changed(ObservableValue<? extends FBrojac> observable, FBrojac oldValue, FBrojac newValue) {
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
		ResponseEntity<FBrojacOdgovor> odgovor = null;
		try {
			odgovor = restBrojac.lista(vratiPretplatnika());
			statusOdgovora(odgovor);
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	public void osveziTabelu(List<FBrojac> listaBrojaca) {
		if(brojaci.getItems() != null) {
			brojaci.getItems().clear();
		}
		ObservableList<FBrojac> lista = null;
		if(listaBrojaca != null) {
			lista = FXCollections.observableArrayList(listaBrojaca);
			postaviUkupno(listaBrojaca.size());
		}else {
			postaviUkupno(0);
		}
		brojaci.getSortOrder().clear();
		brojaci.setItems(lista);
		brojaci.refresh();
	}
	
	public void azurirajTabelu(Boolean snimi) {
		ResponseEntity<FBrojacOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restBrojac.snimi(pregled.preuzmiObjekat());
			}else {
				
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
			ResponseEntity<FBrojacOdgovor> odgovor = (ResponseEntity<FBrojacOdgovor>)odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			pregled.postaviNovo();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
