package rs.atekom.infosystem.desk.a;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class OsnovniPregled extends HBox{
	
	public GridPane grid;
	
	public OsnovniPregled() {
		getStyleClass().add("pregled-sa-pozadinom");
		setSpacing(20);
		}
	
	public void napraviGrid() {
		grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		}
	
	}
