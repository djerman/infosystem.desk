package rs.atekom.infosystem.desk.paneli.j.artikal;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;
import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifa;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.baza.j.JArtikal;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.TekstDecimalni;
import rs.atekom.infosystem.desk.paneli.a.jedinica.AJedinicaMereComboBox;
import rs.atekom.infosystem.desk.paneli.a.poreskatarifa.APoreskaTarifaComboBox;

public class JArtikalPregled extends OsnovniPregled{

	private JArtikalPanel panel;
	private JArtikal artikal;
	private LabelaObaveznaBold lblNaziv, lblJm, lblPoreskaTarifa;
	private LabelaBold lblSifra, lblGrupa, lblBarcode, lblEn, lblDe, lblProizvodnja, lblRokTrajanja, lblInfCena, lblRastur,
	lblOpis, lblOpisEn, lblOpisDe;
	private TextField txtSifra, txtNaziv, txtBarcode, txtEn, txtDe, txtProizvodnja, txtInfCena, txtRastur;
	private SearchableComboBox<IGrupaArtikala> cmbGrupa;
	private AJedinicaMereComboBox cmbJm;
	private APoreskaTarifaComboBox cmbPoreskaTarifa;
	private DatePicker dpRokTrajanja;
	private TextArea txtOpis, txtOpisEn, txtOpisDe;
	
	public JArtikalPregled(JArtikalPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviGrid();
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
		
		txtSifra = new TextField();
		txtNaziv = new TextField();
		cmbJm = new AJedinicaMereComboBox(resource);
		cmbPoreskaTarifa = new APoreskaTarifaComboBox(resource);
		cmbGrupa = new SearchableComboBox<IGrupaArtikala>();
		txtBarcode = new TextField();
		txtEn = new TextField();
		txtDe = new TextField();
		txtProizvodnja = new TextField();
		dpRokTrajanja = new DatePicker((new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
		txtInfCena = new TekstDecimalni();
		txtRastur = new TekstDecimalni();
		txtOpis = new TextArea();
		txtOpisEn = new TextArea();
		txtOpisDe = new TextArea();
	}
	//problem sa comboboxovima i njihovom širinom
	private void popakujElemente() {
		Double minSirina = 100.0;
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHalignment(HPos.RIGHT);
		col3.setMinWidth(minSirina);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setHalignment(HPos.RIGHT);
		col5.setMinWidth(minSirina);
		ColumnConstraints col6 = new ColumnConstraints();
		col6.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col7 = new ColumnConstraints();
		col7.setHalignment(HPos.RIGHT);
		col7.setMinWidth(minSirina);
		ColumnConstraints col8 = new ColumnConstraints();
		col8.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col9 = new ColumnConstraints();
		col9.setHalignment(HPos.RIGHT);
		col9.setMinWidth(minSirina);
		ColumnConstraints col10 = new ColumnConstraints();
		col10.setHgrow(Priority.ALWAYS);
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);
		
		grid.addColumn(0, lblSifra); grid.addColumn(1, txtSifra); grid.addColumn(2, lblNaziv); grid.addColumn(3, txtNaziv); grid.addColumn(4, lblJm); grid.addColumn(5, cmbJm);
		grid.addColumn(6, lblPoreskaTarifa); grid.addColumn(7, cmbPoreskaTarifa); grid.addColumn(8, lblGrupa); grid.addColumn(9, cmbGrupa);
		
		grid.addColumn(0, lblBarcode); grid.addColumn(1, txtBarcode); grid.addColumn(2, lblInfCena); grid.addColumn(3, txtInfCena); grid.addColumn(4, lblRastur); grid.addColumn(5, txtRastur);
		grid.addColumn(6, lblProizvodnja); grid.addColumn(7, txtProizvodnja); grid.addColumn(8, lblRokTrajanja); grid.addColumn(9, dpRokTrajanja);
		
		
		grid.addColumn(0, lblEn); grid.addColumn(1, txtEn); grid.addColumn(2, lblDe); grid.addColumn(3, txtDe);//grid.addColumn(4, lblOpis); grid.addColumn(5, txtOpis);
		//grid.addColumn(6, lblOpisEn); grid.addColumn(7, txtOpisEn); grid.addColumn(8, lblOpisDe); grid.addColumn(9, txtOpisDe);
		
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
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
			txtProizvodnja.setText(artikal.getProizvodnja() == null ? "" : artikal.getProizvodnja().toString());
			dpRokTrajanja.setValue(artikal.getRokTrajanja() == null ? null : artikal.getRokTrajanja().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			txtInfCena.setText(artikal.getInfCena() == null ? "0.00" : artikal.getInfCena().toString());
			txtRastur.setText(artikal.getRastur() == null ? "0.00" : artikal.getRastur().toString());
			txtOpis.setText(artikal.getOpis());
			txtOpisEn.setText(artikal.getOpis_en());
			txtOpisDe.setText(artikal.getOpis_de());
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
		txtBarcode.setText("");
		txtEn.setText("");
		txtDe.setText("");
		txtProizvodnja.setText("");
		dpRokTrajanja.setValue(null);
		txtInfCena.setText("0.00");
		txtRastur.setText("0.00");
		txtOpis.setText("");
		txtOpisEn.setText("");
		txtOpisDe.setText("");
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
			artikal.setInfCena(new BigDecimal(txtInfCena.getText()));
		}catch (Exception e) {
			artikal.setInfCena(new BigDecimal(0));
		}
		try {
			artikal.setRastur(new BigDecimal(txtRastur.getText()));
		}catch (Exception e) {
			artikal.setRastur(new BigDecimal(0));
		}
		artikal.setOpis(txtOpis.getText());
		artikal.setOpis_en(txtOpisEn.getText());
		artikal.setOpis_de(txtOpisDe.getText());
		
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
		cmbPoreskaTarifa.getItems().clear();
		cmbPoreskaTarifa.getItems().addAll(FXCollections.observableArrayList(tarife));
	}
	
	public void setGrupe(List<IGrupaArtikala> grupe) {
		cmbGrupa.getItems().clear();
		cmbGrupa.getItems().addAll(FXCollections.observableArrayList(grupe));
	}
}
