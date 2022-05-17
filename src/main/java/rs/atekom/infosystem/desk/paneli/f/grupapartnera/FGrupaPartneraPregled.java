package rs.atekom.infosystem.desk.paneli.f.grupapartnera;

import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.f.grupapartnera.FGrupaPartnera;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;

public class FGrupaPartneraPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblNaziv;
	private LabelaBold lblOpis;
	private TextField txtNaziv, txtOpis;
	private FGrupaPartnera grupa;
	private FGrupaPartneraPanel panel;
	
	public FGrupaPartneraPregled(FGrupaPartneraPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblOpis = new LabelaBold(resource.getString("lbl.opis"));
		
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
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4);
		
		grid.addColumn(0, lblNaziv); grid.addColumn(1, txtNaziv); grid.addColumn(2, lblOpis); grid.addColumn(3, txtOpis);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
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
