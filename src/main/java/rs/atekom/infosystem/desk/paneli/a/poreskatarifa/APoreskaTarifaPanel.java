package rs.atekom.infosystem.desk.paneli.a.poreskatarifa;

import org.springframework.http.ResponseEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifa;
import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.APoreskaTarifaRestKlijent;

public class APoreskaTarifaPanel extends OsnovniPanel{

	private APoreskaTarifaTabela tarife;
	private APoreskaTarifaRestKlijent restTarifa;
	
	public APoreskaTarifaPanel(OsnovniLayout ol) {
		super(ol);
		}
	
	@Override
	public void postaviElemente() {
		restTarifa = new APoreskaTarifaRestKlijent(vratiRestServis());
		
		postaviInfo();
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(tarife, info);
		
		VBox.setVgrow(tarife, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviTabelu() {
		tarife = new APoreskaTarifaTabela(vratiOsnovniLayout().vratiResource());
		}
	
	@Override
	public void popuniTabelu() {
		osveziTabelu();
		}
	
	private void osveziTabelu() {
		try {
			ResponseEntity<APoreskaTarifaOdgovor> odgovor = restTarifa.lista();
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
			ResponseEntity<APoreskaTarifaOdgovor> odgovor = (ResponseEntity<APoreskaTarifaOdgovor>)odg;
			if(tarife.getItems() != null) {
				tarife.getItems().clear();
				}
			ObservableList<APoreskaTarifa> lista = FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			postaviUkupno(odgovor.getBody() == null ? 0 : odgovor.getBody().getLista() == null ? 0 : odgovor.getBody().getLista().size());
			tarife.getSortOrder().clear();
			tarife.setItems(lista);
			tarife.refresh();
			}catch (Exception e) {
				// TODO: handle exception
				}
		}
	}
