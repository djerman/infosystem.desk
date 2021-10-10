package rs.atekom.infosystem.desk.paneli.d.pretplatnik;

import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;
import org.springframework.http.ResponseEntity;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.baza.a.agencija.AAgencija;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.baza.d.DPodaciZaPretplatnikaOdgovor;
import rs.atekom.infosystem.baza.d.DPretplatnik;
import rs.atekom.infosystem.baza.d.DPretplatnikPodaciOdgovor;
import rs.atekom.infosystem.baza.e.EOrganizacija;
import rs.atekom.infosystem.baza.i.IAdresa;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;
import rs.atekom.infosystem.desk.app.pomocne.TekstCelobrojni;
import rs.atekom.infosystem.desk.paneli.c.mesto.CMestoComboBox;
import rs.atekom.infosystem.desk.paneli.h.bankovniracun.HBankovniRacunTabela;

public class DPretplatnikPregled extends HBox{

	private Label lblAgencija, lblNaziv, lblPunNaziv, lblMesto, lblAdresa, lblOpstina, lblPib, lblMb, lblTelefon, lblFax, lblSifraDelatnosti, lblEmail, lblOdgovornoLice, 
	lblNadlezanSud, lblNapomena;
	private Label lblObveznikPDV, lblAktivan;
	private Label lblPoreskiPeriod, lblPoreskaUprava, lblVrstaVlasnistva, lblVrstaPosla, lblVelicinaObveznika, lblVrstaIzvestaja, lblRacunovodstvenaRegulativa;
	private VBox desnoVBox, osnovnoVbox;
	private TextField /*txtAgencija, */txtNaziv, txtPunNaziv, txtAdresa, txtOpstina, txtPib, txtMb, txtTelefon, txtFax, txtSifraDelatnosti, txtEmail, txtOdgovornoLice, 
	txtNadlezniSud, txtNapomena, txtPoreskaUprava;
	private CheckBox cbObveznikPdv, cbAktivan;
	private TekstCelobrojni txtPoreskiPeriod, txtVrstaVlasnistva, txtVrstaPosla, txtVelicinaObveznika, txtVrstaIzvestaja, txtRacunovodstvenaRegulativa;
	private GridPane noseci, osnovno, ostalo;
	private HBankovniRacunTabela racuni;
	private DPretplatnikPanel panel;
	private DPretplatnik pretplatnik;
	private DPretplatnikPodaciOdgovor pretplatnikPodaci;
	private EOrganizacija organizacija;
	private IAdresa adresa;
	private CMestoComboBox cbMesto;
	private SearchableComboBox<AAgencija> cbAgencija;
	
