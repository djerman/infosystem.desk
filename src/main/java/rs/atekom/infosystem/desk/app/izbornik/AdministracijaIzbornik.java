package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.paneli.e.grupaprava.EGrupaPravaPanel;

public class AdministracijaIzbornik extends OpstiIzbornik{

	public AdministracijaIzbornik(OsnovniLayout osnovniLayout) {
		super(osnovniLayout);
		setText(osnovniLayout.vratiPrevod("izbornik.administracija"));
		setGraphic(fontAwesome.create(FontAwesome.Glyph.CODEPEN).color(Color.RED));
		
		MenuItem korisnici = new MenuItem(osnovniLayout.vratiPrevod("izbornik.administracija.korisnici"), fontAwesome.create(FontAwesome.Glyph.USERS).color(Color.BLUE));
		korisnici.setOnAction(e -> {
			
			});
		
		MenuItem grupaPrava = new MenuItem(osnovniLayout.vratiPrevod("izbornik.administracija.grupeprava"), fontAwesome.create(FontAwesome.Glyph.COGS).color(Color.RED));
		grupaPrava.setOnAction(e -> {
			osnovniLayout.dodajTab(grupaPrava.getText(), new EGrupaPravaPanel(osnovniLayout));
			});
		
		getItems().addAll(korisnici, grupaPrava);
	}

}
