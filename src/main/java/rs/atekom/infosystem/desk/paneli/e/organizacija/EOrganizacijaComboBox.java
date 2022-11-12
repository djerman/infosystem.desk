package rs.atekom.infosystem.desk.paneli.e.organizacija;


import org.controlsfx.control.SearchableComboBox;
import javafx.util.StringConverter;
import rs.atekom.infosystem.baza.e.organizacija.EOrganizacija;

public class EOrganizacijaComboBox extends SearchableComboBox<EOrganizacija> {

	public EOrganizacijaComboBox() {
		setConverter(new StringConverter<EOrganizacija>() {
			
			@Override
			public String toString(EOrganizacija object) {
				if(object != null)
					return object.getNaziv();
				return null;
			}
			
			@Override
			public EOrganizacija fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
