package rs.atekom.infosystem.desk.app.pomocne;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class DugmeOdredjeneSirine extends Button{

	public DugmeOdredjeneSirine(String text, Node graphic, Double max, Double min) {
		super(text, graphic);
		setMaxWidth(max);
		setMinWidth(min);
	}
	
	public DugmeOdredjeneSirine(String text, Node graphic) {
		super(text, graphic);
		setMaxWidth(Dimenzije.MAX_SIRINA);
		setMinWidth(Dimenzije.MIN_SIRINA);
	}
}
