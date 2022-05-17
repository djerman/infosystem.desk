package rs.atekom.infosystem.desk.paneli.a.agencija;

import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.agencija.AAgencija;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;

public class AAgencijePregled extends OsnovniPregled{

	private LabelaObaveznaBold lblNaziv;
	private LabelaBold lblKontaktOsoba, lblAdresa, lblTelefon, lblEmail, lblAktivan;
	private TextField txtNaziv, txtKontaktOsoba, txtAdresa, txtTelefon, txtEmail;
	private CheckBox cbAktivan;
	private AAgencija agencija;
	
	public AAgencijePregled(AAgencijePanel panel, ResourceBundle resource) {
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblKontaktOsoba = new LabelaBold(resource.getString("lbl.kontaktosoba"));
		lblAdresa = new LabelaBold(resource.getString("lbl.adresa"));
		lblTelefon = new LabelaBold(resource.getString("lbl.telefon"));
		lblEmail = new LabelaBold(resource.getString("lbl.email"));
		lblAktivan = new LabelaBold(resource.getString("lbl.aktivan"));
		
		txtNaziv = new TextField();
		txtKontaktOsoba = new TextField();
		txtAdresa = new TextField();
		txtTelefon = new TextField();
		txtEmail = new TextField();
		cbAktivan = new CheckBox();
		}
	
	private void popakujElemente() {
		Double minSirina = 80.0;
		Double minSirina2 = 120.0;
		Double minSirina3 = 80.0;
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHalignment(HPos.RIGHT);
		col3.setMinWidth(minSirina2);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setHalignment(HPos.RIGHT);
		col5.setMinWidth(minSirina3);
		ColumnConstraints col6 = new ColumnConstraints();
		col6.setHgrow(Priority.ALWAYS);
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
		
		grid.addColumn(0, lblNaziv); grid.addColumn(1, txtNaziv); grid.addColumn(2, lblKontaktOsoba); grid.addColumn(3, txtKontaktOsoba); grid.addColumn(4, lblAdresa); grid.addColumn(5, txtAdresa);
		grid.addColumn(0, lblTelefon); grid.addColumn(1, txtTelefon); grid.addColumn(2, lblEmail); grid.addColumn(3, txtEmail); grid.addColumn(4, lblAktivan); grid.addColumn(5, cbAktivan);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
		}
	
	public void postaviObjekat(AAgencija agencija) {
		if(agencija != null) {
			this.agencija = agencija;
			txtNaziv.setText(agencija.getNaziv());
			txtKontaktOsoba.setText(agencija.getKontaktOsoba());
			txtAdresa.setText(agencija.getAdresa());
			txtTelefon.setText(agencija.getTelefon());
			txtEmail.setText(agencija.getEmail());
			cbAktivan.setSelected(agencija.getAktivan());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		agencija = null;
		txtNaziv.setText("");
		txtKontaktOsoba.setText("");
		txtAdresa.setText("");
		txtTelefon.setText("");
		txtEmail.setText("");
		cbAktivan.setSelected(true);
		}
	
	public AAgencija preuzmiObjekat() {
		if(this.agencija == null) {
			agencija = new AAgencija();
			}
		agencija.setNaziv(txtNaziv.getText().trim());
		agencija.setKontaktOsoba(txtKontaktOsoba.getText().trim());
		agencija.setAdresa(txtAdresa.getText().trim());
		agencija.setTelefon(txtTelefon.getText().trim());
		agencija.setEmail(txtEmail.getText().trim());
		agencija.setAktivan(cbAktivan.isSelected());
		return agencija;
		}
	
	}
