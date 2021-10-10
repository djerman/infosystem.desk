package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class KrajIzbornik extends OpstiIzbornik{

	public KrajIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.kraj"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.CLOSE).color(Color.BROWN));
		
		/*
		 * uklonjen iz menija jer se ne koristi i prebačen je u IZBOR PO GRUPI
		 */
		MenuItem datum = new MenuItem(layout.vratiPrevod("izbornik.kraj.datum"), fontAwesome.create(FontAwesome.Glyph.CALENDAR_ALT).color(Color.CADETBLUE));
		//datum.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.CONTROL_ANY));
		datum.setOnAction(e -> {
			//osnovniLayout.dodajTab("CENOVNIK", new IzborPoArtikluPanel(osnovniLayout));
			});
		
		MenuItem sat = new MenuItem(layout.vratiPrevod("izbornik.kraj.sat"), fontAwesome.create(FontAwesome.Glyph.CLOCK_ALT).color(Color.CADETBLUE));
		sat.setOnAction(e -> {
			//osnovniLayout.dodajTab("ARTIKLI PO KATEGORIJAMA", new ArtikliPoGrupamaPanel(osnovniLayout));
			});
		//sat.setAccelerator(new KeyCodeCombination(KeyCode.F2, KeyCombination.CONTROL_ANY));
		
		MenuItem help = new MenuItem(layout.vratiPrevod("izbornik.kraj.pomoc"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.DARKVIOLET));
		//help.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
		help.setOnAction(e -> {
			//osnovniLayout.dodajTab(unosArtikala.getText(), new ArtikalPanel(osnovniLayout));
			});
		
		//Menu grupePodgrupePartnera = new Menu("GRUPE I PODGRUPE PARTNERA", fontAwesome.create(FontAwesome.Glyph.GROUP).color(Color.MEDIUMAQUAMARINE));
		
		MenuItem oProgramu = new MenuItem(layout.vratiPrevod("izbornik.kraj.oprogramu"), fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.DARKBLUE));
		oProgramu.setOnAction(e -> {
			//osnovniLayout.dodajTab(kataloziPartnera.getText(), new KatalogPanel(osnovniLayout, true));
			});
		
		MenuItem izmenaKlijenta = new MenuItem(layout.vratiPrevod("izbornik.kraj.izmenaklijenta"), fontAwesome.create(FontAwesome.Glyph.BOOKMARK_ALT).color(Color.DARKORANGE));
		izmenaKlijenta.setOnAction(e -> {
			//osnovniLayout.dodajTab(bransePartnera.getText(), new BransaPanel(osnovniLayout, true));
			});
		
		//grupePodgrupePartnera.getItems().addAll(oProgramu, bransePartnera);
		
		//Menu cenovnici = new Menu("ЦЕНОВНИЦИ", fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.GREEN));
		
		MenuItem izmenaLozinke =  new MenuItem(layout.vratiPrevod("izbornik.kraj.izmenalozinke"), fontAwesome.create(FontAwesome.Glyph.BITCOIN).color(Color.MEDIUMTURQUOISE));
		izmenaLozinke.setOnAction(e -> {
			//osnovniLayout.dodajTab(cenovnikPoKupcu.getText(), new CenovniciPanel(osnovniLayout));
			});
		
		MenuItem kopijaPodataka =  new MenuItem(layout.vratiPrevod("izbornik.kraj.kopijapodataka"), fontAwesome.create(FontAwesome.Glyph.BRIEFCASE).color(Color.MEDIUMVIOLETRED));
		
		MenuItem ciscenjeEkrana =  new MenuItem(layout.vratiPrevod("izbornik.kraj.ciscenjeekrana"), fontAwesome.create(FontAwesome.Glyph.BUYSELLADS).color(Color.MIDNIGHTBLUE));
		
		MenuItem kursValute = new MenuItem(layout.vratiPrevod("izbornik.kraj.kursvalute"), fontAwesome.create(FontAwesome.Glyph.CALCULATOR).color(Color.DARKSEAGREEN));
		kursValute.setOnAction(e -> {
			//osnovniLayout.dodajTab(kalkulacija.getText(), new KalkulacijePanel(osnovniLayout));
			});
		
		//cenovnici.getItems().addAll(cenovnikPoKupcu, uporedniCenovnikPoKupcu, cenovnikPoDobavljacima);
		
		MenuItem izlaz =  new MenuItem(layout.vratiPrevod("izbornik.kraj.izlaz"), fontAwesome.create(FontAwesome.Glyph.VENUS).color(Color.MEDIUMSEAGREEN));
		izlaz.setOnAction(e -> {
			//osnovniLayout.dodajTab(tipovi.getText(), new TipArtiklaPanel(osnovniLayout));
			});
		
		/*
		MenuItem statusi =  new MenuItem("STATUSI", fontAwesome.create(FontAwesome.Glyph.STAR).color(Color.MEDIUMSPRINGGREEN));
		statusi.setOnAction(e -> {
			//osnovniLayout.dodajTab(statusi.getText(), new StatusiArtikalaPanel(osnovniLayout));
			});
		
		Menu grupePodgrupe = new Menu("GRUPE I PODGRUPE", fontAwesome.create(FontAwesome.Glyph.GROUP).color(Color.MEDIUMSEAGREEN));
		
		MenuItem katalozi = new MenuItem("KATALOZI", fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.DARKGREEN));
		katalozi.setOnAction(e -> {
			//osnovniLayout.dodajTab(katalozi.getText(), new KatalogPanel(osnovniLayout, false));
			});
		
		MenuItem grupe = new MenuItem("GRUPE", fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.DARKBLUE));
		grupe.setOnAction(e -> {
			//osnovniLayout.dodajTab(grupe.getText(), new GrupaArtikalaPanel(osnovniLayout, false));
			});
		
		MenuItem podGrupe = new MenuItem("PODGRUPE", fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.DARKRED));
		podGrupe.setOnAction(e -> {
			//osnovniLayout.dodajTab(podGrupe.getText(), new GrupaArtikalaPanel(osnovniLayout, false));
			});
		
		grupePodgrupe.getItems().addAll(katalozi, grupe, podGrupe);
		*/
		getItems().addAll(datum, sat, help, izmenaLozinke, kopijaPodataka, ciscenjeEkrana, kursValute, izlaz);
		}
	
	}
