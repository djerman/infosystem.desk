package rs.atekom.infosystem.desk.paneli.e.tipdokumenta;

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
import rs.atekom.infosystem.baza.e.tipdokumenta.ETipDokumenta;
import rs.atekom.infosystem.baza.e.tipdokumenta.ETipDokumentaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.ETipDokumentaRestKlijent;

public class ETipDokumentaPanel extends OsnovniPanel{

	public ETipDokumentaTabela tipovi;
	private TableViewSelectionModel<ETipDokumenta> izborTipa;
	private ETipDokumentaRestKlijent restTip;
	
	public ETipDokumentaPanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		}
	
	public void postaviElemente() {
		restTip = new ETipDokumentaRestKlijent(vratiRestServis());

		postaviInfo();
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(tipovi, info);
		
		VBox.setVgrow(tipovi, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviTabelu() {
		tipovi = new ETipDokumentaTabela(vratiOsnovniLayout().vratiResource());
		izborTipa = tipovi.getSelectionModel();
		izborTipa.setSelectionMode(SelectionMode.SINGLE);
		izborTipa.selectedItemProperty().addListener(new ChangeListener<ETipDokumenta>() {
			@Override
			public void changed(ObservableValue<? extends ETipDokumenta> observable, ETipDokumenta oldValue,
					ETipDokumenta newValue) {
				// TODO Auto-generated method stub
				}
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<ETipDokumentaOdgovor> odgovor = null;
		try {
			odgovor = restTip.pretraga(null, null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<ETipDokumenta> listaTipova) {
		if(tipovi.getItems() != null) {
			tipovi.getItems().clear();
			}
		ObservableList<ETipDokumenta> lista = null;
		if(listaTipova != null) {
			lista = FXCollections.observableArrayList(listaTipova);
			postaviUkupno(listaTipova.size());
			}else {
				postaviUkupno(0);
				}
		tipovi.getSortOrder().clear();
		tipovi.setItems(lista);
		tipovi.refresh();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<ETipDokumentaOdgovor> odgovor = odg == null ? null : (ResponseEntity<ETipDokumentaOdgovor>)odg;
			osveziTabelu(odgovor == null ? null : odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				// TODO: handle exception
				}
		}
	
	}
