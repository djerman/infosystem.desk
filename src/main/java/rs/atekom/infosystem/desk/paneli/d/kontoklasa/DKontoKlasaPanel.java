package rs.atekom.infosystem.desk.paneli.d.kontoklasa;

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
import rs.atekom.infosystem.baza.d.kontoklasa.DKontoKlasa;
import rs.atekom.infosystem.baza.d.kontoklasa.DKontoKlasaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.DKontoKlasaRestKlijent;

public class DKontoKlasaPanel extends OsnovniPanel{

	private DKontoKlasaTabela klase;
	private TableViewSelectionModel<DKontoKlasa> izborKlase;
	private DKontoKlasaRestKlijent restKlasa;
	
	public DKontoKlasaPanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		}
	
	private void postaviElemente() {
		restKlasa = new DKontoKlasaRestKlijent(vratiRestServis());
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(klase);
		VBox.setVgrow(klase, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviTabelu() {
		klase = new DKontoKlasaTabela(vratiOsnovniLayout().vratiResource());
		izborKlase = klase.getSelectionModel();
		izborKlase.setSelectionMode(SelectionMode.SINGLE);
		izborKlase.selectedItemProperty().addListener(new ChangeListener<DKontoKlasa>() {
			@Override
			public void changed(ObservableValue<? extends DKontoKlasa> observable, DKontoKlasa oldValue, DKontoKlasa newValue) {
				// TODO Auto-generated method stub
				}
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<DKontoKlasaOdgovor> odgovor = null;
		try {
			odgovor = restKlasa.pretraga(null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<DKontoKlasa> listaKlasa) {
		if(klase.getItems() != null) {
			klase.getItems().clear();
			}
		ObservableList<DKontoKlasa> lista = null;
		if(listaKlasa != null) {
			lista = FXCollections.observableArrayList(listaKlasa);
			}
		klase.getSortOrder().clear();
		klase.setItems(lista);
		klase.refresh();
		}
	/*
	private void azurirajTabelu() {
		ResponseEntity<DKontoKlasaOdgovor> odgovor = null;
		try {
			odgovor = restKlasa.pretraga(null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<DKontoKlasaOdgovor> odgovor = odg == null ? null : (ResponseEntity<DKontoKlasaOdgovor>) odg;
			osveziTabelu(odgovor == null ? null : odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				e.printStackTrace();
				}
		}
	
	}
