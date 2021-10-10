package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class PreglediIzbornik extends OpstiIzbornik{

	public PreglediIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.pregledi"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.BROWN));
		Menu zaRobno = new Menu(layout.vratiPrevod("izbornik.pregledi.zarobno"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKMAGENTA));
		MenuItem lagerRobe = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.lagerrobe"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.FIREBRICK));
		SeparatorMenuItem lagerRobeSep = new SeparatorMenuItem();
		MenuItem odstupanjeArtikala = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.odstupanjeartikala"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKVIOLET));
		MenuItem greske = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.greskedokument"), fontAwesome.create(FontAwesome.Glyph.CART_ARROW_DOWN).color(Color.BURLYWOOD));
		SeparatorMenuItem greskeSep = new SeparatorMenuItem();
		MenuItem analizaVpPrometa = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.analizavpprometa"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.INDIANRED));
		MenuItem analizaMpPrometa = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.analizampprometa"), fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.TOMATO));
		MenuItem realizacija = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.realizacija"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.FIREBRICK));
		SeparatorMenuItem realizacijaSep = new SeparatorMenuItem();
		MenuItem karticaRobe = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.karticarobe"), fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.DARKVIOLET));
		SeparatorMenuItem karticaSep = new SeparatorMenuItem();
		MenuItem ulauIzlazVeleprodaja = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.veleprodaja"), fontAwesome.create(FontAwesome.Glyph.CART_ARROW_DOWN).color(Color.BURLYWOOD));
		MenuItem ulazIzlazMaloprodaja = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.maloprodaja"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.INDIANRED));
		MenuItem ulazIzlazPoPartneru = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zarobno.popartneru"), fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.TOMATO));
		zaRobno.getItems().addAll(lagerRobe, lagerRobeSep, odstupanjeArtikala, greske, greskeSep, analizaVpPrometa, analizaMpPrometa, realizacija, realizacijaSep, 
				karticaRobe, karticaSep, ulauIzlazVeleprodaja, ulazIzlazMaloprodaja, ulazIzlazPoPartneru);
		
		Menu zaFinansije = new Menu(layout.vratiPrevod("izbornik.pregledi.zafinansije"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.FIREBRICK));
		//zaFinansije.setAccelerator(new KeyCodeCombination(KeyCode.F5, KeyCombination.SHIFT_DOWN));
		MenuItem modeliKnjizenja = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.modeliknjizenja"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		SeparatorMenuItem modeliSep = new SeparatorMenuItem();
		MenuItem sintetika = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.sintetika"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem analitika = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.analitika"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		MenuItem avansi = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.avansi"), fontAwesome.create(FontAwesome.Glyph.HAND_ALT_LEFT).color(Color.MEDIUMSPRINGGREEN));
		SeparatorMenuItem avansiSep = new SeparatorMenuItem();
		MenuItem naloziPoKontu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.nalozipokontu"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART).color(Color.BROWN));
		MenuItem naloziPoDatumu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.nalozipodatumu"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART_O).color(Color.INDIGO));
		MenuItem stampaNaloga = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.stampanaloga"), fontAwesome.create(FontAwesome.Glyph.PRINT).color(Color.DARKORANGE));
		SeparatorMenuItem stampaSep = new SeparatorMenuItem();
		MenuItem dnevnikPoBroju = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.dnevnikpobroju"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem dnevnikPoDatumu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.dnevnikpodatumu"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		SeparatorMenuItem dnevnikSep = new SeparatorMenuItem();
		MenuItem kontoPoDatumu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.kontopodatumu"), fontAwesome.create(FontAwesome.Glyph.HAND_ALT_LEFT).color(Color.MEDIUMSPRINGGREEN));
		MenuItem kontoOdDoEur = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zafinansije.kontoeur"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART).color(Color.BROWN));
		zaFinansije.getItems().addAll(modeliKnjizenja, modeliSep, sintetika, analitika, avansi, avansiSep, naloziPoKontu, naloziPoDatumu, stampaNaloga, stampaSep,
				dnevnikPoBroju, dnevnikPoDatumu, dnevnikSep, kontoPoDatumu, kontoOdDoEur);
		
		Menu pregledZaPDV = new Menu(layout.vratiPrevod("izbornik.pregledi.zapdv"), fontAwesome.create(FontAwesome.Glyph.CART_ARROW_DOWN).color(Color.HOTPINK));
		MenuItem kontaZaPDV = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.kontazapdv"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		SeparatorMenuItem kontaSep = new SeparatorMenuItem();
		MenuItem knjizenjaNabavke = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.knjizenjanabavke"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem knjizenjaProdaje = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.knjizenjaprodaje"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		SeparatorMenuItem knjizenjaSep = new SeparatorMenuItem();
		MenuItem kontrolaKnjizenja = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.kontrolaknjizenja"), fontAwesome.create(FontAwesome.Glyph.HAND_ALT_LEFT).color(Color.MEDIUMSPRINGGREEN));
		SeparatorMenuItem knjizenjeSep = new SeparatorMenuItem();
		MenuItem pdvNaKontu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.pdvnakontu"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART).color(Color.BROWN));
		SeparatorMenuItem naKontuSep = new SeparatorMenuItem();
		MenuItem stareKnjige = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.stareknjige"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART_O).color(Color.INDIGO));
		MenuItem evidencijePDV = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.evidencije"), fontAwesome.create(FontAwesome.Glyph.PRINT).color(Color.DARKORANGE));
		SeparatorMenuItem evidencijeSep = new SeparatorMenuItem();
		MenuItem poreskaPrijava = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.poreskaprijava"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem obrazacPOPDV = new MenuItem(layout.vratiPrevod("izbornik.pregledi.zapdv.poreskaprijava"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		pregledZaPDV.getItems().addAll(kontaZaPDV, kontaSep, knjizenjaNabavke, knjizenjaProdaje, knjizenjaSep, kontrolaKnjizenja, knjizenjeSep, pdvNaKontu,
				naKontuSep, stareKnjige, evidencijePDV, evidencijeSep, poreskaPrijava, obrazacPOPDV);
		
		Menu kupciDobavljaci = new Menu(layout.vratiPrevod("izbornik.pregledi.partneri"), fontAwesome.create(FontAwesome.Glyph.DASHBOARD).color(Color.INDIANRED));
		MenuItem kupciPoRacunu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.kupciporacunu"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem kupDobPoSaldu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.kupciposaldu"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		SeparatorMenuItem kupciSep = new SeparatorMenuItem();
		MenuItem kupciPoDatumu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.kupcipodatumu"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem kupciPoValuti = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.kupcipovaluti"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		SeparatorMenuItem kupciPoSep = new SeparatorMenuItem();
		MenuItem dobavljaciPoDatumu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.dobavljacipodatumu"), fontAwesome.create(FontAwesome.Glyph.HAND_ALT_LEFT).color(Color.MEDIUMSPRINGGREEN));
		MenuItem dobavljaciPoValuti = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.dobavljacipovaluti"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART).color(Color.BROWN));
		SeparatorMenuItem dobavljaciSep = new SeparatorMenuItem();
		MenuItem zbirno = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.zbirnoeur"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART_O).color(Color.INDIGO));
		MenuItem karticaZaKRV = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.karticazakr"), fontAwesome.create(FontAwesome.Glyph.PRINT).color(Color.DARKORANGE));
		SeparatorMenuItem karticaKRSep = new SeparatorMenuItem();
		MenuItem listaOtkupa = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.listaotkupa"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem zbirnaListaOtkupa = new MenuItem(layout.vratiPrevod("izbornik.pregledi.partneri.zbirnalistaotkupa"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		kupciDobavljaci.getItems().addAll(kupciPoRacunu, kupDobPoSaldu, kupciSep, kupciPoDatumu, kupciPoValuti, kupciPoSep, dobavljaciPoDatumu, dobavljaciPoValuti, dobavljaciSep, 
				zbirno, karticaZaKRV, karticaKRSep, listaOtkupa, zbirnaListaOtkupa);
		
		Menu bilansi = new Menu(layout.vratiPrevod("izbornik.pregledi.bilansi"), fontAwesome.create(FontAwesome.Glyph.PIE_CHART).color(Color.LIGHTGREEN));
		MenuItem obrazacKepu = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.obrazackepu"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.DARKORANGE));
		MenuItem aopi = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.aopi"), fontAwesome.create(FontAwesome.Glyph.PIE_CHART).color(Color.DARKBLUE));
		SeparatorMenuItem aopiSep = new SeparatorMenuItem();
		MenuItem izradaBilansa = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.izradabilansa"), fontAwesome.create(FontAwesome.Glyph.AREA_CHART).color(Color.ROSYBROWN));
		MenuItem proveraBilansa = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.proverabilansa"), fontAwesome.create(FontAwesome.Glyph.BARS).color(Color.DARKSLATEBLUE));
		SeparatorMenuItem bilansSep = new SeparatorMenuItem();
		MenuItem bilansStanja = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.bilansstanja"), fontAwesome.create(FontAwesome.Glyph.HAND_ALT_LEFT).color(Color.MEDIUMSPRINGGREEN));
		MenuItem bilansUspeha = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.bilansuspeha"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART).color(Color.BROWN));
		MenuItem tokoviGotovine = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.tokovigotovine"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART_O).color(Color.INDIGO));
		MenuItem statistickiAneks = new MenuItem(layout.vratiPrevod("izbornik.pregledi.bilansi.statistickianeks"), fontAwesome.create(FontAwesome.Glyph.LINE_CHART).color(Color.GOLDENROD));
		bilansi.getItems().addAll(obrazacKepu, aopi, aopiSep, izradaBilansa, proveraBilansa, bilansSep, bilansStanja, bilansUspeha, tokoviGotovine, statistickiAneks);
		
		MenuItem poslovanjeOdDo =  new MenuItem(layout.vratiPrevod("izbornik.pregledi.poslovanje"), fontAwesome.create(FontAwesome.Glyph.BARCODE).color(Color.BLACK));
		MenuItem knjizeniDokumenti = new MenuItem(layout.vratiPrevod("izbornik.pregledi.knjizenidokumenti"), fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.LIGHTCORAL));
		
		MenuItem sql = new MenuItem(layout.vratiPrevod("izbornik.pregledi.sql"), fontAwesome.create(FontAwesome.Glyph.CALCULATOR).color(Color.NAVY));
		
		getItems().addAll(zaRobno, zaFinansije, pregledZaPDV, kupciDobavljaci, bilansi, poslovanjeOdDo, knjizeniDokumenti, sql);
		}
	
	}
