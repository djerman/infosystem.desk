package rs.atekom.infosystem.desk.paneli.a.drzava;

import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;

public class ADrzavePregled extends GridPane{

	private LabelaObaveznaBold lblNaziv;
	private LabelaBold lblSr, lblEn, lblDe, lblPozivniBroj, lblOznaka/*, lblPodrazumevan*/;
	private TextField txtNaziv, txtSr, txtEn, txtDe, txtPozivniBroj, txtOznaka;
	//private CheckBox cbPodrazumevan;
	private ADrzava drzava;
	
	public ADrzavePregled(ADrzavePanel panel, ResourceBundle resource) {
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblSr = new LabelaBold(resource.getString("lbl.sr"));
		lblEn = new LabelaBold(resource.getString("lbl.en"));
		lblDe = new LabelaBold(resource.getString("lbl.de"));
		lblPozivniBroj = new LabelaBold(resource.getString("lbl.pozivnibroj"));
		lblOznaka = new LabelaBold(resource.getString("lbl.oznakamedjunarodna"));
		//lblPodrazumevan = new Label(resource.getString("lbl.podrazumevan"));
		
		txtNaziv = new TextField();
		txtSr = new TextField();
		txtEn = new TextField();
		txtDe = new TextField();
		txtPozivniBroj = new TextField();
		txtOznaka = new TextField();
		//cbPodrazumevan = new CheckBox();
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
		addColumn(0, lblDe); addColumn(1, txtDe); addColumn(2, lblPozivniBroj); addColumn(3, txtPozivniBroj); addColumn(4, lblOznaka); addColumn(5, txtOznaka); ;

		}
	
	public void postaviObjekat(ADrzava drzava) {
		if(drzava != null) {
			this.drzava = drzava;
			txtNaziv.setText(drzava.getNaziv());
			txtSr.setText(drzava.getSr());
			txtEn.setText(drzava.getEn());
			txtDe.setText(drzava.getDe());
			txtPozivniBroj.setText(drzava.getPozivniBroj());
			txtOznaka.setText(drzava.getOznaka());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		drzava = null;
		txtNaziv.setText("");
		txtSr.setText("");
		txtEn.setText("");
		txtDe.setText("");
		txtPozivniBroj.setText("");
		txtOznaka.setText("");
		}
	
	public ADrzava preuzmiObjekat() {
		if(this.drzava == null) {
			drzava = new ADrzava();
			//testdrzava.setId(0L);
			drzava.setPodrazumevan(false);
			}
		drzava.setNaziv(txtNaziv.getText() == null ? null : txtNaziv.getText().trim());
		drzava.setSr(txtSr.getText() == null ? null : txtSr.getText().trim());
		drzava.setEn(txtEn.getText() == null ? null : txtEn.getText().trim());
		drzava.setPozivniBroj(txtPozivniBroj.getText() == null ? null : txtPozivniBroj.getText().trim());
		drzava.setOznaka(txtOznaka.getText() == null ? null : txtOznaka.getText().trim());
		drzava.setPodrazumevan(drzava.getPodrazumevan());
		return drzava;
		}
	
	}
