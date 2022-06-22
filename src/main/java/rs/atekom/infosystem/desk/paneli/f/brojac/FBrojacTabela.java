package rs.atekom.infosystem.desk.paneli.f.brojac;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.f.brojac.FBrojac;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class FBrojacTabela extends OpstaTabela<FBrojac>{

	private TableColumn<FBrojac, String> tip, naziv, sr, en, de, opis, opissr, opisen, opisde;
	
	public FBrojacTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
	}
	
	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		tip = new TableColumn<FBrojac, String>(resource.getString("lbl.tip"));
		tip.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FBrojac,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<FBrojac, String> param) {
				FBrojac brojac = param.getValue();
				SimpleStringProperty tip = new SimpleStringProperty("");
				if(brojac.getTip() != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						tip = new SimpleStringProperty(brojac.getTip().getSr());
						break;
					case "eng_us":
						tip = new SimpleStringProperty(brojac.getTip().getEn());
						break;
					case "de_de":
						tip = new SimpleStringProperty(brojac.getTip().getDe());
						break;
					default:
						tip = new SimpleStringProperty(brojac.getTip().getNaziv());
						}
					}
				return tip;
			}
		});
		naziv = new TableColumn<FBrojac, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<FBrojac, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<FBrojac, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<FBrojac, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		opis = new TableColumn<FBrojac, String>(resource.getString("lbl.opis"));
		opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		opissr = new TableColumn<FBrojac, String>(resource.getString("lbl.opissr"));
		opissr.setCellValueFactory(new PropertyValueFactory<>("opissr"));
		opisen = new TableColumn<FBrojac, String>(resource.getString("lbl.opisen"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		opisde = new TableColumn<FBrojac, String>(resource.getString("lbl.opisde"));
		opisde.setCellValueFactory(new PropertyValueFactory<>("opisde"));
		
		getColumns().addAll(tip, naziv, sr, en, de, opis, opissr, opisen, opisde);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
