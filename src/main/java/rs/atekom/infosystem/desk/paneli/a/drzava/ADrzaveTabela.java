package rs.atekom.infosystem.desk.paneli.a.drzava;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class ADrzaveTabela extends OpstaTabela<ADrzava>{

	private TableColumn<ADrzava, String>  naziv, sr, en, pozivniBroj, oznaka;
	private TableColumn<ADrzava, Boolean> podrazumevan;
	
	public ADrzaveTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		naziv = new TableColumn<ADrzava, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<ADrzava, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<ADrzava, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		pozivniBroj = new TableColumn<ADrzava, String>(resource.getString("lbl.pozivnibroj"));
		pozivniBroj.setCellValueFactory(new PropertyValueFactory<>("pozivniBroj"));
		oznaka = new TableColumn<ADrzava, String>(resource.getString("lbl.oznaka"));
		oznaka.setCellValueFactory(new PropertyValueFactory<>("oznaka"));
		podrazumevan = new TableColumn<ADrzava, Boolean>(resource.getString("lbl.podrazumevan"));
		podrazumevan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ADrzava,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<ADrzava, Boolean> param) {
				return new SimpleBooleanProperty(param.getValue() == null ? false : param.getValue().getPodrazumevan());
				}
			});
		podrazumevan.setCellFactory(new Callback<TableColumn<ADrzava,Boolean>, TableCell<ADrzava,Boolean>>() {
			@Override
			public TableCell<ADrzava, Boolean> call(TableColumn<ADrzava, Boolean> param) {
				CheckBoxTableCell<ADrzava, Boolean> cell = new CheckBoxTableCell<ADrzava, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		
		getColumns().addAll(naziv, sr, en, pozivniBroj, oznaka, podrazumevan);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
