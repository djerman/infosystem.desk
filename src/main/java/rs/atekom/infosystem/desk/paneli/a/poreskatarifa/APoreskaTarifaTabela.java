package rs.atekom.infosystem.desk.paneli.a.poreskatarifa;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.a.poreskatarifa.APoreskaTarifa;
import rs.atekom.infosystem.desk.app.pomocne.KolonaDatum;
import rs.atekom.infosystem.desk.app.pomocne.KolonaDecimalna;
import rs.atekom.infosystem.desk.app.pomocne.KolonaInteger;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;


public class APoreskaTarifaTabela extends OpstaTabela<APoreskaTarifa>{

	private KolonaInteger<APoreskaTarifa, Integer> sifra;
	private TableColumn<APoreskaTarifa, String> naziv;
	private KolonaDecimalna<APoreskaTarifa, BigDecimal> stopa;
	private KolonaDatum<APoreskaTarifa, Date> datumOd, datumDo;
	
	public APoreskaTarifaTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}

	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		sifra = new KolonaInteger<APoreskaTarifa, Integer>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<APoreskaTarifa, Integer>("sifra"));
		naziv = new TableColumn<APoreskaTarifa, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<APoreskaTarifa,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<APoreskaTarifa, String> param) {
				APoreskaTarifa tarifa = param.getValue();
				SimpleStringProperty naziv = new SimpleStringProperty("");
				switch(resource.getLocale().toString()) {
				case "srb_rs":
					naziv = new SimpleStringProperty(tarifa.getSr());
					break;
				case "eng_us":
					naziv = new SimpleStringProperty(tarifa.getEn());
					break;
				case "de_de":
					naziv = new SimpleStringProperty(tarifa.getDe());
					break;
				default:
					naziv = new SimpleStringProperty(tarifa.getNaziv());
					}
				return naziv;
				}
			});
		stopa = new KolonaDecimalna<APoreskaTarifa, BigDecimal>(resource.getString("lbl.stopa"), "stopa");
		datumOd = new KolonaDatum<APoreskaTarifa, Date>(resource.getString("lbl.od"), "datumOd");
		datumDo = new KolonaDatum<APoreskaTarifa, Date>(resource.getString("lbl.do"), "datumDo");
		
		getColumns().addAll(sifra, naziv, stopa, datumOd, datumDo);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
