package rs.atekom.infosystem.desk.paneli.a.poreskatarifa;

import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import javafx.util.StringConverter;
import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifa;

public class APoreskaTarifaComboBox extends SearchableComboBox<APoreskaTarifa>{

	public APoreskaTarifaComboBox(ResourceBundle resource) {
		setConverter(new StringConverter<APoreskaTarifa>() {
			@Override
			public String toString(APoreskaTarifa object) {
				if(object != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						return object.getSr();
					case "eng_us":
						return object.getEn();
					case "de_de":
						return object.getDe();
					default:
						return object.getNaziv();
						}
					}else {
						return null;
						}
				}
			
			@Override
			public APoreskaTarifa fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
