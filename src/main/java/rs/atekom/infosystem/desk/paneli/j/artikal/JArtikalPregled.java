package rs.atekom.infosystem.desk.paneli.j.artikal;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;
import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifa;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.baza.j.JArtikal;
import rs.atekom.infosystem.desk.a.DesniColumnConstraint;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.TekstDecimalni;
import rs.atekom.infosystem.desk.paneli.a.jedinica.AJedinicaMereComboBox;
import rs.atekom.infosystem.desk.paneli.a.poreskatarifa.APoreskaTarifaComboBox;
import rs.atekom.infosystem.desk.paneli.e.konto.EKontoComboBox;

public class JArtikalPregled extends OsnovniPregled{

	private JArtikalPanel panel;
	private JArtikal artikal;
	private LabelaObaveznaBold lblNaziv, lblJm, lblPoreskaTarifa;
	private LabelaBold lblSifra, lblGrupa, lblBarcode, lblEn, lblDe, lblProizvodnja, lblRokTrajanja, lblInfCena, lblRastur,
	lblOpis, lblOpisEn, lblOpisDe, lblKontoPrihoda, lblKontoRashoda;
	private TextField txtSifra, txtNaziv, txtBarcode, txtEn, txtDe, txtProizvodnja;
	private TekstDecimalni txtInfCena, txtRastur;
	private SearchableComboBox<IGrupaArtikala> cmbGrupa;
	private AJedinicaMereComboBox cmbJm;
	private APoreskaTarifaComboBox cmbPoreskaTarifa;
	private DatePicker dpRokTrajanja;
	private TextField txtOpis, txtOpisEn, txtOpisDe;
	private EKontoComboBox cmbKontoPrihoda, cmbKontoRashoda;
	
	public JArtikalPregled(JArtikalPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviTabove(resource);
		napraviElemente(resource);
		popakujElemente();
	}
	
