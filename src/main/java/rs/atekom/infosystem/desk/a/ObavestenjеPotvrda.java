package rs.atekom.infosystem.desk.a;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public class ObavestenjеPotvrda extends Alert{

	public ObavestenjеPotvrda(AlertType alertType, String title, String header, String conext, ResourceBundle resource) {
		super(alertType);
		ButtonType da = new ButtonType(resource.getString("btn.uredu"), ButtonData.YES);
		setTitle(title);
		setHeaderText(header);
		setContentText(conext);
		getButtonTypes().clear();
		getButtonTypes().add(da);
		}
	
	}
