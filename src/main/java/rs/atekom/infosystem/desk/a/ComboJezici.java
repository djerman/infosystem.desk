package rs.atekom.infosystem.desk.a;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class ComboJezici extends ComboBox<Jezik>{

	public ComboJezici(ResourceBundle resource) {
		getItems().addAll(FXCollections.observableArrayList(popuniJezike(resource)));
	}
	
	private List<Jezik> popuniJezike(ResourceBundle resource){
		List<Jezik> lista = new ArrayList<>();
		lista.add(new Jezik("", ""));
		lista.add(new Jezik(resource.getString("lbl.sr"), "srb_rs"));
		lista.add(new Jezik(resource.getString("lbl.en"), "eng_us"));
		lista.add(new Jezik(resource.getString("lbl.de"), "de_de"));
		return lista;
	}
}
