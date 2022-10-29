package rs.atekom.infosystem.desk.paneli.f.brojac;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.f.brojac.FBrojac;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class FBrojacTabela extends OpstaTabela<FBrojac>{

	private TableColumn<FBrojac, String> tip, prefiks, brojPolja, stanje, sufiks;
	private TableColumn<FBrojac, Boolean> reset;
	
	public FBrojacTabela(ResourceBundle resource) {
		super(resource);
	}
	
	@SuppressWarnings("unchecked")
	@Override
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
		brojPolja = new TableColumn<FBrojac, String>(resource.getString("lbl.brojpolja"));
		brojPolja.setCellValueFactory(new PropertyValueFactory<>("brojPolja"));
		prefiks = new TableColumn<FBrojac, String>(resource.getString("lbl.prefiks"));
		prefiks.setCellValueFactory(new PropertyValueFactory<>("prefiks"));
		stanje = new TableColumn<FBrojac, String>(resource.getString("lbl.sledecibroj"));
		stanje.setCellValueFactory(new PropertyValueFactory<>("stanje"));
		sufiks = new TableColumn<FBrojac, String>(resource.getString("lbl.sufiks"));
		sufiks.setCellValueFactory(new PropertyValueFactory<>("sufiks"));
		reset = new TableColumn<FBrojac, Boolean>(resource.getString("lbl.reset"));
		reset.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FBrojac,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<FBrojac, Boolean> param) {
				FBrojac brojac = param.getValue();
				return new SimpleBooleanProperty(brojac == null ? false : brojac.getReset());
			}
		});
		reset.setCellFactory(new Callback<TableColumn<FBrojac,Boolean>, TableCell<FBrojac,Boolean>>() {
			@Override
			public TableCell<FBrojac, Boolean> call(TableColumn<FBrojac, Boolean> param) {
				CheckBoxTableCell<FBrojac, Boolean> cell = new CheckBoxTableCell<FBrojac, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		
		getColumns().addAll(tip, brojPolja, prefiks, stanje, sufiks, reset);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
