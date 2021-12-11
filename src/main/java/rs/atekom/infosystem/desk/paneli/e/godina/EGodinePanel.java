package rs.atekom.infosystem.desk.paneli.e.godina;

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
import rs.atekom.infosystem.baza.e.godina.EGodina;
import rs.atekom.infosystem.baza.e.godina.EGodinaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.DPretplatnikRestKlijent;
import rs.atekom.infosystem.desk.app.rest.EGodinaRestKlijent;

public class EGodinePanel extends OsnovniPanel{

	private EGodineTabela godine;
	private TableViewSelectionModel<EGodina> izborGodine;
	private DPretplatnikRestKlijent restPretplatnik;
	private EGodinaRestKlijent restGodina;
	private EGodinePregled pregled;
	
	public EGodinePanel(OsnovniLayout ol) {
		super(ol);
		postaviElmente();
		pregled.postaviSirinu();
		}
	
	private void postaviElmente() {
		restGodina = new EGodinaRestKlijent(vratiRestServis());
		restPretplatnik = new DPretplatnikRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborGodine.getSelectedItem() != null) {
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
			izborGodine.clearSelection();
			});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, godine);
		VBox.setVgrow(godine, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new EGodinePregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		godine = new EGodineTabela(vratiOsnovniLayout().vratiResource());
		izborGodine = godine.getSelectionModel();
		izborGodine.setSelectionMode(SelectionMode.SINGLE);
		izborGodine.selectedItemProperty().addListener(new ChangeListener<EGodina>() {
			@Override
			public void changed(ObservableValue<? extends EGodina> observable, EGodina oldValue, EGodina newValue) {
				if(newValue != null) {
					pregled.postaviObjekat(newValue);
					}else {
						pregled.postaviNovo();
						}
				}
			});
		/*
		godine.setRowFactory(tv -> {
			TableRow<EGodina> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});*/
		}
	
	@Override
	public void popuniTabelu() {
		godine.setItems(null);
		}
	
	public void osveziTabelu(List<EGodina> listaGodina) {
		if(godine.getItems() != null) {
			godine.getItems().clear();
			}
		ObservableList<EGodina> lista = null;
		if(listaGodina != null) {
			lista = FXCollections.observableArrayList(listaGodina);
			}
		godine.getSortOrder().clear();
		godine.setItems(lista);
		godine.refresh();
		}
	
	public DPretplatnikRestKlijent vratiRestPretplatnik() {
		return this.restPretplatnik;
		}
	
	public EGodinaRestKlijent vratiRestGodine() {
		return this.restGodina;
		}
	
	public void azurirajTabelu(Boolean snimi) {
		ResponseEntity<EGodinaOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restGodina.snimi(pregled.preuzmiObjekat());
				}else {
					odgovor = restGodina.brisi(izborGodine.getSelectedItem());
					}
			statusOdgovora(odgovor);
			/*
			if(odgovor != null) {
				switch(odgovor.getStatusCodeValue()) {
				case Odgovori.IZVRSEN:
					osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
					pregled.postaviNovo();
					break;
				default:
					pokaziNijeUspesno(vratiOsnovniLayout().vratiPrevod("obavestenja.nijeuspesno"), 
							vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
					}
				}else {
					vratiNemaOdgovoraServera();
				}
				*/
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		ResponseEntity<EGodinaOdgovor> odgovor = (ResponseEntity<EGodinaOdgovor>) odg;
		osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
		pregled.postaviNovo();
		}
	
	}