	public DPretplatnikPregled(DPretplatnikPanel panel, ResourceBundle resource) {
		setSpacing(20);
		this.panel = panel;
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		noseci = new GridPane();
		noseci.setHgap(20);
		ColumnConstraints ccOsnovno = new ColumnConstraints();
		ccOsnovno.setHalignment(HPos.CENTER);
		ccOsnovno.setPercentWidth(40);
		ColumnConstraints ccDesno = new ColumnConstraints();
		ccDesno.setHalignment(HPos.CENTER);
		ccDesno.setPercentWidth(60);
        noseci.getColumnConstraints().addAll(ccOsnovno, ccDesno);
		
		osnovnoVbox = new VBox(5);
		desnoVBox = new VBox(5);
		
		osnovno = new GridPane();
		osnovno.setVgap(5);
		osnovno.setHgap(5);
		osnovnoVbox.getChildren().add(osnovno);
		HBox.setHgrow(osnovno, Priority.ALWAYS);
		
		ostalo = new GridPane();
		ostalo.setVgap(5);
		ostalo.setHgap(5);
		racuni = new HBankovniRacunTabela(resource);
		racuni.setMaxHeight(100);
		//racuni.setMaxWidth(200);
		
		lblAgencija = new Label(resource.getString("lbl.agencija"));
		lblNaziv = new Label(resource.getString("lbl.naziv"));
		lblNaziv.setStyle("-fx-text-fill: red");
		lblPunNaziv = new Label(resource.getString("lbl.punnaziv"));
		lblPunNaziv.setStyle("-fx-text-fill: red");
		lblAdresa = new Label(resource.getString("lbl.adresa"));
		lblAdresa.setStyle("-fx-text-fill: red");
		lblMesto = new Label(resource.getString("lbl.mesto"));
		lblMesto.setStyle("-fx-text-fill: red");
		lblOpstina = new Label(resource.getString("lbl.opstina"));
		lblPib = new Label(resource.getString("lbl.pibbpg"));
		lblPib.setStyle("-fx-text-fill: red");
		lblMb = new Label(resource.getString("lbl.mbjmbg"));
		lblMb.setStyle("-fx-text-fill: red");
		lblTelefon = new Label(resource.getString("lbl.telefon"));
		lblFax = new Label(resource.getString("lbl.fax"));
		lblSifraDelatnosti = new Label(resource.getString("lbl.sifradelatnosti"));
		lblSifraDelatnosti.setStyle("-fx-text-fill: red");
		lblEmail = new Label(resource.getString("lbl.email"));
		lblEmail.setStyle("-fx-text-fill: red");
		lblOdgovornoLice = new Label(resource.getString("lbl.odgovornolice"));
		lblOdgovornoLice.setStyle("-fx-text-fill: red");
		lblNadlezanSud = new Label(resource.getString("lbl.nadleznisud"));
		lblNapomena = new Label(resource.getString("lbl.napomena"));
		lblObveznikPDV = new Label(resource.getString("lbl.obveznikpdv"));
		lblAktivan = new Label(resource.getString("lbl.aktivan"));
		lblPoreskiPeriod = new Label(resource.getString("lbl.poreskiperiod"));
		lblPoreskiPeriod.setStyle("-fx-text-fill: red");
		lblVrstaVlasnistva =  new Label(resource.getString("lbl.vrstavlasnistva"));
		lblVrstaPosla = new Label(resource.getString("lbl.vrstaposla"));
		lblVelicinaObveznika = new Label(resource.getString("lbl.velicinaobveznika"));
		lblVrstaIzvestaja = new Label(resource.getString("lbl.vrstaizvestaja"));
		lblRacunovodstvenaRegulativa = new Label(resource.getString("lbl.racunregulativa"));
		lblPoreskaUprava = new Label(resource.getString("lbl.poreskauprava"));
		
		//txtAgencija = new TextField();
		cbAgencija = new SearchableComboBox<AAgencija>();
		txtNaziv = new TextField();
		txtPunNaziv = new TextField();
		cbMesto = new CMestoComboBox(resource);
		txtAdresa = new TextField();
		txtOpstina = new TextField();
		txtOpstina.setDisable(true);
		txtPib = new TextField();
		txtMb = new TextField();
		txtTelefon = new TextField();
		txtFax = new TextField();
		txtSifraDelatnosti = new TextField();
		txtEmail = new TextField();
		txtOdgovornoLice = new TextField();
		txtNadlezniSud = new TextField();
		txtNapomena = new TextField();
		cbObveznikPdv = new CheckBox();
		cbAktivan = new CheckBox();
		txtPoreskiPeriod = new TekstCelobrojni();
		txtVrstaVlasnistva = new TekstCelobrojni();
		txtVrstaPosla = new TekstCelobrojni();
		txtVelicinaObveznika = new TekstCelobrojni();
		txtVrstaIzvestaja = new TekstCelobrojni();
		txtRacunovodstvenaRegulativa = new TekstCelobrojni();
		txtPoreskaUprava = new TextField();

		}
	
