package rs.atekom.infosystem.desk.paneli.c.mesto;

import java.util.ResourceBundle;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.baza.a.drzava.ADrzavaOdgovor;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;
import rs.atekom.infosystem.desk.paneli.a.drzava.ADrzavaComboBox;
import rs.atekom.infosystem.desk.paneli.b.opstina.BOpstinaComboBox;

public class CMestoPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblDrzava,lblNaziv;
	private LabelaBold lblOpstina, lblSr, lblEn, lblPozivniBroj, lblPostanskiBroj;
	private TextField txtNaziv, txtSr, txtEn, txtPozivniBroj, txtPostanskiBroj;
	private ADrzavaComboBox cbDrzave;
	private BOpstinaComboBox cbOpstine;
	private CMesto mesto;
	private CMestoPanel panel;
	
	public CMestoPregled(CMestoPanel panel, ResourceBundle resource) {
		this.panel = panel;
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		//System.out.println(resource.getLocale());
		lblDrzava = new LabelaObaveznaBold(resource.getString("lbl.drzava"));
		//lblDrzava.setStyle("-fx-text-fill: red");
		lblOpstina= new LabelaBold(resource.getString("lbl.opstina"));
		lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
		//lblNaziv.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
		lblSr = new LabelaBold(resource.getString("lbl.sr"));
		lblEn = new LabelaBold(resource.getString("lbl.en"));
		lblPostanskiBroj = new LabelaBold(resource.getString("lbl.postanskibroj"));
		lblPozivniBroj = new LabelaBold(resource.getString("lbl.pozivnibroj"));

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
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
		grid.addColumn(0, lblDrzava); grid.addColumn(1, cbDrzave); grid.addColumn(2, lblOpstina); grid.addColumn(3, cbOpstine); 
		grid.addColumn(4, lblNaziv); grid.addColumn(5, txtNaziv); grid.addColumn(6, lblSr); grid.addColumn(7, txtSr);
		grid.addColumn(0, lblEn); grid.addColumn(1, txtEn); grid.addColumn(2, lblPozivniBroj); grid.addColumn(3, txtPozivniBroj); 
		grid.addColumn(4, lblPostanskiBroj); grid.addColumn(5, txtPostanskiBroj);
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
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
		if(cbOpstine.getItems() != null && cbOpstine.getItems().size() > 0) {
			cbOpstine.getItems().clear();
			}
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
