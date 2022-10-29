package rs.atekom.infosystem.desk.paneli.e.konto;

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
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.e.konto.EKontoOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.EKontoRestKlijent;

public class EKontoPanel extends OsnovniPanel{

	private EKontoTabela konta;
	private TableViewSelectionModel<EKonto> izborKonta;
	private EKontoRestKlijent restKonto;
	
	public EKontoPanel(OsnovniLayout ol) {
		super(ol);
		}
	
	@Override
	public void postaviElemente() {
		restKonto = new EKontoRestKlijent(vratiRestServis());
		
		postaviInfo();
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(konta, info);
		
		VBox.setVgrow(konta, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviTabelu() {
		konta = new EKontoTabela(vratiOsnovniLayout().vratiResource());
		izborKonta = konta.getSelectionModel();
		izborKonta.setSelectionMode(SelectionMode.SINGLE);
		izborKonta.selectedItemProperty().addListener(new ChangeListener<EKonto>() {
			@Override
			public void changed(ObservableValue<? extends EKonto> observable, EKonto oldValue, EKonto newValue) {
				// TODO Auto-generated method stub
				}
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<EKontoOdgovor> odgovor = null;
		try {
			odgovor = restKonto.pretraga(null, null, null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<EKonto> listaKonta) {
		if(konta.getItems() != null) {
			konta.getItems().clear();
			}
		ObservableList<EKonto> lista = null;
		if(listaKonta != null) {
			lista = FXCollections.observableArrayList(listaKonta);
			postaviUkupno(listaKonta.size());
			}else {
				postaviUkupno(0);
				}
		konta.getSortOrder().clear();
		konta.setItems(lista);
		konta.refresh();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<EKontoOdgovor> odgovor = odg == null ? null : (ResponseEntity<EKontoOdgovor>)odg;
			osveziTabelu(odgovor == null ? null : odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				// TODO: handle exception
				}
		}
	
	}
