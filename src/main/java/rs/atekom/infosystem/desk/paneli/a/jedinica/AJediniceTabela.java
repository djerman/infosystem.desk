package rs.atekom.infosystem.desk.paneli.a.jedinica;

import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.atekom.infosystem.baza.a.jedinicamere.AJedinicaMere;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class AJediniceTabela extends OpstaTabela<AJedinicaMere>{

	private TableColumn<AJedinicaMere, String> naziv, nazivEn, nazivSr, nazivDe, oznaka, sr, en, de;
	
	public AJediniceTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}

	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		naziv = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		nazivSr = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.nazivsr"));
		nazivSr.setCellValueFactory(new PropertyValueFactory<>("nazivSr"));
		nazivEn = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.naziven"));
		nazivEn.setCellValueFactory(new PropertyValueFactory<>("nazivEn"));
		nazivDe = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.nazivde"));
		nazivDe.setCellValueFactory(new PropertyValueFactory<>("nazivDe"));
		oznaka = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.oznaka"));
		oznaka.setCellValueFactory(new PropertyValueFactory<>("oznaka"));
		sr = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<AJedinicaMere, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		
		getColumns().addAll(naziv, nazivEn, nazivSr, nazivDe, oznaka, sr, en, de);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
		
	}
