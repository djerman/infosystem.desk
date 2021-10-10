package rs.atekom.infosystem.desk.a;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import rs.atekom.infosystem.desk.app.rest.ServisRest;

public class VBoxRoot extends VBox{

	public ServisRest restServis;
	
	public VBoxRoot() {
		setSpacing(2);
		setPadding(new Insets(2, 2, 2, 2));
		}
	
	}
