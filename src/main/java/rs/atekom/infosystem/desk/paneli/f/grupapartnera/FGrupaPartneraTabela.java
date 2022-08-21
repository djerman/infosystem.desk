package rs.atekom.infosystem.desk.paneli.f.grupapartnera;

import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.atekom.infosystem.baza.f.grupapartnera.FGrupaPartnera;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class FGrupaPartneraTabela extends OpstaTabela<FGrupaPartnera>{

	private TableColumn<FGrupaPartnera, String> sifra, naziv, opis;
	
	public FGrupaPartneraTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		sifra = new TableColumn<FGrupaPartnera, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		naziv = new TableColumn<FGrupaPartnera, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		opis = new TableColumn<FGrupaPartnera, String>(resource.getString("lbl.opis"));
		opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		
		getColumns().addAll(sifra, naziv, opis);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
