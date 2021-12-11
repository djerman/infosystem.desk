package rs.atekom.infosystem.desk.paneli.a.agencija;

import java.util.ResourceBundle;

import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.agencija.AAgencija;

public class AAgencijePregled extends GridPane{

	private Label lblNaziv, lblKontaktOsoba, lblAdresa, lblTelefon, lblEmail, lblAktivan;
	private TextField txtNaziv, txtKontaktOsoba, txtAdresa, txtTelefon, txtEmail;
	private CheckBox cbAktivan;
	private AAgencija agencija;
	
	public AAgencijePregled(AAgencijePanel panel, ResourceBundle resource) {
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new Label(resource.getString("lbl.naziv"));
		lblNaziv.setStyle("-fx-text-fill: red");
		lblKontaktOsoba = new Label(resource.getString("lbl.kontaktosoba"));
		lblAdresa = new Label(resource.getString("lbl.adresa"));
		lblTelefon = new Label(resource.getString("lbl.telefon"));
		lblEmail = new Label(resource.getString("lbl.email"));
		lblAktivan = new Label(resource.getString("lbl.aktivan"));
		
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
		
		getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
		
		addColumn(0, lblNaziv); addColumn(1, txtNaziv); addColumn(2, lblKontaktOsoba); addColumn(3, txtKontaktOsoba); addColumn(4, lblAdresa); addColumn(5, txtAdresa);
		addColumn(0, lblTelefon); addColumn(1, txtTelefon); addColumn(2, lblEmail); addColumn(3, txtEmail); addColumn(4, lblAktivan); addColumn(5, cbAktivan);

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
