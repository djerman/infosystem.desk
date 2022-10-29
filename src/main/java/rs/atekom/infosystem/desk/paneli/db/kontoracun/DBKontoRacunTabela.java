package rs.atekom.infosystem.desk.paneli.db.kontoracun;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.db.kontoracun.DBKontoRacun;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class DBKontoRacunTabela extends OpstaTabela<DBKontoRacun>{

	private TableColumn<DBKontoRacun, String> klasa, grupa, sifra, naziv, sr, en, de;
	
	public DBKontoRacunTabela(ResourceBundle resource) {
		super(resource);
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		klasa = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.kontoklasa"));
		klasa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DBKontoRacun,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DBKontoRacun, String> param) {
				DBKontoRacun racun = param.getValue();
				return new SimpleStringProperty(racun == null ? "" : racun.getGrupa() == null ? "" : racun.getGrupa().getKlasa() == null ? "" : 
					racun.getGrupa().getKlasa().getSifra());
				}
			});
		grupa = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.kontogrupa"));
		grupa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DBKontoRacun,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DBKontoRacun, String> param) {
				DBKontoRacun racun = param.getValue();
				return new SimpleStringProperty(racun == null ? "" : racun.getGrupa() == null ? "" : racun.getGrupa().getSifra());
				}
			});
		sifra = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<DBKontoRacun, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		getColumns().addAll(klasa, grupa, sifra, naziv, sr, en, de);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	}
	
	}
