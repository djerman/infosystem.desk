package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class SistemIzbornik extends OpstiIzbornik{

	public SistemIzbornik(OsnovniLayout layout) {
		super(layout);
		setText("_SISTEM");
		setGraphic(fontAwesome.create(FontAwesome.Glyph.COGS).color(Color.BLUE));
		MenuItem brojaci = new MenuItem("BROJAČI", fontAwesome.create(FontAwesome.Glyph.SORT_NUMERIC_ASC).color(Color.DARKGREEN));
		brojaci.setOnAction(e -> {
			//osnovniLayout.dodajTab(brojaci.getText(), new BrojaciPanel(osnovniLayout));
			});
		MenuItem dokumenta = new MenuItem("DOKUMENTA", fontAwesome.create(FontAwesome.Glyph.SORT_NUMERIC_DESC).color(Color.DARKBLUE));
		dokumenta.setOnAction(e -> {
			//osnovniLayout.dodajTab(dokumenta.getText(), new DokumentaPanel(osnovniLayout));
			});
		MenuItem pretplatnici = new MenuItem("PRETPLATNICI",  fontAwesome.create(FontAwesome.Glyph.ANGELLIST).color(Color.DARKRED));
		pretplatnici.setOnAction(e -> {
			//osnovniLayout.dodajTab(pretplatnici.getText(), new PretplatniciPanel(osnovniLayout));
			});
		
		getItems().addAll(brojaci, dokumenta, pretplatnici);
		}
	
	}
