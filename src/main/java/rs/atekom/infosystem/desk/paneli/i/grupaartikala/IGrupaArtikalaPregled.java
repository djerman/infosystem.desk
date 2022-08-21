package rs.atekom.infosystem.desk.paneli.i.grupaartikala;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
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
		napraviGrid();
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
		Double minSirina = 80.0;
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
		for(int i = 0; i < 10; i++) {
			grid.getColumnConstraints().get(i).setPercentWidth(10);
		}
		
		grid.addColumn(0, lblSifra); grid.addColumn(1, txtSifra); grid.addColumn(2, lblNaziv); grid.addColumn(3, txtNaziv); grid.addColumn(4, lblOpis); grid.addColumn(5, txtOpis);
		grid.addColumn(6, lblKontoPrihoda); grid.addColumn(7, cmbKontoPrihoda); grid.addColumn(8, lblKontoRashoda); grid.addColumn(9, cmbKontoRashoda);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
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
		cmbKontoPrihoda.getItems().addAll(FXCollections.observableArrayList(konta));
		cmbKontoRashoda.getItems().addAll(FXCollections.observableArrayList(konta));
	}
	
}
