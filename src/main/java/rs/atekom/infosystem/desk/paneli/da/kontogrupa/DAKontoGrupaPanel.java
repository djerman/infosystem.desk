package rs.atekom.infosystem.desk.paneli.da.kontogrupa;

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
import rs.atekom.infosystem.baza.da.kontogrupa.DAKontoGrupa;
import rs.atekom.infosystem.baza.da.kontogrupa.DAKontoGrupaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.DAKontoGrupaRestKlijent;

public class DAKontoGrupaPanel extends OsnovniPanel{

	private DAKontoGrupaTabela grupe;
	private TableViewSelectionModel<DAKontoGrupa> izborGrupe;
	private DAKontoGrupaRestKlijent restGrupa;
	
	public DAKontoGrupaPanel(OsnovniLayout ol) {
		super(ol);
		}
	
	@Override
	public void postaviElemente() {
		restGrupa = new DAKontoGrupaRestKlijent(vratiRestServis());
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(grupe);
		VBox.setVgrow(grupe, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviTabelu() {
		grupe = new DAKontoGrupaTabela(vratiOsnovniLayout().vratiResource());
		izborGrupe = grupe.getSelectionModel();
		izborGrupe.setSelectionMode(SelectionMode.SINGLE);
		izborGrupe.selectedItemProperty().addListener(new ChangeListener<DAKontoGrupa>() {
			@Override
			public void changed(ObservableValue<? extends DAKontoGrupa> observable, DAKontoGrupa oldValue,
					DAKontoGrupa newValue) {
				// TODO Auto-generated method stub
				}
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<DAKontoGrupaOdgovor> odgovor = null;
		try {
			odgovor = restGrupa.pretraga(null, null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<DAKontoGrupa> listaGrupa) {
		if(grupe.getItems() != null) {
			grupe.getItems().clear();
			}
		ObservableList<DAKontoGrupa> lista = null;
		if(listaGrupa != null) {
			lista = FXCollections.observableArrayList(listaGrupa);
			}
		grupe.getSortOrder();
		grupe.setItems(lista);
		grupe.refresh();
		}
	
	/*
	private void azurirajTabelu() {
		ResponseEntity<DAKontoGrupaOdgovor> odgovor = null;
		try {
			odgovor = restGrupa.pretraga(null, null);
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
			ResponseEntity<DAKontoGrupaOdgovor> odgovor = odg == null ? null : (ResponseEntity<DAKontoGrupaOdgovor>) odg;
			osveziTabelu(odgovor == null ? null : odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				// TODO: handle exception
				}
		}
	
	}
