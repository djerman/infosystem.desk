package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.paneli.a.agencija.AAgencijePanel;
import rs.atekom.infosystem.desk.paneli.a.drzava.ADrzavePanel;
import rs.atekom.infosystem.desk.paneli.a.jedinica.AJedinicePanel;
import rs.atekom.infosystem.desk.paneli.b.opstina.BOpstinePanel;
import rs.atekom.infosystem.desk.paneli.c.mesto.CMestoPanel;
import rs.atekom.infosystem.desk.paneli.d.kontoklasa.DKontoKlasaPanel;
import rs.atekom.infosystem.desk.paneli.da.kontogrupa.DAKontoGrupaPanel;
import rs.atekom.infosystem.desk.paneli.db.kontoracun.DBKontoRacunPanel;
import rs.atekom.infosystem.desk.paneli.e.godina.EGodinePanel;
import rs.atekom.infosystem.desk.paneli.e.konto.EKontoPanel;

public class OpsteIzbornik extends OpstiIzbornik{

	public OpsteIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.opste"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.COGS).color(Color.RED));
		MenuItem drzave = new MenuItem(layout.vratiPrevod("izbornik.opste.drzave"), fontAwesome.create(FontAwesome.Glyph.BANK).color(Color.GOLD));
		drzave.setOnAction(e -> {
			osnovniLayout.dodajTab(drzave.getText(), new ADrzavePanel(layout));
			});
		MenuItem opstine = new MenuItem(layout.vratiPrevod("izbornik.opste.opstine"), fontAwesome.create(FontAwesome.Glyph.BUILDING).color(Color.BROWN)); 
		opstine.setOnAction(e -> {
			osnovniLayout.dodajTab(opstine.getText(), new BOpstinePanel(layout));
			});
		MenuItem mesta = new MenuItem(layout.vratiPrevod("izbornik.opste.mesta"), fontAwesome.create(FontAwesome.Glyph.BUILDING_ALT).color(Color.BURLYWOOD)); 
		mesta.setOnAction(e ->{
			osnovniLayout.dodajTab(mesta.getText(), new CMestoPanel(layout));
			});
		MenuItem agencije = new MenuItem(layout.vratiPrevod("izbornik.opste.agencije"), fontAwesome.create(FontAwesome.Glyph.ARCHIVE).color(Color.RED));
		agencije.setOnAction(e -> {
			osnovniLayout.dodajTab(agencije.getText(), new AAgencijePanel(layout));
			});
		MenuItem godine = new MenuItem(layout.vratiPrevod("izbornik.opste.godine"), fontAwesome.create(FontAwesome.Glyph.CALENDAR).color(Color.BLUE));
		godine.setOnAction(e -> {
			osnovniLayout.dodajTab(godine.getText(), new EGodinePanel(layout));
			});
		MenuItem jediniceMere = new MenuItem(layout.vratiPrevod("izbornik.opste.jedinicemere"), fontAwesome.create(FontAwesome.Glyph.SLIDERS).color(Color.GREEN));
		jediniceMere.setOnAction(e -> {
			osnovniLayout.dodajTab(jediniceMere.getText(), new AJedinicePanel(layout));
			});
		
		MenuItem konta = new MenuItem(layout.vratiPrevod("izbornik.opste.konta"), fontAwesome.create(FontAwesome.Glyph.ANCHOR).color(Color.ORANGERED));
		konta.setOnAction(e -> {
			osnovniLayout.dodajTab(konta.getText(), new EKontoPanel(layout));
			});
		
		MenuItem podgrupeKonta = new MenuItem(layout.vratiPrevod("izbornik.opste.podgrupekonta"), fontAwesome.create(FontAwesome.Glyph.GROUP).color(Color.BISQUE));
		podgrupeKonta.setOnAction(e -> {
			osnovniLayout.dodajTab(podgrupeKonta.getText(), new DBKontoRacunPanel(layout));
			});
		
		MenuItem grupeKonta = new MenuItem(layout.vratiPrevod("izbornik.opste.grupekonta"), fontAwesome.create(FontAwesome.Glyph.GROUP).color(Color.DARKGOLDENROD));
		grupeKonta.setOnAction(e -> {
			osnovniLayout.dodajTab(grupeKonta.getText(), new DAKontoGrupaPanel(layout));
			});
		
		MenuItem klaseKonta = new MenuItem(layout.vratiPrevod("izbornik.opste.klasekonta"), fontAwesome.create(FontAwesome.Glyph.GROUP).color(Color.BROWN));
		klaseKonta.setOnAction(e -> {
			osnovniLayout.dodajTab(klaseKonta.getText(), new DKontoKlasaPanel(layout));
			});
		
		getItems().addAll(agencije, godine, mesta, opstine, drzave, jediniceMere, konta, podgrupeKonta, grupeKonta, klaseKonta);
		}

	}
