package rs.atekom.infosystem.desk.paneli.b.opstina;

import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import javafx.util.StringConverter;
import rs.atekom.infosystem.baza.b.BOpstina;

public class BOpstinaComboBox extends SearchableComboBox<BOpstina>{

	public BOpstinaComboBox(ResourceBundle resource) {
		setConverter(new StringConverter<BOpstina>() {
			@Override
			public String toString(BOpstina object) {
				if(object != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						return object.getPostanskiBroj() + " " + object.getSr();
					case "eng_us":
						return object.getPostanskiBroj() + " " + object.getEn();
					case "de_de":
						return object.getPostanskiBroj() + " " + object.getDe();
					default:
						return object.getNaziv();
						}
					}else {
						return null;
						}
				}
			@Override
			public BOpstina fromString(String string) {
				// TODO Auto-generated method stub
				return null;
				}
			});
		}
	
	}
