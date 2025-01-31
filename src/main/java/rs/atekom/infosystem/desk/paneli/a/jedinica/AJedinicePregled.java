package rs.atekom.infosystem.desk.paneli.a.jedinica;

import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;

public class AJedinicePregled extends OsnovniPregled{

	private LabelaObaveznaBold lblNaziv, lblOznaka;
	private LabelaBold lblNazivSr, lblNazivEn, lblNazivDe, lblOznakaSr, lblOznakaEn, lblOznakaDe;
	private TextField txtNaziv, txtNazivSr, txtNazivEn, txtNazivDe, txtOznaka, txtOznakaSr, txtOznakaEn, txtOznakaDe;
	private AJedinicaMere jedinica;
	
	public AJedinicePregled(AJedinicePanel panel, ResourceBundle resource) {
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblNazivSr = new LabelaBold(resource.getString("lbl.nazivsr"));
		lblNazivEn = new LabelaBold(resource.getString("lbl.naziven"));
		lblNazivDe = new LabelaBold(resource.getString("lbl.nazivde"));
		lblOznaka = new LabelaObaveznaBold(resource.getString("lbl.oznaka"));
		lblOznakaSr = new LabelaBold(resource.getString("lbl.sr"));
		lblOznakaEn = new LabelaBold(resource.getString("lbl.en"));
		lblOznakaDe = new LabelaBold(resource.getString("lbl.de"));
		
		txtNaziv = new TextField();
		txtNazivSr = new TextField();
		txtNazivEn = new TextField();
		txtNazivDe = new TextField();
		txtOznaka = new TextField();
		txtOznakaSr = new TextField();
		txtOznakaEn = new TextField();
		txtOznakaDe = new TextField();
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
		
		grid.addColumn(0, lblNaziv); grid.addColumn(1, txtNaziv); grid.addColumn(2, lblNazivSr); grid.addColumn(3, txtNazivSr); 
		grid.addColumn(4, lblNazivEn); grid.addColumn(5, txtNazivEn); grid.addColumn(6, lblNazivDe); grid.addColumn(7, txtNazivDe);
		grid.addColumn(0, lblOznaka); grid.addColumn(1, txtOznaka); grid.addColumn(2, lblOznakaSr); grid.addColumn(3, txtOznakaSr); 
		grid.addColumn(4, lblOznakaEn); grid.addColumn(5, txtOznakaEn); grid.addColumn(6, lblOznakaDe); grid.addColumn(7, txtOznakaDe);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
		}
	
	public void postaviObjekat(AJedinicaMere jedinica) {
		if(jedinica != null) {
			this.jedinica = jedinica;
			txtNaziv.setText(jedinica.getNaziv());
			txtNazivSr.setText(jedinica.getNazivSr());
			txtNazivEn.setText(jedinica.getNazivEn());
			txtNazivDe.setText(jedinica.getNazivDe());
			txtOznaka.setText(jedinica.getOznaka());
			txtOznakaSr.setText(jedinica.getSr());
			txtOznakaEn.setText(jedinica.getEn());
			txtOznakaDe.setText(jedinica.getDe());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		jedinica = null;
		txtNaziv.setText("");
		txtNazivSr.setText("");
		txtNazivEn.setText("");
		txtNazivDe.setText("");
		txtOznaka.setText("");
		txtOznakaSr.setText("");
		txtOznakaEn.setText("");
		txtOznakaDe.setText("");
		}
	
	public AJedinicaMere preuzmiObjekat() {
		if(jedinica == null) {
			jedinica = new AJedinicaMere();
			jedinica.setId(0L);
			}
		jedinica.setNaziv(txtNaziv.getText() == null ? null : txtNaziv.getText().trim());
		jedinica.setNazivSr(txtNazivSr.getText() == null ? null : txtNazivSr.getText().trim());
		jedinica.setNazivEn(txtNazivEn.getText() == null ? null : txtNazivEn.getText().trim());
		jedinica.setNazivDe(txtNazivDe.getText() == null ? null : txtNazivDe.getText().trim());
		jedinica.setOznaka(txtOznaka.getText() == null ? null : txtOznaka.getText().trim());
		jedinica.setSr(txtOznakaSr.getText() == null ? null : txtOznakaSr.getText().trim());
		jedinica.setEn(txtOznakaEn.getText() == null ? null : txtOznakaEn.getText().trim());
		jedinica.setDe(txtOznakaDe.getText() == null ? null : txtOznakaDe.getText().trim());
		return jedinica;
		}
	
	public Boolean provera(AJedinicaMere jedinica) {
		Boolean ispravno = true;
		if(jedinica.getNaziv() == null || jedinica.getNaziv().equals("")) {
			ispravno = false;
			}
		if(jedinica.getOznaka() == null || jedinica.getOznaka().equals("")) {
			ispravno = false;
			}
		return ispravno;
		}
	
	}
