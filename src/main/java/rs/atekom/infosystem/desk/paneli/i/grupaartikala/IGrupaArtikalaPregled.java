package rs.atekom.infosystem.desk.paneli.i.grupaartikala;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.desk.a.DesniColumnConstraint;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.paneli.e.konto.EKontoComboBox;

public class IGrupaArtikalaPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblNaziv;
	private LabelaBold lblSifra, lblOpis, lblKontoPrihoda, lblKontoRashoda;
	private TextField txtSifra, txtNaziv, txtOpis;
	private IGrupaArtikalaPanel panel;
	private IGrupaArtikala grupa;
	private EKontoComboBox cmbKontoPrihoda, cmbKontoRashoda;
	
	
	public IGrupaArtikalaPregled(IGrupaArtikalaPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviTabove(resource);
		napraviElemente(resource);
		popakujElemente();
	}
	
	private void napraviElemente(ResourceBundle resource) {
		lblSifra = new LabelaBold(resource.getString("lbl.sifra"));
		
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblOpis = new LabelaBold(resource.getString("lbl.opis"));
		lblKontoPrihoda = new LabelaBold(resource.getString("lbl.kontoprihoda"));
		lblKontoRashoda = new LabelaBold(resource.getString("lbl.kontorashoda"));
		
		txtSifra = new TextField();
		txtNaziv = new TextField();
		txtOpis = new TextField();
		cmbKontoPrihoda = new EKontoComboBox(resource);
		cmbKontoRashoda = new EKontoComboBox(resource);
	}
	
	private void popakujElemente() {
		DesniColumnConstraint col1 = new DesniColumnConstraint();
		ColumnConstraints col2 = new ColumnConstraints();
		
		DesniColumnConstraint col3 = new DesniColumnConstraint();
		ColumnConstraints col4 = new ColumnConstraints();
		
		DesniColumnConstraint col5 = new DesniColumnConstraint();
		ColumnConstraints col6 = new ColumnConstraints();
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
		grid.addColumn(0, lblSifra); grid.addColumn(1, txtSifra); grid.addColumn(2, lblNaziv); grid.addColumn(3, txtNaziv); grid.addColumn(4, lblOpis); grid.addColumn(5, txtOpis);
		
		DesniColumnConstraint col21 = new DesniColumnConstraint();
		ColumnConstraints col22 = new ColumnConstraints();
		
		DesniColumnConstraint col23 = new DesniColumnConstraint();
		ColumnConstraints col24 = new ColumnConstraints();
		
		gridKnjigovodstvo.getColumnConstraints().addAll(col21, col22, col23, col24);
		gridKnjigovodstvo.addColumn(0, lblKontoPrihoda); gridKnjigovodstvo.addColumn(1, cmbKontoPrihoda); gridKnjigovodstvo.addColumn(2, lblKontoRashoda); gridKnjigovodstvo.addColumn(3, cmbKontoRashoda);
		
		for(int i = 0; i < 6; i++) {
			if((i % 2) == 0) {
				grid.getColumnConstraints().get(i).setPercentWidth(10);
			}else {
				grid.getColumnConstraints().get(i).setPercentWidth(15);
			}
		}
		
		for(int i = 0; i < 4; i++) {
			if((i % 2) == 0) {
				gridKnjigovodstvo.getColumnConstraints().get(i).setPercentWidth(10);
			}else {
				gridKnjigovodstvo.getColumnConstraints().get(i).setPercentWidth(15);
			}
		}
		
		getChildren().add(tabPane);
	}
	
	public void postaviObjekat(IGrupaArtikala grupa) {
		if(grupa != null) {
			this.grupa = grupa;
			txtSifra.setText(grupa.getSifra());
			txtNaziv.setText(grupa.getNaziv());
			txtOpis.setText(grupa.getOpis());
			cmbKontoPrihoda.setValue(grupa.getPrihod());
			cmbKontoRashoda.setValue(grupa.getRashod());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		this.grupa = null;
		txtSifra.setText("");
		txtNaziv.setText("");
		txtOpis.setText("");
		cmbKontoPrihoda.setValue(null);
		cmbKontoRashoda.setValue(null);
		}
	
	public IGrupaArtikala preuzmiObjekat() {
		if(grupa == null) {
			grupa = new IGrupaArtikala();
			grupa.setPretplatnik(panel.vratiPretplatnika());
		}
		grupa.setSifra(txtSifra.getText());
		grupa.setNaziv(txtNaziv.getText() == null ? null : txtNaziv.getText().trim());
		grupa.setOpis(txtOpis.getText() == null ? "" : txtOpis.getText().trim());
		grupa.setPrihod(cmbKontoPrihoda.getValue());
		grupa.setRashod(cmbKontoRashoda.getValue());
		return grupa;
	}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals("")) {
			unos = false;
			}
		return unos;
	}
	
	public void setKonta(List<EKonto> konta) {
		if(cmbKontoPrihoda.getItems() != null)
			cmbKontoPrihoda.getItems().clear();
		if(cmbKontoRashoda.getItems() != null) {
			cmbKontoRashoda.getItems().clear();
		}
		if(konta != null) {
			cmbKontoPrihoda.getItems().addAll(FXCollections.observableArrayList(konta));
			cmbKontoRashoda.getItems().addAll(FXCollections.observableArrayList(konta));
		}

	}
	
}
