package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.PocetniTab;
import rs.atekom.infosystem.desk.b.TipDokumentaPanel;

public class PodaciIzbornik extends OpstiIzbornik{
	
	public PodaciIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.podaci"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.FILE).color(Color.BLACK));
		MenuItem pocetna = new MenuItem(layout.vratiPrevod("tab.pocetni"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART_O).color(Color.CRIMSON));
		pocetna.setOnAction(e -> osnovniLayout.dodajTab(new PocetniTab()));
		
		MenuItem tipDokumenta = new MenuItem(layout.vratiPrevod("izbornik.podaci.tipdokumenta"), new Glyph("FontAwesome", FontAwesome.Glyph.PRINT).color(Color.BISQUE));
		tipDokumenta.setOnAction(e -> osnovniLayout.dodajTab(layout.vratiPrevod("izbornik.podaci.tipdokumenta"), new TipDokumentaPanel(osnovniLayout)));
		
		MenuItem poreskeTarife = new MenuItem(layout.vratiPrevod("izbornik.podaci.poresketarife"), new Glyph("FontAwesome", FontAwesome.Glyph.TASKS).color(Color.BLACK));
		
		MenuItem artikli = new MenuItem(layout.vratiPrevod("izbornik.podaci.artikli"), fontAwesome.create(FontAwesome.Glyph.CART_PLUS).color(Color.DARKVIOLET));
		artikli.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
		artikli.setOnAction(e -> {
			//osnovniLayout.dodajTab(unosArtikala.getText(), new ArtikalPanel(osnovniLayout));
			});
		
		MenuItem kupci = new MenuItem(layout.vratiPrevod("izbornik.podaci.kupci"),  new Glyph("FontAwesome", FontAwesome.Glyph.MONEY).color(Color.GREEN));
		kupci.setAccelerator(new KeyCodeCombination(KeyCode.F1, KeyCombination.CONTROL_ANY));
		kupci.setOnAction(e -> {
			//osnovniLayout.dodajTab(kupci.getText(), new PartneriPanel(osnovniLayout, true, false));
			});
		
		MenuItem dobavljaci = new MenuItem(layout.vratiPrevod("izbornik.podaci.dobavljaci"), fontAwesome.create(FontAwesome.Glyph.BELL_SLASH).color(Color.DARKSLATEBLUE));
		dobavljaci.setAccelerator(new KeyCodeCombination(KeyCode.F5, KeyCombination.CONTROL_ANY));
		dobavljaci.setOnAction(e -> {
			//osnovniLayout.dodajTab(dobavljaci.getText(), new PartneriPanel(osnovniLayout, false, false));
			});
		
		MenuItem objekti = new MenuItem(layout.vratiPrevod("izbornik.podaci.objekti"), fontAwesome.create(FontAwesome.Glyph.BUILDING).color(Color.BLUE));
		objekti.setOnAction(e -> {
			//osnovniLayout.dodajTab(organizacije.getText(), new OrganizacijePanel(osnovniLayout, null));
			});
		
		MenuItem normativ = new MenuItem(layout.vratiPrevod("izbornik.podaci.normativ"), fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.DARKBLUE));
		normativ.setOnAction(e -> {
			//osnovniLayout.dodajTab(kataloziPartnera.getText(), new KatalogPanel(osnovniLayout, true));
			});
		
		MenuItem izlaz = new MenuItem(layout.vratiPrevod("izbornik.podaci.izlaz"), fontAwesome.create(FontAwesome.Glyph.CLOSE).color(Color.RED));
		
		MenuItem beleske = new MenuItem(layout.vratiPrevod("izbornik.podaci.beleske"), fontAwesome.create(FontAwesome.Glyph.SIMPLYBUILT).color(Color.AQUAMARINE));
		beleske.setOnAction(e -> {
			//osnovniLayout.dodajTab(drzave.getText(), new DrzavePanel(osnovniLayout));
			});
		
		MenuItem imenaAdrese = new MenuItem(layout.vratiPrevod("izbornik.podaci.imenaadrese"), fontAwesome.create(FontAwesome.Glyph.USERS).color(Color.BLUE));
		imenaAdrese.setOnAction(e -> {
			//osnovniLayout.dodajTab(kontakti.getText(), new KontaktiPanel(osnovniLayout, null, true, 2));
			});
		
		MenuItem transakcije = new MenuItem(layout.vratiPrevod("izbornik.podaci.transakcije"), fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.DODGERBLUE));
		transakcije.setOnAction(e -> {
			//osnovniLayout.dodajTab(naciniPlacanja.getText(), new TipoviPlacanjaPanel(osnovniLayout));
			});
		
		getItems().addAll(pocetna, tipDokumenta, poreskeTarife, artikli, kupci, dobavljaci, objekti, normativ, beleske, 
				imenaAdrese, transakcije, izlaz);
		}
	
	}
