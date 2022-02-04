package rs.atekom.infosystem.desk.paneli.e.godina;

import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import org.springframework.http.ResponseEntity;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnikOdgovor;
import rs.atekom.infosystem.baza.e.godina.EGodina;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.Odgovori;
import rs.atekom.infosystem.desk.app.pomocne.TekstCelobrojni;

public class EGodinePregled extends GridPane{

	private LabelaObaveznaBold lblPretplatnik, lblGodina;
	private LabelaBold lblAktivan;
	private ComboBox<DPretplatnik> cbPretplatnici;
	private TekstCelobrojni txtGodina;
	private CheckBox cbAktivan;
	private EGodina godina;
	private EGodinePanel panel;
	
	public EGodinePregled(EGodinePanel panel, ResourceBundle resource) {
		this.panel = panel;
		setHgap(5);
		setVgap(5);
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		lblPretplatnik = new LabelaObaveznaBold(resource.getString("lbl.pretplatnik"));
		lblGodina = new LabelaObaveznaBold(resource.getString("lbl.godina"));
		lblAktivan = new LabelaBold(resource.getString("lbl.aktivan"));
		
		cbPretplatnici = new SearchableComboBox<DPretplatnik>();
		try {
			ResponseEntity<DPretplatnikOdgovor> odgovor = panel.vratiRestPretplatnik().pretraga(null, panel.vratiOsnovniLayout().vratiKorisnika().getAgencija());
			if(odgovor != null) {
				switch (odgovor.getStatusCodeValue()) {
				case Odgovori.IZVRSEN:
					cbPretplatnici.setItems(FXCollections.observableArrayList(odgovor.getBody() == null ? null : odgovor.getBody().getLista()));
					break;
				default:
					panel.pokaziNijeUspesno(panel.vratiOsnovniLayout().vratiPrevod("obavestenja.nijeuspesno"), 
							panel.vratiOsnovniLayout().vratiPrevod("obavestenja.content"));
					}
				}else {
					panel.vratiNemaOdgovoraServera();
					}
			}catch (Exception e) {
				// TODO: handle exception
				}
		txtGodina = new TekstCelobrojni();
		cbAktivan = new CheckBox();
		}
	
	private void popakujElemente() {
		Double minSirina1 = 100.0;
		Double minSirina2 = 120.0;
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina1);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHalignment(HPos.RIGHT);
		col3.setMinWidth(minSirina2);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setHalignment(HPos.RIGHT);
		col5.setMinWidth(minSirina2);
		ColumnConstraints col6 = new ColumnConstraints();
		//col6.setHgrow(Priority.ALWAYS);
		
		getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
		
		addColumn(0, lblPretplatnik); addColumn(1, cbPretplatnici); addColumn(2, lblGodina); addColumn(3, txtGodina); addColumn(4, lblAktivan); addColumn(5, cbAktivan);
		
		}
	
	public void postaviObjekat(EGodina godina) {
		if(godina != null) {
			this.godina = godina;
			cbPretplatnici.setValue(godina.getPretplatnik());
			cbPretplatnici.setDisable(true);
			txtGodina.setText(godina.getGodina().toString());
			cbAktivan.setSelected(godina.getAktivan());
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		godina = null;
		cbPretplatnici.setValue(null);
		cbPretplatnici.setDisable(false);
		txtGodina.setText("");
		cbAktivan.setSelected(false);
		}
	
	public EGodina preuzmiObjekat() {
		if(this.godina == null) {
			godina = new EGodina();
			godina.setId(0L);
			}
		godina.setPretplatnik(cbPretplatnici.getValue());
		godina.setGodina(Integer.valueOf(txtGodina.getText()));
		godina.setAktivan(cbAktivan.isSelected());
		return godina;
		}
	
	public void postaviSirinu() {
		cbPretplatnici.setPrefWidth(txtGodina.getWidth());
		}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtGodina.getText() == null || txtGodina.getText().equals("")) {
			unos = false;
			}
		if(cbPretplatnici.getValue() == null) {
			unos = false;
			}
		return unos;
		}
	
	}
