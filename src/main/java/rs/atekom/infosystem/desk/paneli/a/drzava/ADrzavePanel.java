package rs.atekom.infosystem.desk.paneli.a.drzava;

import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.ADrzavaRestKlijent;

public class ADrzavePanel extends OsnovniPanel{

	private ADrzaveTabela drzave;
	private TableViewSelectionModel<ADrzava> izborDrzave;
	private ADrzavaRestKlijent restDrzava;
	private ADrzavePregled pregled;
	
	public ADrzavePanel(OsnovniLayout ol) {
		super(ol);
		postaviElemente();
		}
	
	private void postaviElemente() {
		restDrzava = new ADrzavaRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborDrzave.getSelectedItem() != null) {
				osveziTabelu(izborDrzave.getSelectedItem(), false);
				pregled.postaviNovo();
				}else {
					pokaziGresku(vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje"), vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje.poruka"));
					}
			});
		
		sacuvaj.setOnAction(e -> {
			if(pregled.preuzmiObjekat().getNaziv() != null && !pregled.preuzmiObjekat().getNaziv().equals("")) {
				osveziTabelu(pregled.preuzmiObjekat(), true);
				pregled.postaviNovo();
				}else {
					pokaziObaveznaPolja(vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja"), 
							vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja.obavestenje"));
					}
			});
		
		novo.setOnAction(e -> {
			pregled.postaviNovo();
			izborDrzave.clearSelection();
			});
		
		postaviTabelu();
		popuniTabelu();
		vratiRoot().getChildren().addAll(pregled, komande, drzave);
		VBox.setVgrow(drzave, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new ADrzavePregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		drzave = new ADrzaveTabela(vratiOsnovniLayout().vratiResource());
		izborDrzave = drzave.getSelectionModel();
		izborDrzave.setSelectionMode(SelectionMode.SINGLE);
		drzave.setRowFactory(tv -> {
			TableRow<ADrzava> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 2 && !row.isEmpty() && row != null) {
					
					}
				pregled.postaviObjekat(row.getItem());
				});
			return row;
			});
		}
	
	@Override
	public void popuniTabelu() {
		osveziTabelu(null, false);
		}
	
	private void osveziTabelu(ADrzava drzava, boolean snimi) {
		try {
			ResponseEntity<ADrzavaOdgovor> odgovor = null;
			if(drzava == null) {
				odgovor = restDrzava.pretraga(null);
				}else {
					if(snimi) {
						odgovor = restDrzava.snimi(drzava);
						}else {
							odgovor = restDrzava.brisi(drzava);
							}
					}
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
			ResponseEntity<ADrzavaOdgovor> odgovor = (ResponseEntity<ADrzavaOdgovor>)odg;
			if(drzave.getItems() != null) {
				drzave.getItems().clear();
				}
			ObservableList<ADrzava> lista = FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
			drzave.getSortOrder().clear();
			drzave.setItems(lista);
			drzave.refresh();
			}catch (Exception e) {
				// TODO: handle exception
				}
		}
	
	}
