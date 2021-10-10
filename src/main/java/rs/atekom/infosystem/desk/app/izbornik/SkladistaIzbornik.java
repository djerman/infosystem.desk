package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class SkladistaIzbornik extends OpstiIzbornik{

	public SkladistaIzbornik(OsnovniLayout layout) {
		super(layout);
		setText("_MAGACIN");
		setGraphic(fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.NAVY));
		MenuItem radniNalozi = new MenuItem("IZDAVANJE ROBE - MAGACIN", fontAwesome.create(FontAwesome.Glyph.HAND_ALT_UP).color(Color.TEAL));
		radniNalozi.setOnAction(e -> {
			//osnovniLayout.dodajTab(radniNalozi.getText(), new PrenosiPanel(osnovniLayout));
			});
		MenuItem prijemRobe = new MenuItem("PRIJEM ROBE", fontAwesome.create(FontAwesome.Glyph.HAND_ALT_DOWN).color(Color.SIENNA));
		MenuItem internePrenosnice = new MenuItem("INTERNE PRENOSNICE", fontAwesome.create(FontAwesome.Glyph.HAND_ALT_RIGHT).color(Color.MEDIUMPURPLE));
		MenuItem povracajKomisiona = new MenuItem("POVRAĆAJ KOMISIONA", fontAwesome.create(FontAwesome.Glyph.HAND_ALT_LEFT).color(Color.MEDIUMSPRINGGREEN));
		MenuItem popis = new MenuItem("POPIS", fontAwesome.create(FontAwesome.Glyph.PENCIL).color(Color.ORANGERED));
		MenuItem prikazPaleta = new MenuItem("PRIKAZ PALETA", fontAwesome.create(FontAwesome.Glyph.MAP_MARKER).color(Color.DARKCYAN));
		Menu mapaPaleta = new Menu("MAPA PALETA", fontAwesome.create(FontAwesome.Glyph.SITEMAP).color(Color.MAROON));
		MenuItem formiranjeMapePaleta = new MenuItem("FORMIRANJE MAPE PALETA", fontAwesome.create(FontAwesome.Glyph.MORTAR_BOARD).color(Color.MEDIUMBLUE));
		MenuItem prenosIzPrijemnog = new MenuItem("PRENOS IZ PRIJEMNOG MAGACINA", fontAwesome.create(FontAwesome.Glyph.CARET_SQUARE_ALT_RIGHT).color(Color.GREENYELLOW));
		MenuItem korekcijaZaliha = new MenuItem("KOREKCIJA ZALIHA", fontAwesome.create(FontAwesome.Glyph.CALCULATOR).color(Color.LIGHTBLUE));
		MenuItem knjigeOtpremnica = new MenuItem("KNJIGE OTPREMNICA", fontAwesome.create(FontAwesome.Glyph.BOOKMARK).color(Color.DARKRED));
		MenuItem knjigePrenosnica = new MenuItem("KNJIGE PRENOSNICA", fontAwesome.create(FontAwesome.Glyph.BOOKMARK_ALT).color(Color.DEEPPINK));
		
		getItems().addAll(radniNalozi, prijemRobe, internePrenosnice, povracajKomisiona, popis, prikazPaleta, mapaPaleta, formiranjeMapePaleta, 
				prenosIzPrijemnog, korekcijaZaliha, knjigeOtpremnica, knjigePrenosnica);
		}
	
	}
