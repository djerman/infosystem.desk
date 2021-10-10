package rs.atekom.infosystem.desk.app.izbornik;

import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javafx.scene.control.Menu;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class OpstiIzbornik extends Menu{

	public GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
	public OsnovniLayout osnovniLayout;
	
	public OpstiIzbornik(OsnovniLayout osnovniLayout) {
		this.osnovniLayout = osnovniLayout;
		setMnemonicParsing(true);
		}
	
	}
