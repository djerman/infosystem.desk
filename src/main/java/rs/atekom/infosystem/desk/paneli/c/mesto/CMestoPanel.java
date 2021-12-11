package rs.atekom.infosystem.desk.paneli.c.mesto;

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
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.baza.c.CMestoOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.ADrzavaRestKlijent;
import rs.atekom.infosystem.desk.app.rest.BOpstinaRestKlijent;
import rs.atekom.infosystem.desk.app.rest.CMestoRestKlijent;

public class CMestoPanel extends OsnovniPanel{

	private CMestoTabela mesta;
	private TableViewSelectionModel<CMesto> izborMesta;
	private ADrzavaRestKlijent restDrzava;
	private BOpstinaRestKlijent restOpstina;
	private CMestoRestKlijent restMesto;
	private CMestoPregled pregled;
	public boolean osveziDrzave;
	
	public CMestoPanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		pregled.postaviSirinu();
		}
	
	private void postaviElemente() {
		restDrzava = new ADrzavaRestKlijent(vratiRestServis());
		restOpstina = new BOpstinaRestKlijent(vratiRestServis());
		restMesto = new CMestoRestKlijent(vratiRestServis());
		osveziDrzave = true;
		postaviPregled();
		postaviKomandeSaPretragom();
		
		vratiPretragu().setOnAction(e -> {
			pretraga(vratiPretragu().getText().trim());
			});

		izbrisi.setOnAction(e -> {
			if(izborMesta.getSelectedItem() != null) {
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
			izborMesta.clearSelection();
			});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, mesta);
		VBox.setVgrow(mesta, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new CMestoPregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		mesta = new CMestoTabela(vratiOsnovniLayout().vratiResource());
		izborMesta = mesta.getSelectionModel();
		izborMesta.setSelectionMode(SelectionMode.SINGLE);
		izborMesta.selectedItemProperty().addListener(new ChangeListener<CMesto>() {
			@Override
			public void changed(ObservableValue<? extends CMesto> observable, CMesto oldValue, CMesto newValue) {
				if(newValue != null) {
					pregled.postaviObjekat(newValue);
					}else {
						pregled.postaviNovo();
						}
				}
			});
		/*
		mesta.setRowFactory(tv -> {
			TableRow<CMesto> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});*/
		}
	@Override
	public void popuniTabelu() {
		ResponseEntity<CMestoOdgovor> odgovor = null;
		try {
			odgovor = restMesto.pretraga(null);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		//mesta.setItems(null);
		}
	
	public void osveziTabelu(List<CMesto> listaMesta) {
		if(mesta.getItems() != null) {
			mesta.getItems().clear();
			}
		ObservableList<CMesto> lista = null;
		if(listaMesta != null) {
			lista = FXCollections.observableArrayList(listaMesta);
			}
		mesta.getSortOrder().clear();
		mesta.setItems(lista);
		mesta.refresh();
		}
	
	private void azurirajTabelu(Boolean snimi) {
		ResponseEntity<CMestoOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restMesto.snimi(pregled.preuzmiObjekat());
				}else {
					odgovor = restMesto.brisi(izborMesta.getSelectedItem());
					}
			statusOdgovora(odgovor);
			pregled.postaviNovo();
			}catch (Exception ee) {
				ee.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void pretraga(String pretraga) {
		try {
			ResponseEntity<CMestoOdgovor> odgovor = restMesto.pretraga(pretraga);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	public ADrzavaRestKlijent vratiRestDrzava() {
		return this.restDrzava;
		}
	
	public BOpstinaRestKlijent vratiRestOpstina() {
		return this.restOpstina;
		}
	
	public CMestoRestKlijent vratiRestMesto() {
		return this.restMesto;
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<CMestoOdgovor> odgovor = (ResponseEntity<CMestoOdgovor>)odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				try {
					ResponseEntity<ADrzavaOdgovor> odgovor = (ResponseEntity<ADrzavaOdgovor>)odg;
					pregled.postaviOpstine(FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getListaOpstina()));
					//osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getListaMesta());
					}catch (Exception ee) {
						ee.printStackTrace();
						}
				}
		}
	
	}
