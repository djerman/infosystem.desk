package rs.atekom.infosystem.desk.app.pomocne;

import java.math.BigDecimal;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import rs.atekom.rmbaza.tabele.a.pretplatnik.APretplatnik;
import rs.atekom.rmbaza.tabele.c.partner.CPartner;

public class DialogPartner extends Dialog<CPartner>{

	private LabelaDesno naziv, punNaziv, pib, maticni, kupac, dobavljac, zaposleni, napomena, valuta, limit, menica, vrednost;
	private TextField nazivTxt, punNazivTxt, pibTxt, maticniTxt, menicaTxt;
	private CheckBox kupacCb, dobavljacCb, zaposleniCb;
	private TextArea napomenaTa;
	private TekstCelobrojni valutaTxt, limitTxt, vrednostTxt;
	
	public DialogPartner(CPartner partner, APretplatnik pretplatnik) {
		setTitle("UNOS/IZMENA PARTNERA");
		napraviElemente();
		getDialogPane().setContent(popakujElemente());
		
		ButtonType buttonTypeOk = new ButtonType("SAČUVAJ", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("OTKAŽI", ButtonData.CANCEL_CLOSE);
		
		getDialogPane().getButtonTypes().add(buttonTypeCancel);
		getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		if(partner != null) {
			nazivTxt.setText(partner.getNaziv());
			punNazivTxt.setText(partner.getPunNaziv());
			pibTxt.setText(partner.getPib());
			maticniTxt.setText(partner.getMb());
			if(partner.getKupac() != null) {
				kupacCb.setSelected(partner.getKupac());
				}
			if(partner.getDobavljac() != null) {
				dobavljacCb.setSelected(partner.getDobavljac());
				}
			if(partner.getZaposleni() != null) {
				zaposleniCb.setSelected(partner.getZaposleni());
				}
			napomenaTa.setText(partner.getNapomena());
			valutaTxt.setText(String.valueOf(partner.getValuta()));
			limitTxt.setText(String.valueOf(partner.getDozvoljeno()));
			menicaTxt.setText(partner.getMenica());
			vrednostTxt.setText(String.valueOf(partner.getVrednost()));
			}
		
		setResultConverter(new Callback<ButtonType, CPartner>() {
			@Override
			public CPartner call(ButtonType b) {
				if(b == buttonTypeOk) {
					if(partner == null) {
						//System.out.println("partner null...");
						return null;
						/*return new CPartner(pretplatnik, null, null, "124578", nazivTxt.getText().trim(), 
								punNazivTxt.getText().trim(),  pibTxt.getText().trim(), maticniTxt.getText().trim(), kupacCb.isSelected(), 
								dobavljacCb.isSelected(), zaposleniCb.isSelected(), napomenaTa.getText().trim(), 
								valutaTxt.getText() == null ? 0 : Integer.valueOf(valutaTxt.getText()), 
								limitTxt.getText() == null ? 0 : Integer.valueOf(limitTxt.getText()), 
										Double.valueOf(0.0), menicaTxt.getText().trim(), 
										vrednostTxt.getText() == null ? 0 : Integer.valueOf(vrednostTxt.getText()));**/
					}else {
						//ne ubacujem pretplatnika, nema menjanja
						partner.setNaziv(nazivTxt.getText().trim());
						partner.setPunNaziv(punNazivTxt.getText().trim());
						partner.setPib(pibTxt.getText().trim());
						partner.setMb(maticniTxt.getText().trim());
						partner.setKupac(kupacCb.isSelected());
						partner.setDobavljac(dobavljacCb.isSelected());
						partner.setZaposleni(zaposleniCb.isSelected());
						partner.setNapomena(napomenaTa.getText().trim());
						partner.setValuta(Integer.valueOf(valutaTxt.getText()));
						partner.setDozvoljeno(new BigDecimal(limitTxt.getText()));
						partner.setMenica(menicaTxt.getText().trim());
						partner.setVrednost(Integer.valueOf(vrednostTxt.getText()));
						return partner;
					}
				}else {
					return null;
				}
			}
		});
	}
	
	private void napraviElemente() {
		naziv = new LabelaDesno("NAZIV PARTNERA: ");
		punNaziv = new LabelaDesno("PUN NAZIV PARTNERA:");
		pib = new LabelaDesno("PIB: ");
		maticni = new LabelaDesno("MATIČNI BROJ: ");
		kupac = new LabelaDesno("KUPAC: ");
		dobavljac = new LabelaDesno("DOBAVLJAČ: ");
		zaposleni = new LabelaDesno("ZAPOSLENI: ");
		napomena = new LabelaDesno("NAPOMENA: ");
		valuta = new LabelaDesno("DANA VALUTE: ");
		limit = new LabelaDesno("KREDITNI LIMIT: ");
		//LabelaDesno dug = new LabelaDesno("запослени: ");
		menica = new LabelaDesno("BROJ MENICE: ");
		vrednost = new LabelaDesno("VREDNOST MENICE: ");
		
		nazivTxt = new TextField();
		punNazivTxt = new TextField();
		pibTxt = new TextField();
		maticniTxt = new TextField();
		kupacCb = new CheckBox();
		dobavljacCb = new CheckBox();
		zaposleniCb = new CheckBox();
		napomenaTa = new TextArea();
		napomenaTa.setWrapText(true);
		napomenaTa.setMaxWidth(150);
		napomenaTa.setMaxHeight(50);
		valutaTxt = new TekstCelobrojni();
		limitTxt = new TekstCelobrojni();
		//TextDecimal dugTxt = new TextDecimal();
		menicaTxt = new TextField();
		vrednostTxt = new TekstCelobrojni();
	}
	
	private GridPane popakujElemente() {
		GridPane grid = new GridPane();
		grid.setHgap(10); //horizontal gap in pixels
		grid.setVgap(10); //vertical gap in pixels
		grid.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid//(top/right/bottom/left)
		grid.add(naziv, 1, 1);
		grid.add(nazivTxt, 2, 1);
		grid.add(punNaziv, 1, 2);
		grid.add(punNazivTxt, 2, 2);
		grid.add(pib, 1, 3);
		grid.add(pibTxt, 2, 3);
		grid.add(maticni, 1, 4);
		grid.add(maticniTxt, 2, 4);
		grid.add(kupac, 1, 5);
		grid.add(kupacCb, 2, 5);
		grid.add(dobavljac, 1, 6);
		grid.add(dobavljacCb, 2, 6);
		grid.add(zaposleni, 1, 7);
		grid.add(zaposleniCb, 2, 7);
		grid.add(napomena, 1, 8);
		grid.add(napomenaTa, 2, 8);
		grid.add(valuta, 1, 9);
		grid.add(valutaTxt, 2, 9);
		grid.add(limit, 1, 10);
		grid.add(limitTxt, 2, 10);
		grid.add(menica, 1, 11);
		grid.add(menicaTxt, 2, 11);
		grid.add(vrednost, 1, 12);
		grid.add(vrednostTxt, 2, 12);
		return grid;
	}
	
	/**
	 * Metoda za proveru da li su obavezna polja popunjena
	 * @return
	 */
	public Boolean provera() {
		Boolean popunjeno = true;
		if(nazivTxt.getText().isEmpty() || nazivTxt.getText().isBlank() || nazivTxt.getText() == null) {
			popunjeno = false;
		}
		if(punNazivTxt.getText().isEmpty() || punNazivTxt.getText().isBlank() || punNazivTxt.getText() == null) {
			popunjeno = false;
		}
		if(pibTxt.getText().isEmpty() || pibTxt.getText().isBlank() || pibTxt.getText() == null) {
			popunjeno = false;
		}
		if(maticniTxt.getText().isEmpty() || maticniTxt.getText().isBlank() || maticniTxt.getText() == null) {
			popunjeno = false;
		}
		return popunjeno;
	}
}
