package rs.atekom.infosystem.desk.paneli.b.opstina;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.b.BOpstina;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class BOpstineTabela extends OpstaTabela<BOpstina>{

	private TableColumn<BOpstina, String> drzava, naziv, sr, en, de, postanskiBroj, pozivniBroj, pozivNaBroj;
	
	public BOpstineTabela(ResourceBundle resource) {
		super(resource);
		}

	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		drzava = new TableColumn<BOpstina, String>(resource.getString("lbl.drzava"));
		drzava.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BOpstina,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BOpstina, String> param) {
				BOpstina opstina = param.getValue();
				SimpleStringProperty drzava = new SimpleStringProperty("");
				if(opstina.getDrzava() != null) {
					switch(resource.getLocale().toString()) {
					case "srb_rs":
						drzava = new SimpleStringProperty(opstina.getDrzava().getSr());
						break;
					case "eng_us":
						drzava = new SimpleStringProperty(opstina.getDrzava().getEn());
						break;
					case "de_de":
						drzava = new SimpleStringProperty(opstina.getDrzava().getDe());
						break;
					default:
						drzava = new SimpleStringProperty(opstina.getDrzava().getNaziv());
						}
					}
				return drzava;
				}
			});
		naziv = new TableColumn<BOpstina, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<BOpstina, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<BOpstina, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<BOpstina, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		
		postanskiBroj = new TableColumn<BOpstina, String>(resource.getString("lbl.postanskibroj"));
		postanskiBroj.setCellValueFactory(new PropertyValueFactory<>("postanskiBroj"));
		pozivniBroj = new TableColumn<BOpstina, String>(resource.getString("lbl.pozivnibroj"));
		pozivniBroj.setCellValueFactory(new PropertyValueFactory<>("pozivniBroj"));
		pozivNaBroj = new TableColumn<BOpstina, String>(resource.getString("lbl.pozivnabroj"));
		pozivNaBroj.setCellValueFactory(new PropertyValueFactory<>("pozivNaBroj"));
		
		getColumns().addAll(drzava, postanskiBroj, naziv, sr, en, de, pozivniBroj, pozivNaBroj);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
