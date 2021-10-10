package rs.atekom.infosystem.desk.paneli.a.agencija;

import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.a.agencija.AAgencija;
import rs.atekom.infosystem.baza.a.agencija.AAgencijaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.AAgencijaRestKlijent;

public class AAgencijePanel extends OsnovniPanel{

	private AAgencijeTabela agencije;
	private TableViewSelectionModel<AAgencija> izborAgencije;
	private AAgencijaRestKlijent restAgencija;
	private AAgencijePregled pregled;
	
	public AAgencijePanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		}

	private void postaviElemente() {
		restAgencija = new AAgencijaRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomande();
		izbrisi.setOnAction(e -> {
			if(izborAgencije.getSelectedItem() != null) {
				osveziTabelu(izborAgencije.getSelectedItem(), false);
				pregled.postaviNovo();
				}else {
					pokaziGresku(vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje"), vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje.poruka"));
					}
			});
		izmeniSacuvaj.setOnAction(e -> {
			if(pregled.preuzmiObjekat().getNaziv() != null && !pregled.preuzmiObjekat().getNaziv().equals("")) {
				osveziTabelu(pregled.preuzmiObjekat(), true);
				pregled.postaviNovo();
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
		vratiRoot().getChildren().addAll(pregled, komande, agencije);
		VBox.setVgrow(agencije, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new AAgencijePregled(this, vratiOsnovniLayout().vratiResource());
		}

	private void postaviTabelu() {
		agencije = new AAgencijeTabela(vratiOsnovniLayout().vratiResource());
		izborAgencije = agencije.getSelectionModel();
		izborAgencije.setSelectionMode(SelectionMode.SINGLE);
		agencije.setRowFactory(tv -> {
			TableRow<AAgencija> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});
		}
	
	
	@Override
	public void popuniTabelu() {
		osveziTabelu(null, false);
		}
	
	private void osveziTabelu(AAgencija agencija, boolean snimi) {
		try {
			ResponseEntity<AAgencijaOdgovor> odgovor = null;
			if(agencija == null) {
				odgovor = restAgencija.pretraga(null);
				}else{
					if(snimi) {
						odgovor = restAgencija.snimi(agencija);
						}else {
							odgovor = restAgencija.brisi(agencija);
							}
					}
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}

		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		if(agencije.getItems() != null) {
			agencije.getItems().clear();
			}
		ObservableList<AAgencija> lista = null;
		ResponseEntity<AAgencijaOdgovor> odgovor = (ResponseEntity<AAgencijaOdgovor>)odg;
		lista = FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
		agencije.getSortOrder().clear();
		agencije.setItems(lista);
		agencije.refresh();
		}
	
	}
