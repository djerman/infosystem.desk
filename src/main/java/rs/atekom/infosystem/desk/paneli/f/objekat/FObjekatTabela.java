package rs.atekom.infosystem.desk.paneli.f.objekat;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.f.objekat.FObjekat;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class FObjekatTabela extends OpstaTabela<FObjekat>{
	
	private TableColumn<FObjekat, String> naziv, konto, dokument, opis;

	public FObjekatTabela(ResourceBundle resource) {
		super(resource);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		naziv = new TableColumn<FObjekat, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		konto = new TableColumn<FObjekat, String>(resource.getString("lbl.konto"));
		konto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FObjekat,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<FObjekat, String> param) {
				FObjekat objekat = param.getValue();
				SimpleStringProperty konto = new SimpleStringProperty(objekat == null ? "" : objekat.getKonto() == null ? "" : 
					objekat.getKonto().getSifra());
				return konto;
			}
		});
		dokument = new TableColumn<FObjekat, String>(resource.getString("lbl.dokument"));
		dokument.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FObjekat,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<FObjekat, String> param) {
				FObjekat objekat = param.getValue();
				SimpleStringProperty dokument = new SimpleStringProperty(objekat == null ? "" : objekat.getDokument() == null ? "" : 
					objekat.getDokument().getSifra());
				return dokument;
			}
		});
		opis = new TableColumn<FObjekat, String>(resource.getString("lbl.opis"));
		opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		
		getColumns().addAll(naziv, konto, dokument, opis);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
