package rs.atekom.infosystem.desk.paneli.da.kontogrupa;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.da.kontogrupa.DAKontoGrupa;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class DAKontoGrupaTabela extends OpstaTabela<DAKontoGrupa>{

	private TableColumn<DAKontoGrupa, String> klasa, sifra, naziv, sr, en, de;
	
	public DAKontoGrupaTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		klasa = new TableColumn<DAKontoGrupa, String>(resource.getString("lbl.kontoklasa"));
		klasa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DAKontoGrupa,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DAKontoGrupa, String> param) {
				DAKontoGrupa grupa = param.getValue();
				return new SimpleStringProperty(grupa == null ? "" : grupa.getKlasa() == null ? "" : grupa.getKlasa().getSifra());
				}
			});
		sifra = new TableColumn<DAKontoGrupa, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<DAKontoGrupa, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<DAKontoGrupa, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<DAKontoGrupa, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<DAKontoGrupa, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		getColumns().addAll(klasa, sifra, naziv, sr, en, de);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
	}
