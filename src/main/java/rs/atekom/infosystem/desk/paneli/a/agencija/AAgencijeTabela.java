package rs.atekom.infosystem.desk.paneli.a.agencija;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.a.agencija.AAgencija;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class AAgencijeTabela extends OpstaTabela<AAgencija>{

	private TableColumn<AAgencija, String> naziv, kontaktOsoba, adresa, telefon, email;
	private TableColumn<AAgencija, Boolean> aktivan;
	
	public AAgencijeTabela(ResourceBundle resource) {
		super(resource);
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		naziv = new TableColumn<AAgencija, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		kontaktOsoba = new TableColumn<AAgencija, String>(resource.getString("lbl.kontaktosoba"));
		kontaktOsoba.setCellValueFactory(new PropertyValueFactory<>("kontaktOsoba"));
		adresa = new TableColumn<AAgencija, String>(resource.getString("lbl.adresa"));
		adresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
		telefon = new TableColumn<AAgencija, String>(resource.getString("lbl.telefon"));
		telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		email = new TableColumn<AAgencija, String>(resource.getString("lbl.email"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		aktivan = new TableColumn<AAgencija, Boolean>(resource.getString("lbl.aktivan"));
		aktivan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AAgencija,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<AAgencija, Boolean> param) {
				return new SimpleBooleanProperty(param.getValue() == null ? false : param.getValue().getAktivan());
				}
			});
		aktivan.setCellFactory(new Callback<TableColumn<AAgencija,Boolean>, TableCell<AAgencija,Boolean>>() {
			@Override
			public TableCell<AAgencija, Boolean> call(TableColumn<AAgencija, Boolean> param) {
				CheckBoxTableCell<AAgencija, Boolean> cell = new CheckBoxTableCell<AAgencija, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		
		getColumns().addAll(naziv, kontaktOsoba, adresa, telefon, email, aktivan);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
