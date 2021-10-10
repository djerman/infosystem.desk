package rs.atekom.infosystem.desk.paneli.b.opstina;

import java.util.List;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.baza.b.BOpstinaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.ADrzavaRestKlijent;
import rs.atekom.infosystem.desk.app.rest.BOpstinaRestKlijent;

public class BOpstinePanel extends OsnovniPanel{

	private BOpstineTabela opstine;
	private TableViewSelectionModel<BOpstina> izborOpstine;
	private BOpstinaRestKlijent restOpstina;
	private ADrzavaRestKlijent restDrzava;
	private BOpstinePregled pregled;
	
	public BOpstinePanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		pregled.postaviSirinu();
		}
	
	private void postaviElemente() {
		restOpstina = new BOpstinaRestKlijent(vratiRestServis());
		restDrzava = new ADrzavaRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborOpstine.getSelectedItem() != null) {
				azurirajTabelu(false);
				}else {
					pokaziGresku(vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje"), vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje.poruka"));
					}
			});
		
		izmeniSacuvaj.setOnAction(e -> {
			if(pregled.proveraUnosa()) {
				azurirajTabelu(true);
				}else {
					pokaziObaveznaPolja(vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja"), 
							vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja.obavestenje"));
					}
			});
		
		dodaj.setOnAction(e -> {
			pregled.postaviNovo();
			});
		
		postaviTabelu();
		popuniTabelu();
		
		vratiRoot().getChildren().addAll(pregled, komande, opstine);
		VBox.setVgrow(opstine, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new BOpstinePregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		opstine = new BOpstineTabela(vratiOsnovniLayout().vratiResource());
		izborOpstine = opstine.getSelectionModel();
		izborOpstine.setSelectionMode(SelectionMode.SINGLE);
		
		opstine.setRowFactory(tv -> {
			TableRow<BOpstina> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});
		
		}
	
	@Override
	public void popuniTabelu() {
		opstine.setItems(null);
		}
	
	public void osveziTabelu(List<BOpstina> listaOpstina) {
		if(opstine.getItems() != null) {
			opstine.getItems().clear();
			}
		ObservableList<BOpstina> lista = null;
		if(listaOpstina != null) {
			lista = FXCollections.observableArrayList(listaOpstina);
			}
		opstine.getSortOrder().clear();
		opstine.setItems(lista);
		opstine.refresh();
		}
	
	public ADrzavaRestKlijent vratiRestDrzava() {
		return this.restDrzava;
		}
	
	public BOpstinaRestKlijent vratiRestOpstina() {
		return this.restOpstina;
		}
	
	private void azurirajTabelu(Boolean snimi) {
		ResponseEntity<BOpstinaOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restOpstina.snimi(pregled.preuzmiObjekat());
				}else {
					odgovor = restOpstina.brisi(izborOpstine.getSelectedItem());
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
			ResponseEntity<BOpstinaOdgovor> odgovor = (ResponseEntity<BOpstinaOdgovor>) odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			}catch (Exception e) {
				//
				}
		}
	
	}
