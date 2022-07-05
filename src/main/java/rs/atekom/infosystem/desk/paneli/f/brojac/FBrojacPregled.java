package rs.atekom.infosystem.desk.paneli.f.brojac;

import java.util.ResourceBundle;

import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.f.brojac.FBrojac;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.TekstCelobrojni;

public class FBrojacPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblBrojPolja, lblStanje;
	private LabelaBold lblPrefiks, lblSufiks, lblTip, lblReset;
	private TextField prefiks, sufiks, tip;
	private CheckBox reset;
	private TekstCelobrojni brojaPolja, stanje;
	private FBrojac brojac;
	private ResourceBundle resource;
	
	public FBrojacPregled(ResourceBundle resource) {
		this.resource = resource;
		napraviGrid();
		napraviElemente(resource);
		popakujElemente();
	}
	
	private void napraviElemente(ResourceBundle resource) {
		lblBrojPolja = new LabelaObaveznaBold(resource.getString("lbl.brojpolja"));
		lblPrefiks = new LabelaBold(resource.getString("lbl.prefiks"));
		lblStanje = new LabelaObaveznaBold(resource.getString("lbl.sledecibroj"));
		lblSufiks = new LabelaBold(resource.getString("lbl.sufiks"));
		lblTip = new LabelaBold(resource.getString("lbl.tip"));
		lblReset = new LabelaBold(resource.getString("lbl.reset"));
		
		brojaPolja = new TekstCelobrojni();
		prefiks = new TextField();
		stanje = new TekstCelobrojni();
		sufiks = new TextField();
		reset = new CheckBox();
		tip = new TextField();
		tip.setEditable(false);
	}
	
	private void popakujElemente() {
		Double minSirina = 80.0;
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHalignment(HPos.RIGHT);
		col3.setMinWidth(minSirina);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHgrow(Priority.ALWAYS);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setHalignment(HPos.RIGHT);
		col5.setMinWidth(minSirina);
		ColumnConstraints col6 = new ColumnConstraints();
		col6.setHgrow(Priority.ALWAYS);
		
		grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
		
		grid.addColumn(0, lblBrojPolja); grid.addColumn(1, brojaPolja); grid.addColumn(2, lblPrefiks); grid.addColumn(3, prefiks); grid.addColumn(4, lblStanje); grid.addColumn(5, stanje);
		grid.addColumn(0, lblSufiks); grid.addColumn(1, sufiks); grid.addColumn(2, lblReset); grid.addColumn(3, reset); grid.addColumn(4, lblTip); grid.addColumn(5, tip);
		
		getChildren().add(grid);
		HBox.setHgrow(grid, Priority.ALWAYS);
	}
	
	public void postaviObjekat(FBrojac brojac) {
		if(brojac != null) {
			this.brojac = brojac;
			brojaPolja.setText(String.valueOf(brojac.getBrojPolja()));
			prefiks.setText(brojac.getPrefiks());
			stanje.setText(String.valueOf(brojac.getStanje()));
			sufiks.setText(brojac.getSufiks());
			reset.setSelected(brojac.getReset());

			if(brojac.getTip() != null) {
				switch(resource.getLocale().toString()) {
				case "srb_rs":
					tip.setText(brojac.getTip().getSr());
					break;
				case "eng_us":
					tip.setText(brojac.getTip().getEn());
					break;
				case "de_de":
					tip.setText(brojac.getTip().getDe());
					break;
				default:
					tip.setText(brojac.getTip().getNaziv());
					}
				}
		}else {
			postaviNovo();
		}
	}
	
	public void postaviNovo() {
		this.brojac = null;
		brojaPolja.setText("");
		prefiks.setText("");
		stanje.setText("");
		sufiks.setText("");
		tip.setText("");
		reset.setSelected(false);
	}
	
	public FBrojac preuzmiObjekat() {
		brojac.setBrojPolja(Integer.valueOf(brojaPolja.getText()));
		brojac.setPrefiks(prefiks.getText());
		brojac.setStanje(Integer.valueOf(stanje.getText()));
		brojac.setSufiks(sufiks.getText());
		brojac.setReset(reset.isSelected());
		return brojac;
	}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(brojac == null)
			unos = false;
		if(brojaPolja.getText() == null || brojaPolja.getText().equals(""))
			unos = false;
		if(stanje.getText() == null || stanje.getText().equals(""))
			unos = false;
		return unos;
	}
}
