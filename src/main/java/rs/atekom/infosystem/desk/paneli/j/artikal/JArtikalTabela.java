package rs.atekom.infosystem.desk.paneli.j.artikal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.j.JArtikal;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class JArtikalTabela extends OpstaTabela<JArtikal>{

	private TableColumn<JArtikal, String> sifra, grupa, naziv, barcode, jm, poreskaTarifa, en, de, proizvodnja, opis, opisEn, opisDe;
	private TableColumn<JArtikal, Date> rokTrajanja;
	private TableColumn<JArtikal, BigDecimal> infCena, rastur;
	
	public JArtikalTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
	}

	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		sifra = new TableColumn<JArtikal, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<JArtikal, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		poreskaTarifa = new TableColumn<JArtikal, String>(resource.getString("lbl.poreskatarifa"));
		poreskaTarifa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JArtikal,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<JArtikal, String> param) {
				JArtikal artikal = param.getValue();
				SimpleStringProperty tarifa = new SimpleStringProperty(artikal == null ? "" : artikal.getPoreskaTarifa() == null ? "" : 
					 "" + artikal.getPoreskaTarifa().getSifra());
				return tarifa;
			}
		});
		jm = new TableColumn<JArtikal, String>(resource.getString("lbl.jm"));
		jm.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JArtikal,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<JArtikal, String> param) {
				JArtikal artikal = param.getValue();
				SimpleStringProperty tarifa = new SimpleStringProperty(artikal == null ? "" : artikal.getJm() == null ? "" : artikal.getJm().getOznaka());
				return tarifa;
			}
		});
		infCena = new TableColumn<JArtikal, BigDecimal>(resource.getString("lbl.infcena"));
		infCena.setCellValueFactory(new PropertyValueFactory<JArtikal, BigDecimal>("infCena"));
		barcode = new TableColumn<JArtikal, String>(resource.getString("lbl.barcode"));
		barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
		grupa = new TableColumn<JArtikal, String>(resource.getString("lbl.grupa"));
		grupa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JArtikal,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<JArtikal, String> param) {
				JArtikal artikal = param.getValue();
				SimpleStringProperty gr = new SimpleStringProperty(artikal == null ? "" : artikal.getGrupa() == null ? "" : artikal.getGrupa().getNaziv());
				return gr;
			}
		});
		rokTrajanja = new TableColumn<JArtikal, Date>(resource.getString("lbl.roktrajanja"));
		rokTrajanja.setCellValueFactory(new PropertyValueFactory<JArtikal, Date>("rokTrajanja"));
		rokTrajanja.setCellFactory(col -> {
			TableCell<JArtikal, Date> cell = new TableCell<JArtikal, Date>(){
				@Override
				 protected void updateItem(Date item, boolean empty) {
					 super.updateItem(item, empty);
					 if(empty) {
						 setText(null);
					 }else if(item != null){
						 this.setText(format.format(item));
					 }else {
						 setText(null);
					 }
				 }
			};
			return cell;
		});
		rastur = new TableColumn<JArtikal, BigDecimal>(resource.getString("lbl.rastur"));
		rastur.setCellValueFactory(new PropertyValueFactory<JArtikal, BigDecimal>("rastur"));
		en = new TableColumn<JArtikal, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<JArtikal, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		//proizvodnja
		opis = new TableColumn<JArtikal, String>(resource.getString("lbl.opis"));
		opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		opisEn = new TableColumn<JArtikal, String>(resource.getString("lbl.opisen"));
		opisEn.setCellValueFactory(new PropertyValueFactory<>("opis_en"));
		opisDe = new TableColumn<JArtikal, String>(resource.getString("lbl.opisde"));
		opisDe.setCellValueFactory(new PropertyValueFactory<>("opis_de"));
		
		getColumns().addAll(sifra, naziv, poreskaTarifa, jm, infCena, barcode, grupa, rokTrajanja, rastur, en, de/*, proizvodnja*/, opis, opisEn, opisDe);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
