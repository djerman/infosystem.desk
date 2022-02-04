package rs.atekom.infosystem.desk.app.pomocne;

import javafx.scene.control.Label;

public class LabelaObaveznaBold extends Label{

	public LabelaObaveznaBold(String naziv) {
		super(naziv);
		setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
	}
}
