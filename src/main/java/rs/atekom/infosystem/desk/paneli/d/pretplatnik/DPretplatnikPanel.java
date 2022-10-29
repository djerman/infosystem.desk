package rs.atekom.infosystem.desk.paneli.d.pretplatnik;

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
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnikOdgovor;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnikPodaci;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.DPretplatnikRestKlijent;

public class DPretplatnikPanel extends OsnovniPanel{

	private DPretplatnikTabela pretplatnici;
	private TableViewSelectionModel<DPretplatnikPodaci> izborPretplatnika;
	private DPretplatnikRestKlijent restPretplatnik;
	private DPretplatnikPregled pregled;
	
	public DPretplatnikPanel(OsnovniLayout ol) {
		super(ol);
		}
	
	@Override
	public void postaviElemente() {
		restPretplatnik = new DPretplatnikRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborPretplatnika.getSelectedItem() != null) {
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
			izborPretplatnika.clearSelection();
			});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, pretplatnici);
		VBox.setVgrow(pretplatnici, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new DPretplatnikPregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		pretplatnici = new DPretplatnikTabela(vratiOsnovniLayout().vratiResource());
		izborPretplatnika = pretplatnici.getSelectionModel();
		izborPretplatnika.setSelectionMode(SelectionMode.SINGLE);
		izborPretplatnika.selectedItemProperty().addListener(new ChangeListener<DPretplatnikPodaci>() {
			@Override
			public void changed(ObservableValue<? extends DPretplatnikPodaci> observable, DPretplatnikPodaci oldValue, DPretplatnikPodaci newValue) {
				if(newValue != null) {
					pregled.postaviObjekat(newValue);
					}else {
						pregled.postaviNovo();
						}
				}
			});
		/*
		pretplatnici.setRowFactory(tv -> {
			TableRow<DPretplatnikPodaciOdgovor> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});*/
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<DPretplatnikOdgovor> odgovor = null;
		try {
			odgovor = restPretplatnik.pretraga(null, null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<DPretplatnikPodaci> listaPretplatnika) {
		if(pretplatnici.getItems() != null) {
			pretplatnici.getItems().clear();
			}
		ObservableList<DPretplatnikPodaci> lista = null;
		if(listaPretplatnika != null) {
			lista = FXCollections.observableArrayList(listaPretplatnika);
			}
		pretplatnici.getSortOrder().clear();
		pretplatnici.setItems(lista);
		pretplatnici.refresh();
		}
	
	public DPretplatnikRestKlijent vratiRestPretplatnika() {
		return restPretplatnik;
		}
	
	private void azurirajTabelu(Boolean snimi) {
		ResponseEntity<DPretplatnikOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restPretplatnik.snimi(pregled.preuzmiObjekat());
				}else {
					odgovor = restPretplatnik.brisi(izborPretplatnika.getSelectedItem().getPretplatnik());
					}
			statusOdgovora(odgovor);
			pregled.postaviNovo();
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<DPretplatnikOdgovor> odgovor = odg == null ? null : (ResponseEntity<DPretplatnikOdgovor>) odg;
			osveziTabelu(odgovor == null ? null : odgovor.getBody() == null ? null : odgovor.getBody().getListaSaPodacima());
			}catch (Exception e) {
				e.printStackTrace();
				}
		}
	
	}
