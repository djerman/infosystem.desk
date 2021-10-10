package rs.atekom.infosystem.desk.a;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public class ObavestenjeBrisanje extends Alert{

	public ObavestenjeBrisanje(AlertType alertType, String header, String context, ResourceBundle resource) {
		super(alertType);
		ButtonType da = new ButtonType(resource.getString("obavestenja.da"), ButtonData.YES);
		ButtonType otkazi = new ButtonType(resource.getString("upozorenje.otkazi"), ButtonData.CANCEL_CLOSE);
		setTitle(resource.getString("upozorenje.title"));
		setHeaderText(header);
		setContentText(context);
		getButtonTypes().clear();
		getButtonTypes().addAll(da, otkazi);
		}
	
	}
