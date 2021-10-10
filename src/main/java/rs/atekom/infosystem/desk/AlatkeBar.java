package rs.atekom.infosystem.desk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import rs.atekom.infosystem.desk.app.pomocne.TekstCelobrojni;

public class AlatkeBar extends ToolBar{

	private Button novo, izmeni, stampa, pronadji, osvezi, izaberi, /* pdf, bar, **/izbrisi, izadji, naPocetak, nazad, napred, naKraj;
	private TextField pretraga;
	private Collection<Node> elementi;
	private Region region1, region2, region3, region4;
	private ComboBox<Integer> brojZapisaPoStrani;
	private TekstCelobrojni tekucaStrana, ukupnoStrana;
	private Label od;
	
	public AlatkeBar() {
		// There are many ways how you can define a Glyph:
		// Use the font-instance with a name:  new Button("", fontAwesome.create("PLUS").color(Color.BLUE))
		setStyle("-fx-padding: 2 0 2 0");
		novo = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.PLUS).color(Color.BLUE));
		novo.setTooltip(new Tooltip("DODAJ"));
		izmeni = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.PENCIL).color(Color.GREEN));   // Use the Glyph-class with a known enum value
		izmeni.setTooltip(new Tooltip("IZMENI IZABRANE PODATKE"));
		pronadji = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.SEARCH).color(Color.BROWN));
		pronadji.setTooltip(new Tooltip("PRETRAGA PO VREDNOSTI UNETE U POLJE PORED"));
		pretraga = new TextField();
		pretraga.setTooltip(new Tooltip("UNESITE VREDNOST ZA PRETRAGU"));
		osvezi = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.REFRESH).color(Color.RED));
		osvezi.setTooltip(new Tooltip("OSVEŽI PODATKE U TABELI"));
		stampa = new Button("", Glyph.create("FontAwesome|print"));// Use the static Glyph-class create protocol
		izaberi = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.CHECK).color(Color.PURPLE));
		izaberi.setTooltip(new Tooltip("PROSLEDI IZABRANU VREDNOST"));
		//pdf = new Button("", fontAwesome.create(FontAwesome.Glyph.FILE_PDF_ALT).color(Color.RED));    // Use the font-instance with a enum
		//bar = new Button("", fontAwesome.create(FontAwesome.Glyph.BAR_CHART).color(Color.ORANGE));
		izbrisi = new Button("", new Glyph("FontAwesome", "TRASH_ALT").color(Color.RED));// Use the Glyph-class with a icon name
		izbrisi.setTooltip(new Tooltip("IZBRIŠI IZABRANU VREDNOST"));
		izadji = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.CLOSE).color(Color.ORANGERED));
		izadji.setTooltip(new Tooltip("ZATVORI AKTIVAN PROZOR"));
		naPocetak = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.FAST_BACKWARD).color(Color.FORESTGREEN));
		naPocetak.setTooltip(new Tooltip("NAZAD NA PRVU STRANU"));
		nazad = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.BACKWARD).color(Color.FORESTGREEN));
		nazad.setTooltip(new Tooltip("NAZAD ZA JEDNU STRANU"));
		napred = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.FORWARD).color(Color.FORESTGREEN));
		napred.setTooltip(new Tooltip("NAPRED ZA JEDNU STRANU"));
		naKraj = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.FAST_FORWARD).color(Color.FORESTGREEN));
		naKraj.setTooltip(new Tooltip("NAPRED NA POSLEDNJU STRANU"));
		brojZapisaPoStrani = new ComboBox<>();
		brojZapisaPoStrani.setTooltip(new Tooltip("BROJ ZAPISA PO STRANI"));
		brojZapisaPoStrani.getItems().addAll(25, 50, 100, 200, 500, 1000);
		brojZapisaPoStrani.setValue(100);
		tekucaStrana = new TekstCelobrojni(String.valueOf(1));
		tekucaStrana.setTooltip(new Tooltip("TEKUĆA STRANA"));
		tekucaStrana.setAlignment(Pos.CENTER_RIGHT);
		tekucaStrana.setMaxWidth(40);
		tekucaStrana.setMinWidth(40);
		od = new Label("/");
		ukupnoStrana = new TekstCelobrojni(String.valueOf(1));
		ukupnoStrana.setTooltip(new Tooltip("UKUPAN BROJ STRANA ZA PREGLED"));
		ukupnoStrana.setAlignment(Pos.CENTER_RIGHT);
		ukupnoStrana.setMaxWidth(40);
		ukupnoStrana.setMinWidth(40);
		ukupnoStrana.setEditable(false);
		
		
		pretraga.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!oldValue.equals(newValue)) {
				tekucaStrana.setText(String.valueOf(1));
				}
			});
		
		brojZapisaPoStrani.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				tekucaStrana.setText(String.valueOf(1));
				}
			});
		
		region1 = new Region();
		HBox.setHgrow(region1, Priority.SOMETIMES);
		region2 = new Region();
		HBox.setHgrow(region2, Priority.SOMETIMES);
		region3 = new Region();
		HBox.setHgrow(region3,  Priority.SOMETIMES);
		region4 = new Region();
		HBox.setHgrow(region4,  Priority.SOMETIMES);
		
		elementi = FXCollections.observableArrayList();

		elementi.add(pretraga);
		elementi.add(pronadji);
		
		
		if(true) {
			elementi.add(region1);
			elementi.add(naPocetak);
			elementi.add(nazad);
			elementi.add(napred);
			elementi.add(naKraj);
			elementi.add(brojZapisaPoStrani);
			elementi.add(tekucaStrana);
			elementi.add(od);
			elementi.add(ukupnoStrana);
			}
		elementi.add(osvezi);
		elementi.add(region2);
		elementi.add(novo);
		elementi.add(izmeni);
		elementi.add(stampa);
		elementi.add(izaberi);
		elementi.add(region3);
		elementi.add(izbrisi);
		elementi.add(region4);
		elementi.add(izadji);
		
		getItems().addAll(elementi);
		}
	
	public Button vratiNovo() {
		return novo;
		}
	
	public Button vratiIzmeni() {
		return izmeni;
		}
	
	public Button vratiStampa() {
		return stampa;
		}
	
	public Button vratiPronadji() {
		return pronadji;
		}
	
	public Button vratiOsvezi() {
		return osvezi;
		}
	
	public Button vratiIzaberi() {
		return izaberi;
		}
	
	public Button vratiIzbrisi() {
		return izbrisi;
		}
	
	public Button vratiIzadji() {
		return izadji;
		}
	
	public TextField vratiPretraga() {
		 return pretraga;
		 }
	
	public Button getNaPocetak() {
		return naPocetak;
		}

	public Button getNazad() {
		return nazad;
		}

	public void setNazad(Button nazad) {
		this.nazad = nazad;
		}

	public Button getNapred() {
		return napred;
		}

	public void setNapred(Button napred) {
		this.napred = napred;
		}

	public void setNaPocetak(Button naPocetak) {
		this.naPocetak = naPocetak;
		}

	public Button getNaKraj() {
		return naKraj;
		}

	public void setNaKraj(Button naKraj) {
		this.naKraj = naKraj;
		}

	public ComboBox<Integer> getBrojZapisaPoStrani() {
		return brojZapisaPoStrani;
		}

	public void setBrojZapisaPoStrani(ComboBox<Integer> brojZapisaPostrani) {
		this.brojZapisaPoStrani = brojZapisaPostrani;
		}

	public TekstCelobrojni getTekucaStrana() {
		return tekucaStrana;
		}

	public void setTekucaStrana(TekstCelobrojni tekucaStrana) {
		this.tekucaStrana = tekucaStrana;
		}

	public TekstCelobrojni getUkupnoStrana() {
		return ukupnoStrana;
		}

	public void setUkupnoStrana(TekstCelobrojni ukupnoStrana) {
		this.ukupnoStrana = ukupnoStrana;
		}
	
	public void ukloniProsledjivanje() {
		elementi.remove(izaberi);
		getItems().clear();
		getItems().addAll(elementi);
		}

	public void ukloniDugme(ArrayList<Node> dugmad) {
		for(Node d: dugmad) {
			elementi.remove(d);
			}
		getItems().clear();
		getItems().addAll(elementi);
		}
	
	public void ukloniStranicenje() {
		elementi.remove(naPocetak);
		elementi.remove(nazad);
		elementi.remove(napred);
		elementi.remove(naKraj);
		elementi.remove(brojZapisaPoStrani);
		elementi.remove(tekucaStrana);
		elementi.remove(od);
		elementi.remove(ukupnoStrana);
		elementi.remove(region2);
		getItems().clear();
		getItems().addAll(elementi);
		}
	
	public ArrayList<Node> vratiStranicenje(){
		ArrayList<Node> stranicenje = new ArrayList<>();
		stranicenje.add(naPocetak);
		stranicenje.add(nazad);
		stranicenje.add(napred);
		stranicenje.add(naKraj);
		stranicenje.add(brojZapisaPoStrani);
		stranicenje.add(tekucaStrana);
		stranicenje.add(od);
		stranicenje.add(ukupnoStrana);
		stranicenje.add(region2);
		return stranicenje;
		}
	
	public void ukloniSve() {
		elementi.removeAll(elementi);
		getItems().clear();
		getItems().addAll(elementi);
		}
	
	public void dodajZaPartnere(List<Node> nodes) {
		for(Node node : nodes) {
			elementi.add(node);
			}
		elementi.add(pretraga);
		elementi.add(pronadji);
		elementi.add(region1);
		if(true) {
			elementi.add(naPocetak);
			elementi.add(nazad);
			elementi.add(napred);
			elementi.add(naKraj);
			elementi.add(brojZapisaPoStrani);
			elementi.add(tekucaStrana);
			elementi.add(od);
			elementi.add(ukupnoStrana);
			}
		elementi.add(osvezi);
		elementi.add(region2);
		elementi.add(novo);
		elementi.add(izmeni);
		elementi.add(stampa);
		elementi.add(region3);
		elementi.add(izbrisi);
		elementi.add(region4);
		elementi.add(izadji);
		getItems().addAll(elementi);
		}
	
	public Region vratiRegion1() {
		return region1;
		}
	
	public Region vratiRegion2() {
		return region2;
		}
	
	public Region vratiRegion3() {
		return region3;
		}
	
	public void smanjiRegion(Region region) {
		region.setMinWidth(20.0);
		region.setMaxWidth(20.0);
		}
	
	}
