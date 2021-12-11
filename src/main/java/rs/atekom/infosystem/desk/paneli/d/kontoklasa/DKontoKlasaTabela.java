package rs.atekom.infosystem.desk.paneli.d.kontoklasa;

import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.atekom.infosystem.baza.d.kontoklasa.DKontoKlasa;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class DKontoKlasaTabela extends OpstaTabela<DKontoKlasa>{

	private TableColumn<DKontoKlasa, String> sifra, naziv, sr, en, de;
	
	public DKontoKlasaTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		sifra = new TableColumn<DKontoKlasa, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<DKontoKlasa, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<DKontoKlasa, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<DKontoKlasa, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<DKontoKlasa, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		getColumns().addAll(sifra, naziv, sr, en, de);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
	}
