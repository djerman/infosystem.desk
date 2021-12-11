package rs.atekom.infosystem.desk.paneli.c.mesto;

import java.util.ResourceBundle;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;
import rs.atekom.infosystem.desk.paneli.a.drzava.ADrzavaComboBox;
import rs.atekom.infosystem.desk.paneli.b.opstina.BOpstinaComboBox;

public class CMestoPregled extends GridPane{

	private Label lblDrzava, lblOpstina, lblNaziv, lblSr, lblEn, lblPozivniBroj, lblPostanskiBroj;
	private TextField txtNaziv, txtSr, txtEn, txtPozivniBroj, txtPostanskiBroj;
	private ADrzavaComboBox cbDrzave;
	private BOpstinaComboBox cbOpstine;
	private CMesto mesto;
	private CMestoPanel panel;
	
	
	public CMestoPregled(CMestoPanel panel, ResourceBundle resource) {
		this.panel = panel;
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		//System.out.println(resource.getLocale());
		lblDrzava = new Label(resource.getString("lbl.drzava"));
		lblDrzava.setStyle("-fx-text-fill: red");
		lblOpstina= new Label(resource.getString("lbl.opstina"));
		lblNaziv = new Label(resource.getString("lbl.naziv"));
		lblNaziv.setStyle("-fx-text-fill: red");
		lblSr = new Label(resource.getString("lbl.sr"));
		lblEn = new Label(resource.getString("lbl.en"));
		lblPostanskiBroj = new Label(resource.getString("lbl.postanskibroj"));
		lblPozivniBroj = new Label(resource.getString("lbl.pozivnibroj"));

		//cbDrzave = new SearchableComboBox<ADrzava>();
		cbDrzave = new ADrzavaComboBox(resource);
		//cbOpstine = new SearchableComboBox<BOpstina>();
		cbOpstine = new BOpstinaComboBox(resource);
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
					ResponseEntity<ADrzavaOdgovor> odgovor = panel.vratiRestDrzava().liste(cbDrzave.getValue());
					panel.osveziDrzave = false;
					panel.statusOdgovora(odgovor);
					}catch (Exception ee) {
						// TODO: handle exception
						}
				}else {
					//panel.osveziTabelu(null);
					}
			});
		/*
		cbOpstine.setOnAction(e -> {
			if(cbOpstine.getValue() != null) {
				try {
					ResponseEntity<CMestoOdgovor> odgovor = panel.vratiRestMesto().poOpstini(cbOpstine.getValue());
					panel.statusOdgovora(odgovor);
					}catch(Exception ex) {
						panel.osveziTabelu(null);
						panel.vratiNemaOdgovoraServera();
						}
				}else {
					panel.osveziTabelu(null);
					}
			});
		*/
		txtNaziv = new TextField();
		txtSr = new TextField();
		txtEn = new TextField();
		txtPozivniBroj = new TextField();
		txtPostanskiBroj = new TextField();
		}
	
	private void popakujElemente() {
		Double minSirina1 = 80.0;
		Double minSirina2 = 120.0;
		Double minSirina3 = 120.0;
		Double minSirina4 = 60.0;

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
		
		ColumnConstraints col7 = new ColumnConstraints();
		col7.setHalignment(HPos.RIGHT);
		col7.setMinWidth(minSirina4);
		ColumnConstraints col8 = new ColumnConstraints();
		col8.setHgrow(Priority.ALWAYS);
		
		getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
		addColumn(0, lblDrzava); addColumn(1, cbDrzave); addColumn(2, lblOpstina); addColumn(3, cbOpstine); addColumn(4, lblNaziv); addColumn(5, txtNaziv); addColumn(6, lblSr); addColumn(7, txtSr);
		addColumn(0, lblEn); addColumn(1, txtEn); addColumn(2, lblPozivniBroj); addColumn(3, txtPozivniBroj); addColumn(4, lblPostanskiBroj); addColumn(5, txtPostanskiBroj);
		
		}
	
	public void postaviObjekat(CMesto mesto) {
		if(mesto != null) {
			this.mesto = mesto;
			cbDrzave.setValue(mesto.getDrzava());
			cbOpstine.setValue(mesto.getOpstina());
			txtNaziv.setText(mesto.getNaziv());
			txtSr.setText(mesto.getSr());
			txtEn.setText(mesto.getEn());
			txtPozivniBroj.setText(mesto.getPozivniBroj());
			txtPostanskiBroj.setText(mesto.getPostanskiBroj());
			}else {
				postaviNovo();
				}
		}
	
	public CMesto preuzmiObjekat() {
		if(this.mesto == null) {
			mesto = new CMesto();
			mesto.setId(0L);
			}
		mesto.setDrzava(cbDrzave.getValue());
		mesto.setOpstina(cbOpstine.getValue());
		mesto.setNaziv(txtNaziv.getText() == null ? null : txtNaziv.getText().trim());
		mesto.setSr(txtSr.getText() == null ? null : txtSr.getText().trim());
		mesto.setEn(txtEn.getText() == null ? null : txtEn.getText().trim());
		mesto.setPozivniBroj(txtPozivniBroj.getText() == null ? null : txtPozivniBroj.getText().trim());
		mesto.setPostanskiBroj(txtPostanskiBroj.getText() == null ? null : txtPostanskiBroj.getText().trim());
		return mesto;
		}
	
	public void postaviNovo() {
		mesto = null;
		cbDrzave.setValue(null);
		cbOpstine.setValue(null);
		txtNaziv.setText("");
		txtSr.setText("");
		txtEn.setText("");
		txtPozivniBroj.setText("");
		txtPostanskiBroj.setText("");
		}
	
	public void postaviSirinu() {
		cbDrzave.setPrefWidth(txtNaziv.getWidth());
		cbOpstine.setPrefWidth(txtNaziv.getWidth());
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
	
	public void postaviOpstine(ObservableList<BOpstina> lista) {
		cbOpstine.getItems().clear();
		cbOpstine.getItems().add(null);
		if(lista != null) {
			cbOpstine.getItems().addAll(lista);
			}
		}
	
	public void postaviDrzave(ObservableList<ADrzava> lista) {
		cbDrzave.getItems().clear();
		cbDrzave.getItems().add(null);
		if(lista != null) {
			cbDrzave.getItems().addAll(lista);
			}
		}
	
	}
