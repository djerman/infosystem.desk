package rs.atekom.infosystem.desk.paneli.a.jedinica;

import org.springframework.http.ResponseEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMereOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.AJedinicaMereRestKlijent;

public class AJedinicePanel extends OsnovniPanel{

	private AJediniceTabela jedinice;
	private TableViewSelectionModel<AJedinicaMere> izborJedinice;
	private AJedinicaMereRestKlijent restJedinica;
	private AJedinicePregled pregled;
	
	public AJedinicePanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		}
	
	private void postaviElemente() {
		restJedinica = new AJedinicaMereRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborJedinice.getSelectedItem() != null) {
				osveziTabelu(izborJedinice.getSelectedItem(), false);
				pregled.postaviNovo();
				}else {
					pokaziGresku(vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje"), 
							vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje.poruka"));
					}
			});
		sacuvaj.setOnAction(e -> {
			if(pregled.provera(pregled.preuzmiObjekat())) {
				osveziTabelu(pregled.preuzmiObjekat(), true);
				}else {
					pokaziObaveznaPolja(vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja"), 
							vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja.obavestenje"));
					}
			});
		novo.setOnAction(e -> {
			pregled.postaviNovo();
			izborJedinice.clearSelection();
			});
		
		postaviTabelu();
		popuniTabelu();
		vratiRoot().getChildren().addAll(pregled, komande, jedinice);
		VBox.setVgrow(jedinice, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new AJedinicePregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		jedinice = new AJediniceTabela(vratiOsnovniLayout().vratiResource());
		izborJedinice = jedinice.getSelectionModel();
		izborJedinice.setSelectionMode(SelectionMode.SINGLE);
		izborJedinice.selectedItemProperty().addListener(new ChangeListener<AJedinicaMere>() {
			@Override
			public void changed(ObservableValue<? extends AJedinicaMere> observable, AJedinicaMere oldValue, AJedinicaMere newValue) {
				if(newValue != null) {
					pregled.postaviObjekat(newValue);
					}else {
						pregled.postaviNovo();
						}
				}
			});
		
		/*
		jedinice.setRowFactory(tv -> {
			TableRow<AJedinicaMere> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});*/
		}
	
	@Override
	public void popuniTabelu() {
		osveziTabelu(null, false);
		}
	
	private void osveziTabelu(AJedinicaMere jedinica, boolean snimi) {
		try {
			ResponseEntity<AJedinicaMereOdgovor> odgovor = null;
			if(jedinica == null) {
				odgovor = restJedinica.lista();
				}else {
					if(snimi) {
						odgovor = restJedinica.snimi(jedinica);
						}else {
							odgovor = restJedinica.brisi(jedinica);
							}
					}
			statusOdgovora(odgovor);
		}catch (Exception e) {
			System.out.println("odgovor greška ");
			vratiNemaOdgovoraServera();
			}
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<AJedinicaMereOdgovor> odgovor = (ResponseEntity<AJedinicaMereOdgovor>)odg;
			if(jedinice.getItems() != null) {
				jedinice.getItems().clear();
				}
			ObservableList<AJedinicaMere> lista = null;
			lista = FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			jedinice.getSortOrder().clear();
			jedinice.setItems(lista);
			jedinice.refresh();
			pregled.postaviNovo();
			}catch (Exception e) {
				// TODO: handle exception
				}
		}
	
	}
