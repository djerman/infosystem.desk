package rs.atekom.infosystem.desk.b;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import rs.atekom.infosystem.baza.a.drzava.ADrzava;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.OsnovniPanel;


public class TipDokumentaPanel extends OsnovniPanel{

	@FXML
	public TableView<ADrzava> tabelaTipova;
	
	public TipDokumentaPanel(OsnovniLayout ol) {
		super(ol);
		setContent(vratiRoot());
		}
	
	}
