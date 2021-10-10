package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.FontAwesome;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class NabavkaIzbornik extends OpstiIzbornik{

	public NabavkaIzbornik(OsnovniLayout layout) {
		super(layout);
		setText("_NABAVKA");
		setGraphic(fontAwesome.create(FontAwesome.Glyph.TRUCK).color(Color.NAVY));
		MenuItem dobavljaci = new MenuItem("DOBAVLJAČI", fontAwesome.create(FontAwesome.Glyph.BELL_SLASH).color(Color.DARKSLATEBLUE));
		dobavljaci.setAccelerator(new KeyCodeCombination(KeyCode.F5, KeyCombination.CONTROL_ANY));
		dobavljaci.setOnAction(e -> {
			//osnovniLayout.dodajTab(dobavljaci.getText(), new PartneriPanel(osnovniLayout, false, false));
			});
		MenuItem orderi =  new MenuItem("ORDERI", fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.FIREBRICK));
		
		getItems().addAll(dobavljaci, orderi);
	}

}
