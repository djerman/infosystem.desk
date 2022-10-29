package rs.atekom.infosystem.desk.paneli.e.grupaprava;

import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.atekom.infosystem.baza.e.grupaprava.EGrupaPrava;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class EGrupaPravaTabela extends OpstaTabela<EGrupaPrava>{

	private TableColumn<EGrupaPrava, String> naziv, sr, en, de, opis, opisSr, opisEn, opisDe;
	
	public EGrupaPravaTabela(ResourceBundle resource) {
		super(resource);
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		naziv = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		sr = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		opis = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.opis"));
		opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		opisSr = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.opissr"));
		opisSr.setCellValueFactory(new PropertyValueFactory<>("opis_sr"));
		opisEn = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.opisen"));
		opisEn.setCellValueFactory(new PropertyValueFactory<>("opis_en"));
		opisDe = new TableColumn<EGrupaPrava, String>(resource.getString("lbl.opisde"));
		opisDe.setCellValueFactory(new PropertyValueFactory<>("opis_de"));
		
		getColumns().addAll(naziv, sr, en, de, opis, opisSr, opisEn, opisDe);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	}
	
	}