	private void napraviElemente(ResourceBundle resource) {
		lblSifra = new LabelaBold(resource.getString("lbl.sifra"));
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblJm = new LabelaObaveznaBold(resource.getString("lbl.jm"));
		lblPoreskaTarifa = new LabelaObaveznaBold(resource.getString("lbl.poreskatarifa"));
		lblGrupa = new LabelaBold(resource.getString("lbl.grupa"));
		lblBarcode = new LabelaBold(resource.getString("lbl.barcode"));
		lblEn = new LabelaBold(resource.getString("lbl.en"));
		lblDe = new LabelaBold(resource.getString("lbl.de"));
		lblProizvodnja = new LabelaBold(resource.getString("lbl.proizvodnja"));
		lblRokTrajanja = new LabelaBold(resource.getString("lbl.roktrajanja"));
		lblInfCena = new LabelaBold(resource.getString("lbl.infcena"));
		lblRastur = new LabelaBold(resource.getString("lbl.rastur"));
		lblOpis = new LabelaBold(resource.getString("lbl.opis"));
		lblOpisEn = new LabelaBold(resource.getString("lbl.opisen"));
		lblOpisDe = new LabelaBold(resource.getString("lbl.opisde"));
		lblKontoPrihoda = new LabelaBold(resource.getString("lbl.kontoprihoda"));
		lblKontoRashoda = new LabelaBold(resource.getString("lbl.kontorashoda"));
		
		txtSifra = new TextField();
		txtNaziv = new TextField();
		txtNaziv.setTooltip(new Tooltip(resource.getString("obavestenja.duzinanaziva")));
		cmbJm = new AJedinicaMereComboBox(resource);
		cmbPoreskaTarifa = new APoreskaTarifaComboBox(resource);
		cmbGrupa = new SearchableComboBox<IGrupaArtikala>();
		cmbGrupa.valueProperty().addListener(new ChangeListener<IGrupaArtikala>() {
			@Override
			public void changed(ObservableValue<? extends IGrupaArtikala> observable, IGrupaArtikala oldValue, IGrupaArtikala newValue) {
				if(newValue != null) {
					cmbKontoPrihoda.setValue(newValue.getPrihod());
					cmbKontoRashoda.setValue(newValue.getRashod());
				}else {
					cmbKontoPrihoda.setValue(null);
					cmbKontoRashoda.setValue(null);
				}
			}
		});
		txtBarcode = new TextField();
		txtEn = new TextField();
		txtDe = new TextField();
		txtProizvodnja = new TextField();
		dpRokTrajanja = new DatePicker((new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
		dpRokTrajanja.setMaxWidth(Double.MAX_VALUE);
		txtInfCena = new TekstDecimalni();
		txtRastur = new TekstDecimalni();
		txtOpis = new TextField();
		txtOpisEn = new TextField();
		txtOpisDe = new TextField();
		cmbKontoPrihoda = new EKontoComboBox(resource);
		cmbKontoRashoda = new EKontoComboBox(resource);
	}
	//problem sa comboboxovima i njihovom širinom
	private void popakujElemente() {
		
		DesniColumnConstraint col1 = new  DesniColumnConstraint();
		ColumnConstraints col2 = new ColumnConstraints();
		
		DesniColumnConstraint col3 = new  DesniColumnConstraint();;
		ColumnConstraints col4 = new ColumnConstraints();
		
		DesniColumnConstraint col5 = new  DesniColumnConstraint();
		ColumnConstraints col6 = new ColumnConstraints();

		DesniColumnConstraint col7 = new  DesniColumnConstraint();
		ColumnConstraints col8 = new ColumnConstraints();
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
		for(int i = 0; i < 8; i++) {
			if((i % 2) == 0) {
				grid.getColumnConstraints().get(i).setPercentWidth(10);
			}else {
				grid.getColumnConstraints().get(i).setPercentWidth(15);
			}
		}
		
		//podaci
		grid.addColumn(0, lblSifra); grid.addColumn(1, txtSifra); grid.addColumn(2, lblNaziv); grid.addColumn(3, txtNaziv); 
		grid.addColumn(4, lblJm); grid.addColumn(5, cmbJm); grid.addColumn(6, lblPoreskaTarifa); grid.addColumn(7, cmbPoreskaTarifa); 
		
		grid.addColumn(0, lblGrupa); grid.addColumn(1, cmbGrupa); grid.addColumn(2, lblBarcode); grid.addColumn(3, txtBarcode); 
		grid.addColumn(4, lblInfCena); grid.addColumn(5, txtInfCena); grid.addColumn(6, lblRastur); grid.addColumn(7, txtRastur);
		
		grid.addColumn(0, lblProizvodnja); grid.addColumn(1, txtProizvodnja); grid.addColumn(2, lblRokTrajanja); grid.addColumn(3, dpRokTrajanja);
		grid.addColumn(4, lblOpis); grid.addColumn(5, txtOpis); 
		
		//prevod
		DesniColumnConstraint col21 = new  DesniColumnConstraint();
		ColumnConstraints col22 = new ColumnConstraints();
		DesniColumnConstraint col23 = new  DesniColumnConstraint();
		ColumnConstraints col24 = new ColumnConstraints();
		DesniColumnConstraint col25 = new  DesniColumnConstraint();
		ColumnConstraints col26 = new ColumnConstraints();
		DesniColumnConstraint col27 = new  DesniColumnConstraint();
		ColumnConstraints col28 = new ColumnConstraints();
		
		gridPrevod.getColumnConstraints().addAll(col21, col22, col23, col24, col25, col26, col27, col28);
		for(int i = 0; i < 8; i++) {
			if((i % 2) == 0) {
				gridPrevod.getColumnConstraints().get(i).setPercentWidth(10);
			}else {
				gridPrevod.getColumnConstraints().get(i).setPercentWidth(15);
			}
		}
		gridPrevod.addColumn(0, lblEn); gridPrevod.addColumn(1, txtEn); gridPrevod.addColumn(2, lblDe); gridPrevod.addColumn(3, txtDe); 
		gridPrevod.addColumn(4, lblOpisEn); gridPrevod.addColumn(5, txtOpisEn); gridPrevod.addColumn(6, lblOpisDe); gridPrevod.addColumn(7, txtOpisDe);

		//knjigovodstvo
		DesniColumnConstraint col31 = new  DesniColumnConstraint();
		ColumnConstraints col32 = new ColumnConstraints();
		DesniColumnConstraint col33 = new  DesniColumnConstraint();
		ColumnConstraints col34 = new ColumnConstraints();
		DesniColumnConstraint col35 = new  DesniColumnConstraint();
		ColumnConstraints col36 = new ColumnConstraints();
		DesniColumnConstraint col37 = new  DesniColumnConstraint();
		ColumnConstraints col38 = new ColumnConstraints();
		gridKnjigovodstvo.getColumnConstraints().addAll(col31, col32, col33, col34, col35, col36, col37, col38);
		for(int i = 0; i < 8; i++) {
			if((i % 2) == 0) {
				gridKnjigovodstvo.getColumnConstraints().get(i).setPercentWidth(10);
			}else {
				gridKnjigovodstvo.getColumnConstraints().get(i).setPercentWidth(15);
			}
		}
		gridKnjigovodstvo.addColumn(0, lblKontoPrihoda); gridKnjigovodstvo.addColumn(1, cmbKontoPrihoda); gridKnjigovodstvo.addColumn(2, lblKontoRashoda); gridKnjigovodstvo.addColumn(3, cmbKontoRashoda);

		getChildren().add(tabPane);
	}
	
	public void postaviObjekat(JArtikal artikal) {
		if(artikal != null) {
			this.artikal = artikal;
			txtSifra.setText(artikal.getSifra());
			txtNaziv.setText(artikal.getNaziv());
			cmbJm.setValue(artikal.getJm());
			cmbPoreskaTarifa.setValue(artikal.getPoreskaTarifa());
			cmbGrupa.setValue(artikal.getGrupa());
			txtBarcode.setText(artikal.getBarcode());
			txtEn.setText(artikal.getEn());
			txtDe.setText(artikal.getDe());
			txtProizvodnja.setText(artikal == null || artikal.getProizvodnja() == null ? "" : artikal.getProizvodnja().toString());
			dpRokTrajanja.setValue(artikal == null || artikal.getRokTrajanja() == null ? null : artikal.getRokTrajanja().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			txtInfCena.setText(artikal == null || artikal.getInfCena() == null ? "0,00" : txtInfCena.vratiFormatiranBroj(artikal.getInfCena()));
			txtRastur.setText(artikal == null || artikal.getRastur() == null ? "0,00" : txtRastur.vratiFormatiranBroj(artikal.getRastur()));
			txtOpis.setText(artikal.getOpis());
			txtOpisEn.setText(artikal.getOpis_en());
			txtOpisDe.setText(artikal.getOpis_de());
			cmbKontoPrihoda.setValue(artikal.getPrihod());
			cmbKontoRashoda.setValue(artikal.getRashod());
		}else {
			postaviNovo();
		}
	}
	
	public void postaviNovo() {
		this.artikal = null;
		txtSifra.setText("");
		txtNaziv.setText("");
		cmbJm.setValue(null);
		cmbPoreskaTarifa.setValue(null);
		cmbGrupa.setValue(null);
		cmbGrupa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IGrupaArtikala>() {
			@Override
			public void changed(ObservableValue<? extends IGrupaArtikala> observable, IGrupaArtikala oldValue,
					IGrupaArtikala newValue) {
				if(newValue != null) {
					cmbKontoPrihoda.setValue(newValue.getPrihod());
					cmbKontoRashoda.setValue(newValue.getRashod());
				}else {
					cmbKontoPrihoda.setValue(null);
					cmbKontoRashoda.setValue(null);
				}
			}
		});
		txtBarcode.setText("");
		txtEn.setText("");
		txtDe.setText("");
		txtProizvodnja.setText("");
		dpRokTrajanja.setValue(null);
		txtInfCena.setText("0,00");
		txtRastur.setText("0,0");
		txtOpis.setText("");
		txtOpisEn.setText("");
		txtOpisDe.setText("");
		cmbKontoPrihoda.setValue(null);
		cmbKontoRashoda.setValue(null);
	}
	
	public JArtikal preuzmiObjekat() {
		if(this.artikal == null) {
			artikal = new JArtikal();
			artikal.setPretplatnik(panel.vratiPretplatnika());
		}
		artikal.setSifra(txtSifra.getText() == null ? "" : txtSifra.getText().trim());
		artikal.setNaziv(txtNaziv.getText() == null ? "" : txtNaziv.getText().trim());
		artikal.setJm(cmbJm.getValue());
		artikal.setPoreskaTarifa(cmbPoreskaTarifa.getValue());
		artikal.setGrupa(cmbGrupa.getValue());
		artikal.setBarcode(txtBarcode.getText() == null ? "" : txtBarcode.getText().trim());
		artikal.setEn(txtEn.getText() == null ? "" : txtEn.getText().trim());
		artikal.setDe(txtDe.getText() == null ? "" : txtDe.getText().trim());
		try {
			artikal.setProizvodnja(Long.valueOf(txtProizvodnja.getText().trim()));
		}catch (Exception e) {
			artikal.setProizvodnja(null);
		}
		try {
			artikal.setInfCena(txtInfCena.vratiDecimalniBroj(txtInfCena.getText()));
		}catch (Exception e) {
			artikal.setInfCena(txtInfCena.vratiDecimalniBroj("0,00"));
		}
		try {
			artikal.setRastur(txtRastur.vratiDecimalniBroj(txtRastur.getText()));
		}catch (Exception e) {
			artikal.setRastur(txtRastur.vratiDecimalniBroj("0,00"));
		}
		artikal.setOpis(txtOpis.getText());
		artikal.setOpis_en(txtOpisEn.getText());
		artikal.setOpis_de(txtOpisDe.getText());
		if(artikal.getTip() == null)
			artikal.setTip(1);
		artikal.setPrihod(cmbKontoPrihoda.getValue());
		artikal.setRashod(cmbKontoRashoda.getValue());
		return artikal;
	}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals("")) {
			unos = false;
			}
		if(cmbJm.getValue() == null)
			unos = false;
		if(cmbPoreskaTarifa.getValue() == null)
			unos = false;
		return unos;
	}
	
