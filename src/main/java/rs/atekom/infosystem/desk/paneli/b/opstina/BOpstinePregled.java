package rs.atekom.infosystem.desk.paneli.b.opstina;

import java.util.ResourceBundle;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;
import rs.atekom.infosystem.desk.paneli.a.drzava.ADrzavaComboBox;

public class BOpstinePregled extends OsnovniPregled{

	private LabelaObaveznaBold lblDrzava, lblNaziv;
	private LabelaBold lblSr, lblEn, lblDe, lblPozivniBroj, lblPostanskiBroj, lblPozivNaBroj;
	private TextField txtNaziv, txtSr, txtEn, txtDe, txtPozivniBroj, txtPostanskiBroj, txtPozivNaBroj;
	private ADrzavaComboBox cbDrzave;
	private BOpstina opstina;
	private BOpstinePanel panel;
	
	public BOpstinePregled(BOpstinePanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblDrzava = new LabelaObaveznaBold(resource.getString("lbl.drzava"));
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		lblSr = new LabelaBold(resource.getString("lbl.sr"));
		lblEn = new LabelaBold(resource.getString("lbl.en"));
		lblDe = new LabelaBold(resource.getString("lbl.de"));
		lblPozivniBroj = new LabelaBold(resource.getString("lbl.pozivnibroj"));
		lblPostanskiBroj = new LabelaBold(resource.getString("lbl.postanskibroj"));
		lblPozivNaBroj = new LabelaBold(resource.getString("lbl.pozivnabroj"));
		
		cbDrzave = new ADrzavaComboBox(resource);
		try {
			ResponseEntity<ADrzavaOdgovor> odgovor = panel.vratiRestDrzava().pretraga(null);
			if(odgovor != null) {
				switch(odgovor.getStatusCodeValue()) {
				case Odgovori.IZVRSEN:
					cbDrzave.setItems(FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getLista()));
					break;
				default:
					panel.pokaziNijeUspesno(panel.vratiOsnovniLayout().vratiPrevod("obavestenja.nijeuspesno"), 
							panel.vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
					}
				}else {
					panel.vratiNemaOdgovoraServera();
					}
			}catch (Exception e) {
				panel.vratiNemaOdgovoraServera();
				}
		/*
		cbDrzave.setOnAction(e -> {
			if(cbDrzave.getValue() != null) {
				try {
					ResponseEntity<BOpstinaOdgovor> odgovor = panel.vratiRestOpstina().poDrzavi(cbDrzave.getValue());
					panel.statusOdgovora(odgovor);
					}catch(Exception ex) {
						ex.printStackTrace();
						panel.osveziTabelu(null);
						panel.vratiNemaOdgovoraServera();
						}
				}else {
					panel.osveziTabelu(null);
					}
			postaviNovo();
			});
		*/
		txtNaziv = new TextField();
		txtSr = new TextField();
		txtEn = new TextField();
		txtDe = new TextField();
		txtPozivniBroj = new TextField();
		txtPostanskiBroj = new TextField();
		txtPozivNaBroj = new TextField();
		}
	
	private void popakujElemente() {
		Double minSirina = 100.0;;
		
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
		
		grid.addColumn(0, lblDrzava); grid.addColumn(1, cbDrzave); grid.addColumn(2, lblNaziv); grid.addColumn(3, txtNaziv); 
		grid.addColumn(4, lblSr); grid.addColumn(5, txtSr); grid.addColumn(6, lblEn); grid.addColumn(7, txtEn);
		grid.addColumn(0, lblDe); grid.addColumn(1, txtDe); grid.addColumn(2, lblPostanskiBroj); grid.addColumn(3, txtPostanskiBroj); 
		grid.addColumn(4, lblPozivniBroj); grid.addColumn(5, txtPozivniBroj); grid.addColumn(6, lblPozivNaBroj); grid.addColumn(7, txtPozivNaBroj);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
		}
	
	public void postaviObjekat(BOpstina opstina) {
		if(opstina != null) {
			this.opstina = opstina;
			cbDrzave.setValue(opstina.getDrzava());
			txtNaziv.setText(opstina.getNaziv());
			txtSr.setText(opstina.getSr());
			txtEn.setText(opstina.getEn());
			txtDe.setText(opstina.getDe());
			txtPozivniBroj.setText(opstina.getPozivniBroj());
			txtPostanskiBroj.setText(opstina.getPostanskiBroj());
			txtPozivNaBroj.setText(opstina.getPozivNaBroj());
			}else {
				postaviNovo();
				}
		}
	
	public BOpstina preuzmiObjekat() {
		if(this.opstina == null) {
			opstina = new BOpstina();
			opstina.setId(0L);
			}
		opstina.setDrzava(cbDrzave.getValue());
		opstina.setNaziv(txtNaziv.getText() == null ? null : txtNaziv.getText().trim());
		opstina.setSr(txtSr.getText() == null ? null : txtSr.getText().trim());
		opstina.setEn(txtEn.getText() == null ? null : txtEn.getText().trim());
		opstina.setDe(txtDe.getText() == null ? null : txtDe.getText().trim());
		opstina.setPozivniBroj(txtPozivniBroj.getText().trim());
		opstina.setPostanskiBroj(txtPostanskiBroj.getText().trim());
		opstina.setPozivNaBroj(txtPozivNaBroj.getText().trim());
		return opstina;
		}
	
	public void postaviNovo() {
		opstina = null;
		cbDrzave.getSelectionModel().clearSelection();
		txtNaziv.setText("");
		txtSr.setText("");
		txtEn.setText("");
		txtDe.setText("");
		txtPozivniBroj.setText("");
		txtPostanskiBroj.setText("");
		txtPozivNaBroj.setText("");
		}
	
	public void postaviSirinu() {
		cbDrzave.setPrefWidth(txtNaziv.getWidth());
		}

	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals("")) {
			unos = false;
			}
		if(cbDrzave.getValue() == null) {
			unos = false;
			}
		return unos;
		}
	
	}
