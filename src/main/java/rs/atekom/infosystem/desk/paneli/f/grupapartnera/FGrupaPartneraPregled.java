package rs.atekom.infosystem.desk.paneli.f.grupapartnera;

import java.util.ResourceBundle;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.f.FGrupaPartnera;

public class FGrupaPartneraPregled extends GridPane{

	private Label lblNaziv, lblOpis;
	private TextField txtNaziv, txtOpis;
	private FGrupaPartnera grupa;
	private FGrupaPartneraPanel panel;
	
	public FGrupaPartneraPregled(FGrupaPartneraPanel panel, ResourceBundle resource) {
		this.panel = panel;
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new Label(resource.getString("lbl.naziv"));
		lblNaziv.setStyle("-fx-text-fill: red");
		lblOpis = new Label(resource.getString("lbl.opis"));
		
		txtNaziv = new TextField();
		txtOpis = new TextField();
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
		
		getColumnConstraints().addAll(col1, col2, col3, col4);
		
		addColumn(0, lblNaziv); addColumn(1, txtNaziv); addColumn(2, lblOpis); addColumn(3, txtOpis);
		}
	
	public void postaviObjekat(FGrupaPartnera grupa) {
		if(grupa != null) {
			this.grupa = grupa;
			txtNaziv.setText(grupa.getNaziv());
			txtOpis.setText(grupa.getOpis());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		this.grupa = null;
		txtNaziv.setText("");
		txtOpis.setText("");
		}
	
	public FGrupaPartnera preuzmiObjekat() {
		if(this.grupa == null) {
			grupa = new FGrupaPartnera();
			grupa.setId(0L);
			}
		grupa.setPretplatnik(panel.vratiPretplatnika());
		grupa.setNaziv(txtNaziv.getText().trim());
		grupa.setOpis(txtOpis.getText().trim());
		return grupa;
		}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals("")) {
			unos = false;
			}
		return unos;
		}
	
	}
