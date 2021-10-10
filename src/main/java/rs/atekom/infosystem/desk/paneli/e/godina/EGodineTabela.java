package rs.atekom.infosystem.desk.paneli.e.godina;

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
import rs.atekom.infosystem.baza.e.godina.EGodina;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class EGodineTabela extends OpstaTabela<EGodina>{

	private TableColumn<EGodina, String> pretplatnik;
	private TableColumn<EGodina, Integer> godina;
	private TableColumn<EGodina, Boolean> aktivan;
	
	public EGodineTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		pretplatnik = new TableColumn<EGodina, String>(resource.getString("lbl.pretplatnik"));
		pretplatnik.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EGodina,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<EGodina, String> param) {
				return new SimpleStringProperty(param.getValue() == null ? "" : param.getValue().getPretplatnik() == null ? 
						"" : param.getValue().getPretplatnik().getNaziv());
				}
			});
		godina = new TableColumn<EGodina, Integer>(resource.getString("lbl.godina"));
		godina.setCellValueFactory(new PropertyValueFactory<>("godina"));
		aktivan = new TableColumn<EGodina, Boolean>(resource.getString("lbl.aktivan"));
		aktivan.setCellValueFactory(new  Callback<TableColumn.CellDataFeatures<EGodina,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<EGodina, Boolean> param) {
				return new SimpleBooleanProperty(param.getValue() == null ? false : param.getValue().getAktivan());
				}
			});
		aktivan.setCellFactory(new Callback<TableColumn<EGodina,Boolean>, TableCell<EGodina,Boolean>>() {
			@Override
			public TableCell<EGodina, Boolean> call(TableColumn<EGodina, Boolean> param) {
				CheckBoxTableCell<EGodina, Boolean> cell = new CheckBoxTableCell<EGodina, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		
		getColumns().addAll(pretplatnik, godina, aktivan);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
