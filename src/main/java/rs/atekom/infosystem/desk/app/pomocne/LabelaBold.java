package rs.atekom.infosystem.desk.app.pomocne;

import javafx.scene.control.Label;

public class LabelaBold extends Label{
	
	public LabelaBold(String naziv) {
		super(naziv);
		setStyle("-fx-font-weight: bold;");
	}
}
