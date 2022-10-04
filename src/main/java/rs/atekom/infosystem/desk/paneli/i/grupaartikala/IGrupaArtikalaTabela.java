package rs.atekom.infosystem.desk.paneli.i.grupaartikala;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.i.grupaartikala.IGrupaArtikala;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class IGrupaArtikalaTabela extends OpstaTabela<IGrupaArtikala>{

	private TableColumn<IGrupaArtikala, String> sifra, naziv, opis, prihod, rashod;
	
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
		
		prihod = new TableColumn<IGrupaArtikala, String>(resource.getString("lbl.kontoprihoda"));
		prihod.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<IGrupaArtikala,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<IGrupaArtikala, String> param) {
				IGrupaArtikala grupa = param.getValue();
				EKonto object = grupa.getPrihod();
				if(object != null) {
					SimpleStringProperty sifra = new SimpleStringProperty( object.getSifra() + "-");
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						return sifra.concat(object.getSr());
					case "eng_us":
						return sifra.concat(object.getEn());
					case "de_de":
						return sifra.concat(object.getDe());
					default:
						return sifra.concat(object.getNaziv());
						}
					}else {
						return null;
						}
			}
		});
		rashod = new TableColumn<IGrupaArtikala, String>(resource.getString("lbl.kontorashoda"));
		rashod.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<IGrupaArtikala,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<IGrupaArtikala, String> param) {
				IGrupaArtikala grupa = param.getValue();
				EKonto object = grupa.getRashod();
				if(object != null) {
					SimpleStringProperty sifra = new SimpleStringProperty( object.getSifra() + "-");
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						return sifra.concat(object.getSr());
					case "eng_us":
						return sifra.concat(object.getEn());
					case "de_de":
						return sifra.concat(object.getDe());
					default:
						return sifra.concat(object.getNaziv());
						}
					}else {
						return null;
						}
			}
		});
		
		getColumns().addAll(sifra, naziv, opis, prihod, rashod);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