	public void setJedinice(List<AJedinicaMere> jedinice) {
		cmbJm.getItems().clear();
		cmbJm.getItems().addAll(FXCollections.observableArrayList(jedinice));
	}
	
	public void setTarife(List<APoreskaTarifa> tarife) {
		if(cmbPoreskaTarifa.getItems() != null)
			cmbPoreskaTarifa.getItems().clear();
		cmbPoreskaTarifa.getItems().addAll(FXCollections.observableArrayList(tarife));
	}
	
	public void setGrupe(List<IGrupaArtikala> grupe) {
		if(cmbGrupa.getItems() != null)
			cmbGrupa.getItems().clear();
		cmbGrupa.getItems().add(null);
		cmbGrupa.getItems().addAll(FXCollections.observableArrayList(grupe));
	}
	
	public void setKonta(List<EKonto> konta) {
		if(cmbKontoPrihoda.getItems() != null)
			cmbKontoPrihoda.getItems().clear();
		if(cmbKontoRashoda.getItems() != null) {
			cmbKontoRashoda.getItems().clear();
		}
		if(konta != null) {
			cmbKontoPrihoda.getItems().addAll(konta == null ? null : FXCollections.observableArrayList(konta));
			cmbKontoRashoda.getItems().addAll(konta == null ? null : FXCollections.observableArrayList(konta));
		}

	}
}
