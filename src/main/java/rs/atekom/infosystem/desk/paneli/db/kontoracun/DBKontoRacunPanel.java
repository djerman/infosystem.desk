package rs.atekom.infosystem.desk.paneli.db.kontoracun;

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
import rs.atekom.infosystem.baza.db.kontoracun.DBKontoRacun;
import rs.atekom.infosystem.baza.db.kontoracun.DBKontoRacunOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.DBKontoRacunRestKlijent;

public class DBKontoRacunPanel extends OsnovniPanel{

	private DBKontoRacunTabela racuni;
	private TableViewSelectionModel<DBKontoRacun> izborRacuna;
	private DBKontoRacunRestKlijent restRacun;
	
	public DBKontoRacunPanel(OsnovniLayout ol) {
		super(ol);
		}
	
	@Override
	public void postaviElemente() {
		restRacun = new DBKontoRacunRestKlijent(vratiRestServis());
		
		postaviInfo();
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(racuni, info);
		VBox.setVgrow(racuni, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviTabelu() {
		racuni = new DBKontoRacunTabela(vratiOsnovniLayout().vratiResource());
		izborRacuna = racuni.getSelectionModel();
		izborRacuna.setSelectionMode(SelectionMode.SINGLE);
		izborRacuna.selectedItemProperty().addListener(new ChangeListener<DBKontoRacun>() {
			@Override
			public void changed(ObservableValue<? extends DBKontoRacun> observable, DBKontoRacun oldValue,
					DBKontoRacun newValue) {
				// TODO Auto-generated method stub
				}
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<DBKontoRacunOdgovor> odgovor = null;
		try {
			odgovor = restRacun.pretraga(null, null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<DBKontoRacun> listaRacuna) {
		if(racuni.getItems() != null) {
			racuni.getItems().clear();
			}
		ObservableList<DBKontoRacun> lista = null;
		if(listaRacuna != null) {
			lista = FXCollections.observableArrayList(listaRacuna);
			postaviUkupno(listaRacuna.size());
			}else {
				postaviUkupno(0);
				}
		
		racuni.getSortOrder().clear();
		racuni.setItems(lista);
		racuni.refresh();
		}
	/*
	private void azurirajTabelu() {
		ResponseEntity<DBKontoRacunOdgovor> odgovor = null;
		try {
			odgovor = restRacun.pretraga(null, null);
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
			ResponseEntity<DBKontoRacunOdgovor> odgovor = odg == null ? null : (ResponseEntity<DBKontoRacunOdgovor>) odg;
			osveziTabelu(odgovor == null ? null : odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				}
		}
	
	}
