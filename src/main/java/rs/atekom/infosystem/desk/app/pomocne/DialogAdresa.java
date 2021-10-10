package rs.atekom.infosystem.desk.app.pomocne;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import rs.atekom.rmbaza.tabele.b.drzava.BDrzava;
import rs.atekom.rmbaza.tabele.c.CGrad;
import rs.atekom.rmbaza.tabele.c.partner.CPartner;
import rs.atekom.rmbaza.tabele.e.adresa.EAdresa;

public class DialogAdresa extends Dialog<EAdresa>{

	private LabelaDesno drzava, grad, oznaka, ulica, broj, posta, roba;
	private ComboBox<BDrzava> drzavaCbx;
	private ComboBox<CGrad> gradCbx;
	private TextField oznakaTxt, ulicaTxt, brojTxt;
	private CheckBox postaCb, robaCb;
	
	public DialogAdresa(EAdresa adresa, CPartner partner) {
		setTitle("UNOS/IZMENA ADRESE PARTNERA");
		napraviElemente();
		getDialogPane().setContent(popakujElement());
		
		ButtonType buttonTypeOk = new ButtonType("SAČUVAJ", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("OTKAŽI", ButtonData.CANCEL_CLOSE);
		
		getDialogPane().getButtonTypes().add(buttonTypeCancel);
		getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		if(adresa != null) {
			//drzavaCbx.setValue(adresa.getBDrzava());
			//gradCbx.setValue(adresa.getCGrad());
			oznakaTxt.setText(adresa.getOznaka());
			ulicaTxt.setText(adresa.getUlica());
			brojTxt.setText(adresa.getBroj());
			if(adresa.getPosta() != null) {
				postaCb.setSelected(adresa.getPosta());
			}
			if(adresa.getRoba() != null) {
				robaCb.setSelected(adresa.getRoba());
			}
		}
		
		setResultConverter(new Callback<ButtonType, EAdresa>() {
			@Override
			public EAdresa call(ButtonType b) {
				if(b == buttonTypeOk) {
					if(adresa == null) {
						return new EAdresa(partner.getAPretplatnik(), null, null, partner, null, drzavaCbx.getValue(), 
								gradCbx.getValue(), oznakaTxt.getText().trim(), ulicaTxt.getText().trim(), brojTxt.getText().trim(), 
								0, false);
					}else {
						//ne ubacujem prtplatnika ni partnera - nema izmene
						adresa.setBDrzava(drzavaCbx.getValue());
						adresa.setCGrad(gradCbx.getValue());
						adresa.setOznaka(oznakaTxt.getText().trim());
						adresa.setUlica(ulicaTxt.getText().trim());
						adresa.setBroj(brojTxt.getText().trim());
						adresa.setVerzija(adresa.getVerzija() + 1);
						return adresa;
					}
				}else {
					return null;
				}
			}
		});
	}
	
	private void napraviElemente() {
		drzava = new LabelaDesno("DRŽAVA: ");
		grad = new LabelaDesno("MESTO: ");
		oznaka = new LabelaDesno("OZNAKA: ");
		ulica = new LabelaDesno("ULICA: ");
		broj = new LabelaDesno("BROJ: ");
		posta = new LabelaDesno("ZA POŠTU: ");
		roba = new LabelaDesno("ZA ROBU: ");
		
		drzavaCbx = new ComboBox<BDrzava>(null);
		gradCbx = new ComboBox<CGrad>(null);
		oznakaTxt = new TextField();
		ulicaTxt = new TextField();
		brojTxt = new TextField();
		postaCb = new CheckBox();
		robaCb = new CheckBox();
		}
	
	private GridPane popakujElement() {
		GridPane grid = new GridPane();
		grid.setHgap(10); //horizontal gap in pixels
		grid.setVgap(10); //vertical gap in pixels
		grid.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid//(top/right/bottom/left)
		grid.add(drzava, 1, 1);
		grid.add(drzavaCbx, 2, 1);
		grid.add(grad, 1, 2);
		grid.add(gradCbx, 2, 2);
		grid.add(oznaka, 1, 3);
		grid.add(oznakaTxt, 2, 3);
		grid.add(ulica, 1, 4);
		grid.add(ulicaTxt, 2, 4);
		grid.add(broj, 1, 5);
		grid.add(brojTxt, 2, 5);
		grid.add(posta, 1, 6);
		grid.add(postaCb, 2, 6);
		grid.add(roba, 1, 7);
		grid.add(robaCb, 2, 7);
		return grid;
		}
	
	}
