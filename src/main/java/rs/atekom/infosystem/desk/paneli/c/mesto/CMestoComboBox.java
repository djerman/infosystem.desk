package rs.atekom.infosystem.desk.paneli.c.mesto;

import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import javafx.util.StringConverter;
import rs.atekom.infosystem.baza.c.CMesto;

public class CMestoComboBox extends SearchableComboBox<CMesto>{

	public CMestoComboBox(ResourceBundle resource) {
		setConverter(new StringConverter<CMesto>() {
			@Override
			public String toString(CMesto object) {
				if(object != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						return object.getPostanskiBroj() + " " + object.getSr();
					case "eng_us":
						return object.getPostanskiBroj() + " " + object.getEn();
					case "de_de":
						return object.getPostanskiBroj() + " " + object.getDe();
					default:
						return object.getPostanskiBroj() + " " + object.getNaziv();
						}
					}else {
						return null;
						}
				}
			
			@Override
			public CMesto fromString(String string) {
				// TODO Auto-generated method stub
				return null;
				}
			});
		}
	
	}
