package rs.atekom.infosystem.desk.paneli.e.konto;

import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;
import javafx.util.StringConverter;
import rs.atekom.infosystem.baza.e.konto.EKonto;

public class EKontoComboBox extends SearchableComboBox<EKonto>{

	public EKontoComboBox(ResourceBundle resource) {
		
		setConverter(new StringConverter<EKonto>() {
			@Override
			public String toString(EKonto object) {
				if(object != null) {
					String sifra = object.getSifra() + "-";
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						return sifra + object.getSr();
					case "eng_us":
						return sifra + object.getEn();
					case "de_de":
						return sifra + object.getDe();
					default:
						return sifra + object.getNaziv();
						}
					}else {
						return null;
						}
				}
			
			@Override
			public EKonto fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
