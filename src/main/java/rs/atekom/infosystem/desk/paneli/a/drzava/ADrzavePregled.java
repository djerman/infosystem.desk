package rs.atekom.infosystem.desk.paneli.a.drzava;

import java.util.ResourceBundle;

import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;

public class ADrzavePregled extends GridPane{

	private Label lblNaziv, lblSr, lblEn, lblPozivniBroj, lblOznaka, lblPodrazumevan;
	private TextField txtNaziv, txtSr, txtEn, txtPozivniBroj, txtOznaka;
	private CheckBox cbPodrazumevan;
	private ADrzava drzava;
	
	public ADrzavePregled(ADrzavePanel panel, ResourceBundle resource) {
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new Label(resource.getString("lbl.naziv"));
		lblNaziv.setStyle("-fx-text-fill: red");
		lblSr = new Label(resource.getString("lbl.sr"));
		lblEn = new Label(resource.getString("lbl.en"));
		lblPozivniBroj = new Label(resource.getString("lbl.pozivnibroj"));
		lblOznaka = new Label(resource.getString("lbl.oznaka"));
		lblPodrazumevan = new Label(resource.getString("lbl.podrazumevan"));
		
		txtNaziv = new TextField();
		txtSr = new TextField();
		txtEn = new TextField();
		txtPozivniBroj = new TextField();
		txtOznaka = new TextField();
		cbPodrazumevan = new CheckBox();
		}
	
	private void popakujElemente() {
		Double minSirina1 = 100.0;
		Double minSirina2 = 150.0;
		Double minSirina3 = 120.0;
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina1);
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
		
		addColumn(0, lblNaziv); addColumn(1, txtNaziv); addColumn(2, lblSr); addColumn(3, txtSr); addColumn(4, lblEn); addColumn(5, txtEn);
		addColumn(0, lblPozivniBroj); addColumn(1, txtPozivniBroj); addColumn(2, lblOznaka); addColumn(3, txtOznaka); addColumn(4, lblPodrazumevan); addColumn(5, cbPodrazumevan);

		}
	
	public void postaviObjekat(ADrzava drzava) {
		if(drzava != null) {
			this.drzava = drzava;
			txtNaziv.setText(drzava.getNaziv());
			txtSr.setText(drzava.getSr());
			txtEn.setText(drzava.getEn());
			txtPozivniBroj.setText(drzava.getPozivniBroj());
			txtOznaka.setText(drzava.getOznaka());
			cbPodrazumevan.setSelected(drzava.getPodrazumevan());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		drzava = null;
		txtNaziv.setText("");
		txtSr.setText("");
		txtEn.setText("");
		txtPozivniBroj.setText("");
		txtOznaka.setText("");
		cbPodrazumevan.setSelected(false);
		}
	
	public ADrzava preuzmiObjekat() {
		if(this.drzava == null) {
			drzava = new ADrzava();
			drzava.setId(0L);
			}
		drzava.setNaziv(txtNaziv.getText().trim());
		drzava.setSr(txtSr.getText().trim());
		drzava.setEn(txtEn.getText().trim());
		drzava.setPozivniBroj(txtPozivniBroj.getText().trim());
		drzava.setOznaka(txtOznaka.getText().trim());
		drzava.setPodrazumevan(cbPodrazumevan.isSelected());
		return drzava;
		}
	
	}
