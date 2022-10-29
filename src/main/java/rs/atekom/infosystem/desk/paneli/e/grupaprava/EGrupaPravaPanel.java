package rs.atekom.infosystem.desk.paneli.e.grupaprava;

import java.util.List;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.springframework.http.ResponseEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.baza.a.prava.APrava;
import rs.atekom.infosystem.baza.e.grupaprava.EGrupaPrava;
import rs.atekom.infosystem.baza.e.grupaprava.EGrupaPravaOdgovor;
import rs.atekom.infosystem.desk.a.OsnovniLayout;
import rs.atekom.infosystem.desk.a.osnovnipanel.OsnovniPanel;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.rest.EGrupaPravaRestKlijent;

public class EGrupaPravaPanel extends OsnovniPanel{

	//private ListSelectionView<APrava> pregledPrava;
	private ListView<APrava> dodeljena, slobodna;
	private VBox prenos, dodeljenePregled, slobodnePregled;
	private LabelaBold dodeljeneLabela, slobodneLabela;
	private Button ukloni, ukloniSve, dodaj, dodajSve;
	private HBox layout, pregledPrava;
	private EGrupaPravaPregled pregled;
	private EGrupaPravaTabela tabela;
	private TableViewSelectionModel<EGrupaPrava> izborGrupePrava;
	private GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
	private EGrupaPravaRestKlijent restGrupa;
	
	public EGrupaPravaPanel(OsnovniLayout ol) {
		super(ol);
		}
	
	@Override
	public void postaviElemente() {
		restGrupa = new EGrupaPravaRestKlijent(vratiRestServis());
		
		layout = new HBox();
		layout.setSpacing(10);
		
		pregledPrava = new HBox();
		
		dodeljenePregled = new VBox(5);
		dodeljena = new ListView<APrava>();
		dodeljeneLabela = new LabelaBold(vratiOsnovniLayout().vratiPrevod("lbl.dodeljene"));
		dodeljenePregled.getChildren().addAll(dodeljeneLabela, dodeljena);
		VBox.setVgrow(dodeljena, Priority.ALWAYS);
		
		slobodnePregled = new VBox(5);
		slobodna = new ListView<APrava>();
		slobodneLabela = new LabelaBold(vratiOsnovniLayout().vratiPrevod("lbl.slobodne"));
		slobodnePregled.getChildren().addAll(slobodneLabela, slobodna);
		VBox.setVgrow(slobodna, Priority.ALWAYS);
		
		prenos = new VBox();
		ukloni = new Button("", fontAwesome.create(FontAwesome.Glyph.FORWARD).color(Color.BLACK));
		ukloniSve = new Button("", fontAwesome.create(FontAwesome.Glyph.FAST_FORWARD).color(Color.BLACK));
		dodaj = new Button("", fontAwesome.create(FontAwesome.Glyph.BACKWARD).color(Color.BLACK));
		dodajSve = new Button("", fontAwesome.create(FontAwesome.Glyph.FAST_BACKWARD).color(Color.BLACK));
		prenos.setAlignment(Pos.CENTER);
		prenos.getChildren().addAll(ukloni, ukloniSve, dodaj, dodajSve);
		
		pregledPrava.getChildren().addAll(dodeljenePregled, prenos, slobodnePregled);
		HBox.setHgrow(dodeljenePregled, Priority.ALWAYS);
		//HBox.setHgrow(prenos, Priority.ALWAYS);
		HBox.setHgrow(slobodnePregled, Priority.ALWAYS);
		
		postaviInfo();
		postaviPregled();
		postaviKomande();
		
		izbrisi.setOnAction(e -> {
			if(izborGrupePrava.getSelectedItem() != null) {
				azurirajTabelu(false);
				}else {
					pokaziGresku(vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje"), vratiOsnovniLayout().vratiPrevod("obavestenja.brisanje.poruka"));
				}
		});
		
		sacuvaj.setOnAction(e -> {
			if(pregled.proveraUnosa()) {
				azurirajTabelu(true);
			}else {
				pokaziObaveznaPolja(vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja"), 
						vratiOsnovniLayout().vratiPrevod("obavestenja.obaveznapolja.obavestenje"));
			}
		});
		
		novo.setOnAction(e-> {
			pregled.postaviNovo();
			izborGrupePrava.clearSelection();
		});
		
		postaviTabelu();
		popuniTabelu();
		
		layout.getChildren().addAll(tabela, pregledPrava);
		
		VBox.setVgrow(tabela, Priority.ALWAYS);
		VBox.setVgrow(pregledPrava, Priority.ALWAYS);
		HBox.setHgrow(tabela, Priority.ALWAYS);
		HBox.setHgrow(pregledPrava, Priority.ALWAYS);
		
		vratiRoot().getChildren().addAll(pregled, komande, layout, info);
		
		VBox.setVgrow(layout, Priority.ALWAYS);
		setContent(vratiRoot());
		}

	private void postaviPregled() {
		pregled = new EGrupaPravaPregled(this, vratiOsnovniLayout().vratiResource());
		}
	
	private void postaviTabelu() {
		tabela = new EGrupaPravaTabela(vratiOsnovniLayout().vratiResource());
		izborGrupePrava = tabela.getSelectionModel();
		izborGrupePrava.setSelectionMode(SelectionMode.SINGLE);
		izborGrupePrava.selectedItemProperty().addListener(new ChangeListener<EGrupaPrava>() {
			@Override
			public void changed(ObservableValue<? extends EGrupaPrava> observable, EGrupaPrava oldValue,
					EGrupaPrava newValue) {
				// TODO Auto-generated method stub
				}
			});
		}
	
	@Override
	public void popuniTabelu() {
		ResponseEntity<EGrupaPravaOdgovor> odgovor = null; 
		try {
			odgovor = restGrupa.lista(vratiPretplatnika());
			statusOdgovora(odgovor);
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	public void osveziTabelu(List<EGrupaPrava> listaGrupaPrava) {
		if(tabela.getItems() != null)
			tabela.getItems().clear();
		ObservableList<EGrupaPrava> lista = null;
		if(listaGrupaPrava != null) {
			lista = FXCollections.observableArrayList(listaGrupaPrava);
			postaviUkupno(listaGrupaPrava.size());
			}else {
				postaviUkupno(0);
				}
		tabela.getSortOrder().clear();
		tabela.setItems(lista);
		tabela.refresh();
		}
	
	public void azurirajTabelu(Boolean snimi) {
		ResponseEntity<EGrupaPravaOdgovor> odgovor = null; 
		try {
			if(snimi) {
				odgovor = restGrupa.snimi(pregled.preuzmiObjekat());
			}else {
				odgovor = restGrupa.brisi(izborGrupePrava.getSelectedItem());
			}
			statusOdgovora(odgovor);
		}catch (Exception e) {
			e.printStackTrace();
			vratiNemaOdgovoraServera();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void izvrsen(Object odg) {
		try {
			ResponseEntity<EGrupaPravaOdgovor> odgovor = (ResponseEntity<EGrupaPravaOdgovor>) odg;
			osveziTabelu(odgovor.getBody() == null ? null : odgovor.getBody().getLista());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
