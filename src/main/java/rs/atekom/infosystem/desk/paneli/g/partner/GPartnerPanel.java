package rs.atekom.infosystem.desk.paneli.g.partner;

import java.util.List;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.g.GPartnerOdgovor;
import rs.atekom.infosystem.baza.g.GPartnerOdgovorPodaci;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;
import rs.atekom.infosystem.desk.app.rest.GPartnerRestKlijent;

public class GPartnerPanel extends OsnovniPanel{

	private Boolean kupac;
	private GPartnerPregled pregled;
	private GPartnerRestKlijent restPartner;
	private GPartnerTabela partneri;
	private TableViewSelectionModel<GPartnerOdgovorPodaci> izborPartnera;
	
	public GPartnerPanel(OsnovniLayout ol, Boolean kupac) {
		super(ol);
		this.kupac = kupac;
		postaviElemente();
		}
	
	private void postaviElemente() {
		restPartner = new GPartnerRestKlijent(vratiRestServis());
		postaviPregled();
		postaviKomandeSaPretragom();
		
		izbrisi.setOnAction(e -> {
			if(izborPartnera.getSelectedItem() != null) {
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
			izborPartnera.clearSelection();
			});
		
		vratiPretragu().setOnAction(e -> {
			popuniTabeluPretragom(vratiPretragu().getText().trim());
		});
		
		postaviTabelu();
		popuniTabelu();
		vratiRoot().getChildren().addAll(pregled, komande, partneri);
		VBox.setVgrow(partneri, Priority.ALWAYS);
		setContent(vratiRoot());
		}
	
	private void postaviPregled() {
		pregled = new GPartnerPregled(this, vratiOsnovniLayout().vratiResource(), kupac);
		}
	
	private void postaviTabelu() {
		partneri = new GPartnerTabela(vratiOsnovniLayout().vratiResource());
		izborPartnera = partneri.getSelectionModel();
		izborPartnera.setSelectionMode(SelectionMode.SINGLE);
		/*
		izborPartnera.selectedItemProperty().addListener(new ChangeListener<GPartner>() {
			@Override
			public void changed(ObservableValue<? extends GPartner> observable, GPartner oldValue, GPartner newValue) {
				if(newValue != null) {
					pregled.postaviObjekat(newValue);
					}else {
						pregled.postaviNovo();
						}
				}
			});
		*/
		partneri.setRowFactory(tv -> {
			TableRow<GPartnerOdgovorPodaci> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(row.getItem() != null) {
					pregled.postaviObjekat(row.getItem());
					}else {
						pregled.postaviNovo();
						}
				});
			return row;
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<GPartnerOdgovor> odgovor = null;
		try {
			odgovor = restPartner.lista(null, vratiPretplatnika(), kupac);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void popuniTabeluPretragom(String pojam) {
		ResponseEntity<GPartnerOdgovor> odgovor = null;
		try {
			odgovor = restPartner.lista(pojam, vratiPretplatnika(), kupac);
			statusOdgovora(odgovor);
			}catch (Exception e) {
				e.printStackTrace();
				vratiNemaOdgovoraServera();
				}
		}
	
	private void osveziTabelu(List<GPartnerOdgovorPodaci> listaPartnera) {
		if(partneri.getItems() != null) {
			partneri.getItems().clear();
			}
		ObservableList<GPartnerOdgovorPodaci> lista = null;
		if(listaPartnera != null) {
			lista = FXCollections.observableArrayList(listaPartnera);
			}
		partneri.getSortOrder().clear();
		partneri.setItems(lista);
		partneri.refresh();
		}
	
	public GPartnerRestKlijent vratiRestPartnera() {
		return this.restPartner;
		}
	
	private void azurirajTabelu(Boolean snimi) {
		ResponseEntity<GPartnerOdgovor> odgovor = null;
		try {
			if(snimi) {
				odgovor = restPartner.snimi(pregled.preuzmiObjekat(), kupac);
				}else {
					odgovor = restPartner.brisi(izborPartnera.getSelectedItem().getPartner(), kupac);
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
		try {
			ResponseEntity<GPartnerOdgovor> odgovor = odg == null ? null : (ResponseEntity<GPartnerOdgovor>) odg;
			if(odgovor != null) {
				osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getListaSaPodacima());
				//System.out.println("lista je " + odgovor.getBody().getListaSaPodacima().size() + " mesta " + odgovor.getBody().getMesta().size() + " grupe " + odgovor.getBody().getGrupe().size());
				pregled.popuniGrupe(odgovor.getBody() == null ? null : odgovor.getBody().getGrupe());
				pregled.popuniMesta(odgovor.getBody() == null ? null : odgovor.getBody().getMesta());
				pregled.popuniKonta(odgovor.getBody() == null ? null : odgovor.getBody().getKonta());
				}else {
					pregled.popuniGrupe(null);
					pregled.popuniMesta(null);
					pregled.popuniKonta(null);
					osveziTabelu(null);
					}
			}catch (Exception e) {
				e.printStackTrace();
				}
		pregled.postaviNovo();
		}
	
	}
