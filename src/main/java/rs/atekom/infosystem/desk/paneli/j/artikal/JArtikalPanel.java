package rs.atekom.infosystem.desk.paneli.j.artikal;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.j.JArtikal;
import rs.atekom.infosystem.baza.j.JArtikalOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.JArtikalRestKlijent;

public class JArtikalPanel extends OsnovniPanel{

	private JArtikalTabela artikli;
	private TableViewSelectionModel<JArtikal> izborArtikla;
	private JArtikalRestKlijent restArtikal;
	private JArtikalPregled pregled;
	
	public JArtikalPanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
	}
	
	private void postaviElemente() {
		restArtikal = new JArtikalRestKlijent(vratiRestServis());
		
		postaviInfo();
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborArtikla.getSelectedItem() != null) {
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
			izborArtikla.clearSelection();
		});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, artikli, info);
		VBox.setVgrow(artikli, Priority.ALWAYS);
		setContent(vratiRoot());
	}
	
	private void postaviPregled() {
		pregled = new JArtikalPregled(this, vratiOsnovniLayout().vratiResource());
	}

	private void postaviTabelu() {
		artikli = new JArtikalTabela(vratiOsnovniLayout().vratiResource());
		izborArtikla = artikli.getSelectionModel();
		izborArtikla.setSelectionMode(SelectionMode.SINGLE);
		izborArtikla.selectedItemProperty().addListener(new ChangeListener<JArtikal>() {
			@Override
			public void changed(ObservableValue<? extends JArtikal> observable, JArtikal oldValue, JArtikal newValue) {
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
		ResponseEntity<JArtikalOdgovor> odgovor = null;
		try {
			odgovor = restArtikal.lista(vratiPretplatnika(), null, Optional.of(1), 1);
			statusOdgovora(odgovor);
			if(odgovor != null && odgovor.getBody() != null) {
				pregled.setJedinice(odgovor.getBody().getJedinice());
				pregled.setTarife(odgovor.getBody().getTarife());
				pregled.setGrupe(odgovor.getBody().getGrupe());
				pregled.setKonta(odgovor.getBody().getKonta());
			}
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	public void osveziTabelu(List<JArtikal> listaArtikala) {
		if(artikli.getItems() != null)
			artikli.getItems().clear();
		ObservableList<JArtikal> lista = null;
		if(listaArtikala != null) {
			lista = FXCollections.observableArrayList(listaArtikala);
			postaviUkupno(listaArtikala.size());
		}else {
			postaviUkupno(0);
		}
		artikli.getSortOrder().clear();
		artikli.setItems(lista);
		artikli.refresh();
	}
	
	public void azurirajTabelu(Boolean snimi) {
		ResponseEntity<JArtikalOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restArtikal.snimi(pregled.preuzmiObjekat());
			}else {
				odgovor= restArtikal.brisi(izborArtikla.getSelectedItem());
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
			ResponseEntity<JArtikalOdgovor> odgovor = (ResponseEntity<JArtikalOdgovor>) odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getArtikli());
			pregled.postaviNovo();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
