package rs.atekom.infosystem.desk.paneli.e.grupaprava;

import java.util.ResourceBundle;

import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.e.grupaprava.EGrupaPrava;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;

public class EGrupaPravaPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblNaziv;
	private LabelaBold lblSr, lblEn, lblDe;
	private LabelaBold lblOpis, lblOpisSr, lblOpisEn, lblOpisDe;
	private TextField txtNaziv, txtSr, txtEn, txtDe, txtOpis, txtOpisSr, txtOpisEn, txtOpisDe;
	private EGrupaPrava grupaPrava;
	private EGrupaPravaPanel panel;
	
	public EGrupaPravaPregled(EGrupaPravaPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblSr = new LabelaBold(resource.getString("lbl.nazivsr"));
		lblEn = new LabelaBold(resource.getString("lbl.naziven"));
		lblDe = new LabelaBold(resource.getString("lbl.nazivde"));
		lblOpis = new LabelaBold(resource.getString("lbl.opis"));
		lblOpisSr = new LabelaBold(resource.getString("lbl.opissr"));
		lblOpisEn = new LabelaBold(resource.getString("lbl.opisen"));
		lblOpisDe = new LabelaBold(resource.getString("lbl.opisde"));
		
		txtNaziv = new TextField(); 
		txtSr = new  TextField(); 
		txtEn = new  TextField(); 
		txtDe = new TextField(); 
		txtOpis = new TextField(); 
		txtOpisSr = new TextField(); 
		txtOpisEn = new TextField(); 
		txtOpisDe = new TextField();
		}
	
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
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
		
		grid.addColumn(0, lblNaziv); grid.addColumn(1, txtNaziv); grid.addColumn(2, lblSr); grid.addColumn(3, txtSr); 
		grid.addColumn(4, lblEn); grid.addColumn(5, txtEn); grid.addColumn(6, lblDe); grid.addColumn(7, txtDe);
		grid.addColumn(0, lblOpis); grid.addColumn(1, txtOpis); grid.addColumn(2, lblOpisSr); grid.addColumn(3, txtOpisSr); 
		grid.addColumn(4, lblOpisEn); grid.addColumn(5, txtOpisEn); grid.addColumn(6, lblOpisDe); grid.addColumn(7, txtOpisDe);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
		}
	
	public void postaviObjekat(EGrupaPrava grupaPrava) {
		if(grupaPrava != null) {
			this.grupaPrava = grupaPrava;
			txtNaziv.setText(grupaPrava.getNaziv());
			txtSr.setText(grupaPrava.getSr());
			txtEn.setText(grupaPrava.getEn());
			txtDe.setText(grupaPrava.getDe());
			txtOpis.setText(grupaPrava.getOpis());
			txtOpisSr.setText(grupaPrava.getOpis_sr());
			txtOpisEn.setText(grupaPrava.getOpis_en());
			txtOpisDe.setText(grupaPrava.getOpis_de());
			}else {
				postaviNovo();
			}
		}
	
	public void postaviNovo() {
		grupaPrava = null;
		txtNaziv.setText("");
		txtSr.setText("");
		txtEn.setText("");
		txtDe.setText("");
		txtOpis.setText("");
		txtOpisSr.setText("");
		txtOpisEn.setText("");
		txtOpisDe.setText("");
		}
	
	public EGrupaPrava preuzmiObjekat() {
		if(grupaPrava == null) {
			grupaPrava = new EGrupaPrava();
			grupaPrava.setPretplatnik(panel.vratiPretplatnika());
		}
		grupaPrava.setNaziv(txtNaziv.getText() == null ? null : txtNaziv.getText().trim());
		grupaPrava.setSr(txtSr.getText() == null ? null : txtSr.getText().trim());
		grupaPrava.setEn(txtEn.getText() == null ? null : txtEn.getText().trim());
		grupaPrava.setDe(txtDe.getText() == null ? null : txtDe.getText().trim());
		grupaPrava.setOpis(txtOpis.getText() == null ? null : txtOpis.getText().trim());
		grupaPrava.setOpis_sr(txtOpisSr.getText() == null ? null : txtOpisSr.getText().trim());
		grupaPrava.setOpis_en(txtOpisEn.getText() == null ? null : txtOpisEn.getText().trim());
		grupaPrava.setOpis_de(txtOpisDe.getText() == null ? null : txtOpisDe.getText().trim());
		return grupaPrava;
		}
	
	public Boolean proveraUnosa() {
		Boolean ispravno = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals(""))
			ispravno = false;
		return ispravno;
		}
	
	}
