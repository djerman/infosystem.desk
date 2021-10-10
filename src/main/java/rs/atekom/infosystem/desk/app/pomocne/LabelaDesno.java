package rs.atekom.infosystem.desk.app.pomocne;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LabelaDesno extends Label{

	public LabelaDesno() {
		super();
		GridPane.setHalignment(this, HPos.RIGHT);
		}
	
	public LabelaDesno(String naziv) {
		super(naziv);
		GridPane.setHalignment(this, HPos.RIGHT);
		}
	
	}
