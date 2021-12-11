package rs.atekom.infosystem.desk.paneli.e.tipdokumenta;

import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.atekom.infosystem.baza.e.tipdokumenta.ETipDokumenta;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class ETipDokumentaTabela extends OpstaTabela<ETipDokumenta>{

	private TableColumn<ETipDokumenta, String> sifra, naziv, nalog, sr, en, de;
	private TableColumn<ETipDokumenta, Integer> dokument;
	
	public ETipDokumentaTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		sifra = new TableColumn<ETipDokumenta, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<ETipDokumenta, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		dokument = new TableColumn<ETipDokumenta, Integer>(resource.getString("lbl.dokument"));
		dokument.setCellValueFactory(new PropertyValueFactory<>("dokument"));
		nalog = new TableColumn<ETipDokumenta, String>(resource.getString("lbl.nalog"));
		nalog.setCellValueFactory(new PropertyValueFactory<>("nalog"));
		sr = new TableColumn<ETipDokumenta, String>(resource.getString("lbl.sr"));
		sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
		en = new TableColumn<ETipDokumenta, String>(resource.getString("lbl.en"));
		en.setCellValueFactory(new PropertyValueFactory<>("en"));
		de = new TableColumn<ETipDokumenta, String>(resource.getString("lbl.de"));
		de.setCellValueFactory(new PropertyValueFactory<>("de"));
		
		getColumns().addAll(sifra, naziv, dokument, nalog, sr, en, de);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
	}
