package rs.atekom.infosystem.desk.paneli.a.jedinica;

import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import javafx.util.StringConverter;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;

public class AJedinicaMereComboBox extends SearchableComboBox<AJedinicaMere>{

	public AJedinicaMereComboBox(ResourceBundle resource) {
		setConverter(new StringConverter<AJedinicaMere>() {
			//srpski obrnut??
			@Override
			public String toString(AJedinicaMere object) {
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
			public AJedinicaMere fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
