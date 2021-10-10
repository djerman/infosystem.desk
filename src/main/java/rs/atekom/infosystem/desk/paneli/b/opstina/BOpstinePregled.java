package rs.atekom.infosystem.desk.paneli.b.opstina;

import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.baza.b.BOpstinaOdgovor;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;

public class BOpstinePregled extends GridPane{

	private Label lblDrzava, lblNaziv, lblSr, lblEn, lblPozivniBroj, lblPostanskiBroj;
	private TextField txtNaziv, txtSr, txtEn, txtPozivniBroj, txtPostanskiBroj;
	private ComboBox<ADrzava> cbDrzave;
	private BOpstina opstina;
	private BOpstinePanel panel;
	
	public BOpstinePregled(BOpstinePanel panel, ResourceBundle resource) {
		this.panel = panel;
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblDrzava = new Label(resource.getString("lbl.drzava"));
		lblDrzava.setStyle("-fx-text-fill: red");
		lblNaziv = new Label(resource.getString("lbl.naziv"));
		lblNaziv.setStyle("-fx-text-fill: red");
		lblSr = new Label(resource.getString("lbl.sr"));
		lblEn = new Label(resource.getString("lbl.en"));
		lblPozivniBroj = new Label(resource.getString("lbl.pozivnibroj"));
		lblPostanskiBroj = new Label(resource.getString("lbl.postanskibroj"));
		
		cbDrzave = new SearchableComboBox<ADrzava>();
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
		
		cbDrzave.setOnAction(e -> {
			if(cbDrzave.getValue() != null) {
				try {
					ResponseEntity<BOpstinaOdgovor> odgovor = panel.vratiRestOpstina().poDrzavi(cbDrzave.getValue());
					panel.statusOdgovora(odgovor);
					}catch(Exception ex) {
						panel.osveziTabelu(null);
						panel.vratiNemaOdgovoraServera();
						}
				}else {
					panel.osveziTabelu(null);
					}
			postaviNovo();
			});
		
		txtNaziv = new TextField();
		txtSr = new TextField();
		txtEn = new TextField();
		txtPozivniBroj = new TextField();
		txtPostanskiBroj = new TextField();
		}
	
	private void popakujElemente() {
		Double minSirina = 80.0;
		Double minSirina2 = 120.0;
		Double minSirina3 = 120.0;
		
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
		
		addColumn(0, lblDrzava); addColumn(1, cbDrzave); addColumn(2, lblNaziv); addColumn(3, txtNaziv); addColumn(4, lblSr); addColumn(5, txtSr);
		addColumn(0, lblEn); addColumn(1, txtEn); addColumn(2, lblPozivniBroj); addColumn(3, txtPozivniBroj); addColumn(4, lblPostanskiBroj); addColumn(5, txtPostanskiBroj);
		}
	
	public void postaviObjekat(BOpstina opstina) {
		if(opstina != null) {
			this.opstina = opstina;
			//cbDrzave.setValue(opstina.getDrzava());
			txtNaziv.setText(opstina.getNaziv());
			txtSr.setText(opstina.getSr());
			txtEn.setText(opstina.getEn());
			txtPozivniBroj.setText(opstina.getPozivniBroj());
			txtPostanskiBroj.setText(opstina.getPostanskiBroj());
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
		opstina.setNaziv(txtNaziv.getText().trim());
		opstina.setSr(txtSr.getText().trim());
		opstina.setEn(txtEn.getText().trim());
		opstina.setPozivniBroj(txtPozivniBroj.getText().trim());
		opstina.setPostanskiBroj(txtPostanskiBroj.getText().trim());
		return opstina;
		}
	
	public void postaviNovo() {
		opstina = null;
		//cbDrzave.setValue(null);
		txtNaziv.setText("");
		txtSr.setText("");
		txtEn.setText("");
		txtPozivniBroj.setText("");
		txtPostanskiBroj.setText("");
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
