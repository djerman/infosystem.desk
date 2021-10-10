package rs.atekom.infosystem.desk.app.pomocne;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LabelaLevo extends Label{

	public LabelaLevo() {
		super();
		setAlignment(Pos.CENTER_LEFT);
		GridPane.setHalignment(this, HPos.LEFT);
		}
	
	public LabelaLevo(String naziv) {
		super(naziv);
		setAlignment(Pos.CENTER_LEFT);
		GridPane.setHalignment(this, HPos.LEFT);
		}
	
	}
