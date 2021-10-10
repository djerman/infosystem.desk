package rs.atekom.infosystem.desk.paneli.h.bankovniracun;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.h.HBankovniRacun;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class HBankovniRacunTabela extends OpstaTabela<HBankovniRacun>{

	private TableColumn<HBankovniRacun, String> banka, brojRacuna;
	
	public HBankovniRacunTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}

	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		banka = new TableColumn<HBankovniRacun, String>(resource.getString("lbl.banka"));
		banka.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HBankovniRacun,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<HBankovniRacun, String> param) {
				return new SimpleStringProperty(param.getValue() == null ? "" : param.getValue().getBanka() == null ? 
						"" : param.getValue().getBanka().getNaziv());
				}
			});
		brojRacuna = new TableColumn<HBankovniRacun, String>(resource.getString("lbl.brojracuna"));
		brojRacuna.setCellValueFactory(new PropertyValueFactory<>("brojRacuna"));
        banka.prefWidthProperty().bind(this.widthProperty().multiply(0.3));
        brojRacuna.prefWidthProperty().bind(this.widthProperty().multiply(0.7));
		getColumns().addAll(banka, brojRacuna);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
	}