	private void popakujElemente() {
		Double minSirina = 80.0;
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		//col2.setPercentWidth(50);
		osnovno.getColumnConstraints().addAll(col1, col2);
		
		ColumnConstraints col21 = new ColumnConstraints();
		col21.setHalignment(HPos.RIGHT);
		col21.setMinWidth(minSirina);
		ColumnConstraints col22 = new ColumnConstraints();
		ColumnConstraints col23 = new ColumnConstraints();
		col23.setHalignment(HPos.RIGHT);
		col23.setMinWidth(minSirina);
		ColumnConstraints col24 = new ColumnConstraints();
		ostalo.getColumnConstraints().addAll(col21, col22, col23, col24);
		
		if(panel.vratiOsnovniLayout().vratiKorisnika().getSistem()) {
			//osnovno.add(lblAgencija, 1, 1); osnovno.add(txtAgencija, 2, 1);
			osnovno.addColumn(0, lblAgencija); osnovno.addColumn(1, cbAgencija);
			}
		if(panel.vratiOsnovniLayout().vratiKorisnika().getAgencija() != null) {
			osnovno.addColumn(0, lblAgencija); osnovno.addColumn(1, cbAgencija);
			cbAgencija.setDisable(true);
			}
		osnovno.addColumn(0, lblNaziv); osnovno.addColumn(1, txtNaziv);
		osnovno.addColumn(0, lblPunNaziv); osnovno.addColumn(1, txtPunNaziv);
		osnovno.addColumn(0, lblMesto); osnovno.addColumn(1, cbMesto);
		/*cbMesto.setOnShowing(e -> osnovno.getColumnConstraints().get(1).setMaxWidth(cbMesto.getWidth()));
		osnovno.widthProperty().addListener(e -> {
			osnovno.getColumnConstraints().get(1).setMaxWidth(Double.MAX_VALUE);
			});*/
		osnovno.addColumn(0, lblAdresa); osnovno.addColumn(1, txtAdresa);
		osnovno.addColumn(0, lblOpstina); osnovno.addColumn(1, txtOpstina);
		osnovno.addColumn(0, lblTelefon); osnovno.addColumn(1, txtTelefon);
		osnovno.addColumn(0, lblFax); osnovno.addColumn(1, txtFax);
		osnovno.addColumn(0, lblOdgovornoLice); osnovno.addColumn(1, txtOdgovornoLice);
		osnovno.addColumn(0, lblEmail); osnovno.addColumn(1, txtEmail);
		osnovno.addColumn(0, lblNadlezanSud); osnovno.addColumn(1, txtNadlezniSud);
		osnovno.addColumn(0, lblNapomena); osnovno.addColumn(1, txtNapomena);
		
		ostalo.addColumn(0, lblObveznikPDV); ostalo.addColumn(1, cbObveznikPdv); ostalo.addColumn(2, lblPoreskiPeriod); ostalo.addColumn(3, txtPoreskiPeriod);
		ostalo.addColumn(0, lblPib); ostalo.addColumn(1, txtPib); ostalo.addColumn(2, lblMb); ostalo.addColumn(3, txtMb);
		ostalo.addColumn(0, lblSifraDelatnosti); ostalo.addColumn(1, txtSifraDelatnosti); ostalo.addColumn(2, lblPoreskaUprava); ostalo.addColumn(3, txtPoreskaUprava);
		ostalo.addColumn(0, lblVrstaVlasnistva); ostalo.addColumn(1, txtVrstaVlasnistva); ostalo.addColumn(2, lblVrstaPosla); ostalo.addColumn(3, txtVrstaPosla);
		ostalo.addColumn(0, lblVelicinaObveznika); ostalo.addColumn(1, txtVelicinaObveznika); ostalo.addColumn(2, lblVrstaIzvestaja); ostalo.addColumn(3, txtVrstaIzvestaja);
		ostalo.addColumn(0, lblRacunovodstvenaRegulativa); ostalo.addColumn(1, txtRacunovodstvenaRegulativa); ostalo.addColumn(2, lblAktivan); ostalo.addColumn(3, cbAktivan);
		
		GridPane.setHgrow(txtNaziv, Priority.ALWAYS);
		desnoVBox.getChildren().addAll(racuni, ostalo);
		//HBox.setHgrow(racuni, Priority.ALWAYS);
		GridPane.setHgrow(txtNaziv, Priority.ALWAYS);
		GridPane.setHgrow(txtPib, Priority.ALWAYS);
		GridPane.setHgrow(txtMb, Priority.ALWAYS);
		HBox.setHgrow(ostalo, Priority.ALWAYS);
		
		//osnovno.setMaxWidth(this.getMaxWidth()*0.4);
		//osnovnoVbox.getStyleClass().add(".sirina-pola");
		//desnoVBox.getStyleClass().add(".sirina-pola");
		noseci.addColumn(0, osnovnoVbox); noseci.addColumn(1, desnoVBox);
		getChildren().add(noseci);
		HBox.setHgrow(noseci, Priority.ALWAYS);

		try {
			ResponseEntity<DPodaciZaPretplatnikaOdgovor> odgovor = panel.vratiRestPretplatnika().podaci();
			if(odgovor != null) {
				switch(odgovor.getStatusCodeValue()) {
				case Odgovori.IZVRSEN:
					osveziAgencije(odgovor.getBody() == null ? null : odgovor.getBody().getAgencije());
					osveziMesta(odgovor.getBody() == null ? null : odgovor.getBody().getMesta());
					break;
				default: 
					panel.pokaziNijeUspesno(panel.vratiOsnovniLayout().vratiPrevod("obavestenja.nijeuspesno"), 
							panel.vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
				}
			}else {
				panel.vratiNemaOdgovoraServera();
				}
			}catch (Exception e) {
				panel.vratiNemaOdgovoraServera();
				}
		}
	
	public void postaviObjekat(DPretplatnikPodaciOdgovor pretplatnikPodaci) {
		if(pretplatnikPodaci != null) {
			this.pretplatnikPodaci = pretplatnikPodaci;
			this.pretplatnik = pretplatnikPodaci.getPretplatnik();
			this.organizacija = pretplatnikPodaci.getOrganizacija();
			this.adresa = organizacija.getAdresa();
			
			txtNaziv.setText(pretplatnik.getNaziv());
			txtPunNaziv.setText(pretplatnik.getPunNaziv());
			cbMesto.setValue(adresa == null ? null : adresa.getMesto());
			txtAdresa.setText(adresa == null ? null : adresa.getAdresa());
			txtOpstina.setText(adresa == null ? "" : adresa.getMesto() == null ? "" : adresa.getMesto().getOpstina().getNaziv());
			txtTelefon.setText(pretplatnik.getTelefon());
			txtFax.setText(pretplatnik.getFax());
			txtOdgovornoLice.setText(pretplatnik.getOdgovornoLice());
			txtEmail.setText(pretplatnik.getEmail());
			txtNadlezniSud.setText(pretplatnik.getNadlezniSud());
			txtNapomena.setText(pretplatnik.getNapomena());
			cbObveznikPdv.setSelected(pretplatnik.getObveznikPDV());
			txtPoreskiPeriod.setText(pretplatnik.getPoreskiPeriod().toString());
			txtPib.setText(pretplatnik.getPib());
			txtMb.setText(pretplatnik.getMb());
			txtSifraDelatnosti.setText(pretplatnik.getSifraDelatnosti());
			txtPoreskaUprava.setText(pretplatnik.getPoreskaUprava());
			txtVrstaVlasnistva.setText(pretplatnik.getVrstaVlasnistva().toString());
			txtVrstaPosla.setText(pretplatnik.getVrstaPosla().toString());
			txtVelicinaObveznika.setText(pretplatnik.getVelicinaObveznika().toString());
			txtVrstaIzvestaja.setText(pretplatnik.getVrstaIzvestaja().toString());
			txtRacunovodstvenaRegulativa.setText(pretplatnik.getRacunovodstvenaRegulativa().toString());
			cbAktivan.setSelected(pretplatnik.getAktivan());
			}else {
				postaviNovo();
				}
		}
	
	public DPretplatnikPodaciOdgovor preuzmiObjekat() {
		if(this.pretplatnik == null) {
			pretplatnikPodaci = new DPretplatnikPodaciOdgovor();
			pretplatnik = new DPretplatnik();
			pretplatnik.setId(0L);
			organizacija = new EOrganizacija();
			organizacija.setId(0L);
			organizacija.setNaziv("Sedište");
			adresa = new IAdresa();
			adresa.setId(0L);
			}
		if(this.organizacija == null) {
			organizacija = new EOrganizacija();
			organizacija.setId(0L);
			organizacija.setNaziv("Sedište");
			adresa = new IAdresa();
			adresa.setId(0L);
			}
		if(this.adresa == null) {
			adresa = new IAdresa();
			adresa.setId(0L);
			}
		pretplatnik.setNaziv(txtNaziv.getText());
		pretplatnik.setPunNaziv(txtPunNaziv.getText());
		
		adresa.setMesto(cbMesto.getValue());
		adresa.setAdresa(txtAdresa.getText().trim());
		
		pretplatnik.setTelefon(txtTelefon.getText());
		pretplatnik.setFax(txtFax.getText());
		pretplatnik.setOdgovornoLice(txtOdgovornoLice.getText());
		pretplatnik.setEmail(txtEmail.getText());
		pretplatnik.setNadlezniSud(txtNadlezniSud.getText());
		pretplatnik.setNapomena(txtNapomena.getText());
		pretplatnik.setObveznikPDV(cbObveznikPdv.isSelected());
		pretplatnik.setPoreskiPeriod(Integer.valueOf(txtPoreskiPeriod.getText()));
		pretplatnik.setPib(txtPib.getText());
		pretplatnik.setMb(txtMb.getText());
		pretplatnik.setSifraDelatnosti(txtSifraDelatnosti.getText());
		pretplatnik.setPoreskaUprava(txtPoreskaUprava.getText());
		pretplatnik.setVrstaVlasnistva(Integer.valueOf(txtVrstaVlasnistva.getText() == null ? "" : txtVrstaVlasnistva.getText()));
		pretplatnik.setVrstaPosla(Integer.valueOf(txtVrstaPosla.getText()));
		pretplatnik.setVelicinaObveznika(Integer.valueOf(txtVelicinaObveznika.getText()));
		pretplatnik.setVrstaIzvestaja(Integer.valueOf(txtVrstaIzvestaja.getText()));
		pretplatnik.setRacunovodstvenaRegulativa(Integer.valueOf(txtRacunovodstvenaRegulativa.getText()));
		pretplatnik.setAktivan(cbAktivan.isSelected());
		
		organizacija.setAdresa(adresa);
		pretplatnikPodaci.setOrganizacija(organizacija);
		pretplatnikPodaci.setPretplatnik(pretplatnik);
		return this.pretplatnikPodaci;
		}
	
	public void postaviNovo() {
		this.pretplatnik = null;
		txtNaziv.setText("");
		txtPunNaziv.setText("");
		txtAdresa.setText("");
		cbMesto.setValue(null);
		txtOpstina.setText("");
		txtTelefon.setText("");
		txtFax.setText("");
		txtOdgovornoLice.setText("");
		txtEmail.setText("");
		txtNadlezniSud.setText("");
		txtNapomena.setText("");
		cbObveznikPdv.setSelected(false);
		txtPoreskiPeriod.setText("");
		txtPib.setText("");
		txtMb.setText("");
		txtSifraDelatnosti.setText("");
		txtPoreskaUprava.setText("");
		txtVrstaVlasnistva.setText("");
		txtVrstaPosla.setText("");
		txtVelicinaObveznika.setText("");
		txtVrstaIzvestaja.setText("");
		txtRacunovodstvenaRegulativa.setText("");
		cbAktivan.setSelected(false);
		}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals("")) {
			unos = false;
			}
		if(txtPunNaziv.getText() == null || txtPunNaziv.getText().equals("")) {
			unos = false;
			}
		if(cbMesto.getValue() == null) {
			unos = false;
			}
		if(txtAdresa.getText() == null || txtAdresa.getText().equals("")) {
			unos = false;
			}
		if(txtMb.getText() == null || txtMb.getText().equals("")) {
			unos = false;
			}
		if(txtPib.getText() == null || txtPib.getText().equals("")) {
			unos = false;
			}
		if(txtSifraDelatnosti.getText() == null || txtSifraDelatnosti.getText().equals("")) {
			unos = false;
			}
		if(txtEmail.getText() == null || txtEmail.getText().equals("")) {
			unos = false;
			}
		if(txtOdgovornoLice.getText() == null || txtOdgovornoLice.getText().equals("")) {
			unos = false;
			}
		if(txtPoreskiPeriod.getText() == null || txtPoreskiPeriod.getText().equals("")) {
			unos = false;
			}
		return unos;
		}
	
	public void osveziMesta(List<CMesto> lista) {
		cbMesto.getItems().clear();
		cbMesto.getItems().add(null);
		cbMesto.getItems().addAll(FXCollections.observableArrayList(lista));
		}
	
	public void osveziAgencije(List<AAgencija> lista) {
		cbAgencija.getItems().clear();
		cbAgencija.getItems().add(null);
		cbAgencija.getItems().addAll(FXCollections.observableArrayList(lista));
		}
	
	}
