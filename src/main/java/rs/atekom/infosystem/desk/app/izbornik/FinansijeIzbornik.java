package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class FinansijeIzbornik extends OpstiIzbornik{

	public FinansijeIzbornik(OsnovniLayout layout) {
		super(layout);
		setText(layout.vratiPrevod("izbornik.finansije"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.GOLD));
		MenuItem izvodiNalozi = new MenuItem(layout.vratiPrevod("izbornik.finansije.izvodinalozi"), fontAwesome.create(FontAwesome.Glyph.BANK).color(Color.RED));
		MenuItem blagajna = new MenuItem(layout.vratiPrevod("izbornik.finansije.blagajna"), fontAwesome.create(FontAwesome.Glyph.BITCOIN).color(Color.GOLD));
		MenuItem devizniIzvodi = new MenuItem(layout.vratiPrevod("izbornik.finansije.devizniizvod"), fontAwesome.create(FontAwesome.Glyph.DOLLAR).color(Color.RED));
		
		MenuItem troskoviNabavke = new MenuItem(layout.vratiPrevod("izbornik.finansije.troskovinabavke"), fontAwesome.create(FontAwesome.Glyph.BAR_CHART_O).color(Color.DEEPPINK));
		
		MenuItem ostalaKnjizenja = new MenuItem(layout.vratiPrevod("izbornik.finansije.ostalaknjizenja"), fontAwesome.create(FontAwesome.Glyph.MONEY).color(Color.DARKOLIVEGREEN));
		MenuItem pocetnoStanje = new MenuItem(layout.vratiPrevod("izbornik.finansije.pocetnostanje"), fontAwesome.create(FontAwesome.Glyph.LINE_CHART).color(Color.ORANGE));
		
		MenuItem preknjizavanje = new MenuItem(layout.vratiPrevod("izbornik.finansije.preknjizavanje"), fontAwesome.create(FontAwesome.Glyph.BELL_SLASH).color(Color.DARKSLATEBLUE));
		preknjizavanje.setAccelerator(new KeyCodeCombination(KeyCode.F5, KeyCombination.CONTROL_ANY));
		preknjizavanje.setOnAction(e -> {
			//osnovniLayout.dodajTab(dobavljaci.getText(), new PartneriPanel(osnovniLayout, false, false));
			});
		
		Menu izmenaKonta = new Menu(layout.vratiPrevod("izbornik.finansije.izmenakonta"), fontAwesome.create(FontAwesome.Glyph.STACK_EXCHANGE).color(Color.MEDIUMAQUAMARINE));
		
		getItems().addAll(izvodiNalozi, blagajna, devizniIzvodi, troskoviNabavke, ostalaKnjizenja, pocetnoStanje, preknjizavanje, izmenaKonta);
		}
	
	}
