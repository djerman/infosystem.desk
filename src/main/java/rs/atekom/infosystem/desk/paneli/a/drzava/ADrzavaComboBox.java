package rs.atekom.infosystem.desk.paneli.a.drzava;

import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import javafx.util.StringConverter;

public class ADrzavaComboBox extends SearchableComboBox<ADrzava>{

	public ADrzavaComboBox(ResourceBundle resource) {
		setConverter(new StringConverter<ADrzava>() {
			@Override
			public String toString(ADrzava object) {
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
			public ADrzava fromString(String string) {
				// TODO Auto-generated method stub
				return null;
				}
			});
		}
	}
