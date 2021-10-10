package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.paneli.d.pretplatnik.DPretplatnikPanel;

public class ServisIzbornik extends OpstiIzbornik{

	public ServisIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.servis"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.COG).color(Color.MEDIUMPURPLE));
		
		Menu datokete = new Menu(layout.vratiPrevod("izbornik.servis.datoteke"), fontAwesome.create(FontAwesome.Glyph.VIACOIN).color(Color.BLACK));
		MenuItem obnovaIndeksa = new MenuItem(layout.vratiPrevod("izbornik.servis.datoteke.obnovaindeksa"), fontAwesome.create(FontAwesome.Glyph.STACK_OVERFLOW).color(Color.DARKVIOLET));
		MenuItem sortiranje = new MenuItem(layout.vratiPrevod("izbornik.servis.datoteke.sortiranje"), fontAwesome.create(FontAwesome.Glyph.INSTITUTION).color(Color.GREEN));
		SeparatorMenuItem sortSep = new SeparatorMenuItem();
		MenuItem ciscenjeTabela = new MenuItem(layout.vratiPrevod("izbornik.servis.datoteke.ciscenjetabela"), fontAwesome.create(FontAwesome.Glyph.BUILDING_ALT).color(Color.CORNFLOWERBLUE));
		datokete.getItems().addAll(obnovaIndeksa, sortiranje, sortSep, ciscenjeTabela);
		
		Menu podesavanja = new Menu(layout.vratiPrevod("izbornik.servis.podesavanja"), fontAwesome.create(FontAwesome.Glyph.SIMPLYBUILT).color(Color.AQUAMARINE));
		MenuItem meni = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.meni"), fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.DARKCYAN));
		MenuItem baza = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.baza"), fontAwesome.create(FontAwesome.Glyph.USER_MD).color(Color.BLACK));
		MenuItem tip = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.tipovi"), fontAwesome.create(FontAwesome.Glyph.USER_SECRET).color(Color.ORANGE));
		MenuItem modelTipova = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.modelitipova"), fontAwesome.create(FontAwesome.Glyph.BANK).color(Color.GREEN));
		MenuItem specijali = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.specijali"), fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.VIOLET));
		MenuItem obaveznaKonta = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.obaveznakonta"), fontAwesome.create(FontAwesome.Glyph.BANK).color(Color.LIGHTSKYBLUE));
		MenuItem greske =  new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.greske"), fontAwesome.create(FontAwesome.Glyph.QRCODE).color(Color.ORANGE));
		MenuItem printeri =  new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.printeri"), fontAwesome.create(FontAwesome.Glyph.PRINT).color(Color.GREEN));
		MenuItem linija = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.linija"), fontAwesome.create(FontAwesome.Glyph.ADJUST).color(Color.BLUE));
		MenuItem parametri = new MenuItem(layout.vratiPrevod("izbornik.servis.podesavanja.parametri"),  fontAwesome.create(FontAwesome.Glyph.LANGUAGE).color(Color.GREEN));
		podesavanja.getItems().addAll(meni, baza, tip, modelTipova, specijali, obaveznaKonta, greske, printeri, linija, parametri);
		
		MenuItem korisnici = new MenuItem(layout.vratiPrevod("izbornik.servis.korisnici"), fontAwesome.create(FontAwesome.Glyph.USERS).color(Color.RED));
		korisnici.setOnAction(e -> {
			osnovniLayout.dodajTab(korisnici.getText(), new DPretplatnikPanel(osnovniLayout));
			});
		
		MenuItem kalkulator = new MenuItem(layout.vratiPrevod("izbornik.servis.kalkulator"), fontAwesome.create(FontAwesome.Glyph.CALCULATOR).color(Color.DARKVIOLET));
		kalkulator.setOnAction(e -> {
			//osnovniLayout.dodajTab(naciniIsporuke.getText(), new NaciniIsporukePanel(osnovniLayout));
			});
		
		MenuItem naciniPlacanja = new MenuItem(layout.vratiPrevod("izbornik.servis.naciniplacanja"), fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.DODGERBLUE));
		naciniPlacanja.setOnAction(e -> {
			//osnovniLayout.dodajTab(naciniPlacanja.getText(), new TipoviPlacanjaPanel(osnovniLayout));
			});
		
		getItems().addAll(datokete, podesavanja, korisnici, kalkulator, naciniPlacanja);
		}
	
	}
