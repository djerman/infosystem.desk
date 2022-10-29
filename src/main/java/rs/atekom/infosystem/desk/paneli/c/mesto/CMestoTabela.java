package rs.atekom.infosystem.desk.paneli.c.mesto;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class CMestoTabela extends OpstaTabela<CMesto>{

	private TableColumn<CMesto, String> drzava, opstina, naziv, sr, en, de, postanskiBroj, pozivniBroj;
	
	public CMestoTabela(ResourceBundle resource) {
		super(resource);
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		drzava = new TableColumn<CMesto, String>(resource.getString("lbl.drzava"));
		drzava.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CMesto,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CMesto, String> param) {
				CMesto mesto = param.getValue();
				SimpleStringProperty drzava = new SimpleStringProperty("");
				if(mesto.getDrzava() != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						drzava = new SimpleStringProperty(mesto.getDrzava().getSr());
						break;
					case "eng_us":
						drzava = new SimpleStringProperty(mesto.getDrzava().getEn());
						break;
					case "de_de":
						drzava = new SimpleStringProperty(mesto.getDrzava().getDe());
						break;
					default:
						drzava = new SimpleStringProperty(mesto.getDrzava().getNaziv());
						}
					}
				return drzava;
				}
			});
		opstina = new TableColumn<CMesto, String>(resource.getString("lbl.opstina"));
		opstina.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CMesto,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CMesto, String> param) {
				CMesto mesto = param.getValue();
				SimpleStringProperty opstina = new SimpleStringProperty("");
				if(mesto.getOpstina() != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						opstina = new SimpleStringProperty(mesto.getOpstina().getSr());
						break;
					case "eng_us":
						opstina = new SimpleStringProperty(mesto.getOpstina().getEn());
						break;
					default:
						opstina = new SimpleStringProperty(mesto.getOpstina().getNaziv());
						}
					}
				return opstina;
				}
			});
		naziv = new TableColumn<CMesto, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<CMesto, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<CMesto, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<CMesto, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		postanskiBroj = new TableColumn<CMesto, String>(resource.getString("lbl.postanskibroj"));
		postanskiBroj.setCellValueFactory(new PropertyValueFactory<>("postanskiBroj"));
		pozivniBroj = new TableColumn<CMesto, String>(resource.getString("lbl.pozivnibroj"));
		pozivniBroj.setCellValueFactory(new PropertyValueFactory<>("pozivniBroj"));
		
		getColumns().addAll(drzava, opstina, postanskiBroj, naziv, sr, en, de, pozivniBroj);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
