package rs.atekom.infosystem.desk.paneli.f.objekat;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.e.organizacija.EOrganizacija;
import rs.atekom.infosystem.baza.f.objekat.FObjekat;
import rs.atekom.infosystem.desk.a.ComboSaBrisanjem;
import rs.atekom.infosystem.desk.a.DesniColumnConstraint;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.TekstDecimalni;
import rs.atekom.infosystem.desk.paneli.c.mesto.CMestoComboBox;
import rs.atekom.infosystem.desk.paneli.e.konto.EKontoComboBox;
import rs.atekom.infosystem.desk.paneli.e.organizacija.EOrganizacijaComboBox;

public class FObjekatPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblOrganizacija, lblNaziv, lblKonto;
	private LabelaBold lblAdresa, lblDokument, lblMesto, lblSaldo;
	private FObjekatPanel panel;
	private TextField txtNaziv, txtAdresa, txtDokument;
	private EOrganizacijaComboBox cmbOrganizacija;
	private EKontoComboBox cmbKonto;
	private CMestoComboBox cmbMesto;
	private TekstDecimalni txtSaldo;
	private FObjekat objekat;
	private ComboSaBrisanjem mesta, organizacije, konta;
	
	public FObjekatPregled(FObjekatPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
	}
	
	private void napraviElemente(ResourceBundle resource) {
		lblOrganizacija = new LabelaObaveznaBold(resource.getString("lbl.organizacija"));
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblKonto = new LabelaObaveznaBold(resource.getString("lbl.konto"));
		lblMesto = new LabelaBold(resource.getString("lbl.mesto"));
		lblAdresa = new LabelaBold(resource.getString("lbl.adresa"));
		lblDokument = new LabelaBold(resource.getString("lbl.dokument"));
		lblSaldo = new LabelaBold(resource.getString("lbl.saldo"));
		
		cmbOrganizacija = new EOrganizacijaComboBox();
		organizacije = new ComboSaBrisanjem(cmbOrganizacija);
		txtNaziv = new TextField();
		cmbKonto = new EKontoComboBox(resource);
		konta = new ComboSaBrisanjem(cmbKonto);
		cmbMesto = new CMestoComboBox(resource);
		mesta = new ComboSaBrisanjem(cmbMesto);
		txtAdresa = new TextField();
		txtDokument = new TextField();
		txtDokument.setDisable(true);
		txtSaldo = new TekstDecimalni();
		txtSaldo.setDisable(true);
	}
	
	private void popakujElemente() {
		DesniColumnConstraint col1 = new  DesniColumnConstraint();
		ColumnConstraints col2 = new ColumnConstraints();
		
		DesniColumnConstraint col3 = new  DesniColumnConstraint();;
		ColumnConstraints col4 = new ColumnConstraints();
		
		DesniColumnConstraint col5 = new  DesniColumnConstraint();
		ColumnConstraints col6 = new ColumnConstraints();
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
		
		for(int i = 0; i < 6; i++) {
			if((i % 2) == 0) {
				grid.getColumnConstraints().get(i).setPercentWidth(10);
			}else {
				grid.getColumnConstraints().get(i).setPercentWidth(23);
			}
		}
		
		grid.addColumn(0, lblOrganizacija); grid.addColumn(1, organizacije);  grid.addColumn(2, lblNaziv); grid.addColumn(3, txtNaziv); grid.addColumn(4, lblKonto); grid.addColumn(5, konta); 
		
		grid.addColumn(0, lblMesto); grid.addColumn(1, mesta); grid.addColumn(2, lblAdresa); grid.addColumn(3, txtAdresa); grid.addColumn(4, lblDokument); grid.addColumn(5, txtDokument); 
		
		grid.addColumn(4, lblSaldo); grid.addColumn(5, txtSaldo);
		
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
	}
	
	public void postaviObjekat(FObjekat objekat) {
		if(objekat != null) {
			this.objekat = objekat;
			cmbOrganizacija.setValue(objekat.getOrganizacija());
			txtNaziv.setText(objekat.getNaziv());
			cmbKonto.setValue(objekat.getKonto());
			cmbMesto.setValue(objekat.getMesto());
			txtAdresa.setText(objekat.getAdresa());
			txtDokument.setText(objekat.getDokument() == null ? "" : objekat.getDokument().getSifra());
			txtSaldo.setText(objekat.getSaldo() == null ? "0,00" : txtSaldo.vratiFormatiranBroj(objekat.getSaldo()));
		}else {
			postaviNovo();
		}
	}
	
	public void postaviNovo() {
		this.objekat = null;
		cmbOrganizacija.setValue(null);
		txtNaziv.setText("");
		cmbKonto.setValue(null);
		cmbKonto.getSelectionModel().clearSelection();
		cmbMesto.setValue(null);
		txtAdresa.setText("");
		txtDokument.setText("");
		txtSaldo.setText("0,00");
	}
	
	public FObjekat preuzmiObjekat() {
		if(objekat == null) {
			objekat = new FObjekat();
			objekat.setPretplatnik(panel.vratiPretplatnika());
			objekat.setSaldo(txtSaldo.vratiDecimalniBroj("0,00"));
		}
		objekat.setOrganizacija(cmbOrganizacija.getValue());
		objekat.setNaziv(txtNaziv.getText() == null ? "" : txtNaziv.getText().trim());
		objekat.setKonto(cmbKonto.getValue());
		objekat.setMesto(cmbMesto.getValue());
		objekat.setAdresa(txtAdresa.getText() == null ? "" : txtAdresa.getText().trim());
		//dokument
		//saldo
		return objekat;
	}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals(""))
			unos = false;
		if(cmbOrganizacija.getValue() == null)
			unos = false;
		return unos;
	}
	
	public void setKonta(List<EKonto> konta) {
		if(cmbKonto.getItems() != null)
			cmbKonto.getItems().clear();
		
		if(konta != null) 
			cmbKonto.getItems().addAll(konta == null ? null : FXCollections.observableArrayList(konta));
	}
	
	public void setMesta(List<CMesto> lista) {
		if(cmbMesto.getItems() != null)
			cmbMesto.getItems().clear();
		if(lista != null) {
			cmbMesto.getItems().addAll(lista);
		}
	}
	
	public void setOrganizacije(List<EOrganizacija> lista) {
		if(cmbOrganizacija.getItems() != null)
			cmbOrganizacija.getItems().clear();
		if(lista != null)
			cmbOrganizacija.getItems().addAll(lista);
	}
	
}
