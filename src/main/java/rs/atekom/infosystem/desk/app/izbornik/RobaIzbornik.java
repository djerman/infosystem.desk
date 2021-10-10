package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class RobaIzbornik extends OpstiIzbornik{

	public RobaIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.roba"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.RED));
		
		Menu nabavka =  new Menu(layout.vratiPrevod("izbornik.roba.nabavka"), fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.FIREBRICK));
		MenuItem nabavkaDomaca = new MenuItem(layout.vratiPrevod("izbornik.roba.domacaroba"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.FIREBRICK));
		MenuItem otkup = new MenuItem(layout.vratiPrevod("izbornik.roba.otkuparoba"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKVIOLET));
		MenuItem uvoz = new MenuItem(layout.vratiPrevod("izbornik.roba.uvoz"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.INDIANRED));
		nabavka.getItems().addAll(nabavkaDomaca, otkup, uvoz);
		
		Menu prodajaURS = new Menu(layout.vratiPrevod("izbornik.roba.prodajaurs"),  new Glyph("FontAwesome", FontAwesome.Glyph.SHOPPING_CART).color(Color.GREEN));
		MenuItem robaZaRS = new MenuItem(layout.vratiPrevod("izbornik.roba.prodajaurs.robazars"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.FIREBRICK));
		MenuItem uslugeZaRS = new MenuItem(layout.vratiPrevod("izbornik.roba.prodajaurs.uslugezars"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKVIOLET));
		MenuItem materijalRad = new MenuItem(layout.vratiPrevod("izbornik.roba.prodajaurs.materijalrad"), fontAwesome.create(FontAwesome.Glyph.CART_ARROW_DOWN).color(Color.BURLYWOOD));
		MenuItem mpRazduzenje = new MenuItem(layout.vratiPrevod("izbornik.roba.prodajaurs.mprazduzenje"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.INDIANRED));
		prodajaURS.getItems().addAll(robaZaRS, uslugeZaRS, materijalRad, mpRazduzenje);
		
		Menu ostalaProdaja = new Menu(layout.vratiPrevod("izbornik.roba.ostalaprodaja"),  new Glyph("FontAwesome", FontAwesome.Glyph.MONEY).color(Color.GREEN));
		MenuItem izvozRoba = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalaprodaja.izvozroba"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.FIREBRICK));
		MenuItem izvozUsluge = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalaprodaja.izvozusluge"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKVIOLET));
		MenuItem prodajaBezPDV = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalaprodaja.prodajabezpdv"), fontAwesome.create(FontAwesome.Glyph.CART_ARROW_DOWN).color(Color.BURLYWOOD));
		MenuItem avansPredracun = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalaprodaja.avanspredracun"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.INDIANRED));
		MenuItem knjiznaPisma = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalaprodaja.knjiznapisma"), fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.TOMATO));
		ostalaProdaja.getItems().addAll(izvozRoba, izvozUsluge, prodajaBezPDV, avansPredracun, knjiznaPisma);
		
		Menu proizvodnja = new Menu(layout.vratiPrevod("izbornik.roba.proizvodnja"), fontAwesome.create(FontAwesome.Glyph.CALENDAR).color(Color.RED));
		MenuItem prodajneCeneGP = new MenuItem(layout.vratiPrevod("izbornik.roba.proizvodnja.prodajnecenegp"), fontAwesome.create(FontAwesome.Glyph.PAPERCLIP).color(Color.CADETBLUE));
		MenuItem obradaNormativ = new MenuItem(layout.vratiPrevod("izbornik.roba.proizvodnja.obradasanormativom"), fontAwesome.create(FontAwesome.Glyph.COMPRESS).color(Color.DARKBLUE));
		MenuItem obradaBezNormativa = new MenuItem(layout.vratiPrevod("izbornik.roba.proizvodnja.obradabeznormativa"), fontAwesome.create(FontAwesome.Glyph.BRIEFCASE).color(Color.TOMATO));
		proizvodnja.getItems().addAll(prodajneCeneGP, obradaNormativ, obradaBezNormativa);
		
		Menu sredstva =  new Menu(layout.vratiPrevod("izbornik.roba.sredstva"), fontAwesome.create(FontAwesome.Glyph.BITCOIN).color(Color.MEDIUMTURQUOISE));
		MenuItem stopeZaIspravku = new MenuItem(layout.vratiPrevod("izbornik.roba.sredstva.stopezaispravku"), fontAwesome.create(FontAwesome.Glyph.COG).color(Color.CADETBLUE));
		MenuItem listaSredstava = new MenuItem(layout.vratiPrevod("izbornik.roba.sredstva.listasredstava"), fontAwesome.create(FontAwesome.Glyph.LIST).color(Color.DARKBLUE));
		MenuItem obracunSredstava = new MenuItem(layout.vratiPrevod("izbornik.roba.sredstva.obracunsredstava"), fontAwesome.create(FontAwesome.Glyph.COGS).color(Color.TOMATO));
		sredstva.getItems().addAll(stopeZaIspravku, listaSredstava, obracunSredstava);
		
		Menu ostalo = new Menu(layout.vratiPrevod("izbornik.roba.ostalo"), fontAwesome.create(FontAwesome.Glyph.VIACOIN).color(Color.DARKRED));
		MenuItem prenosnice = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalo.prenosnice"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.FIREBRICK));
		MenuItem nivelacije = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalo.nivelacije"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKVIOLET));
		MenuItem otpisi = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalo.otpisi"), fontAwesome.create(FontAwesome.Glyph.CART_ARROW_DOWN).color(Color.BURLYWOOD));
		MenuItem licniTroskovi = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalo.licnitroskovi"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.INDIANRED));
		MenuItem popisiVisak = new MenuItem(layout.vratiPrevod("izbornik.roba.ostalo.popisivisak"), fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.TOMATO));
		ostalo.getItems().addAll(prenosnice, nivelacije, otpisi, licniTroskovi, popisiVisak);
		
		MenuItem nijeKnjizeno = new MenuItem(layout.vratiPrevod("izbornik.roba.nijeknjizeno"), fontAwesome.create(FontAwesome.Glyph.YEN).color(Color.CADETBLUE));
		
		MenuItem rucnoBrisanje = new MenuItem(layout.vratiPrevod("izbornik.roba.rucnobrisanje"), fontAwesome.create(FontAwesome.Glyph.ARROWS_V).color(Color.LIGHTSTEELBLUE));
		
		MenuItem izmenaSifre = new MenuItem(layout.vratiPrevod("izbornik.roba.izmenasifre"), fontAwesome.create(FontAwesome.Glyph.BOOKMARK_ALT).color(Color.DARKORANGE));
		
		getItems().addAll(nabavka, prodajaURS, ostalaProdaja, proizvodnja, ostalo, sredstva, nijeKnjizeno, rucnoBrisanje, izmenaSifre);
		}
	}
