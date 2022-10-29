package rs.atekom.infosystem.desk.paneli.e.konto;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class EKontoTabela extends OpstaTabela<EKonto>{
	
	private TableColumn<EKonto, String> klasa, grupa, podgrupa, sifra, naziv, sr, en, de;
	
	public EKontoTabela(ResourceBundle resource) {
		super(resource);
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		klasa = new TableColumn<EKonto, String>(resource.getString("lbl.kontoklasa"));
		klasa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EKonto,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<EKonto, String> param) {
				EKonto konto = param.getValue();
				return new SimpleStringProperty(konto == null ? "" : konto.getPodgrupa() == null ? "" : konto.getPodgrupa().getGrupa() == null ? "" : 
					konto.getPodgrupa().getGrupa().getKlasa() == null ? "" : konto.getPodgrupa().getGrupa().getKlasa().getSifra());
				}
			});
		
		grupa = new TableColumn<EKonto, String>(resource.getString("lbl.kontogrupa"));
		grupa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EKonto,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<EKonto, String> param) {
				EKonto konto = param.getValue();
				return new SimpleStringProperty(konto == null ? "" : konto.getPodgrupa() == null ? "" : konto.getPodgrupa().getGrupa() == null ? "" : konto.getPodgrupa().getGrupa().getSifra());
				}
			});
		
		podgrupa = new TableColumn<EKonto, String>(resource.getString("lbl.podgrupa"));
		podgrupa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EKonto,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<EKonto, String> param) {
				EKonto konto = param.getValue();
				return new SimpleStringProperty(konto == null ? "" : konto.getPodgrupa() == null ? "" : konto.getPodgrupa().getSifra());
				}
			});
		
		sifra = new TableColumn<EKonto, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<EKonto, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<EKonto, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<EKonto, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<EKonto, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		
		getColumns().addAll(klasa, grupa, podgrupa, sifra, naziv, sr, en, de);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
	}
