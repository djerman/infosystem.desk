package rs.atekom.infosystem.desk.paneli.i.grupaartikala;

import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class IGrupaArtikalaTabela extends OpstaTabela<IGrupaArtikala>{

	private TableColumn<IGrupaArtikala, String> sifra, naziv, opis;
	
	public IGrupaArtikalaTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
	}

	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		sifra = new TableColumn<IGrupaArtikala, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<IGrupaArtikala, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		opis = new TableColumn<IGrupaArtikala, String>(resource.getString("lbl.opis"));
		opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		
		getColumns().addAll(sifra, naziv, opis);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
