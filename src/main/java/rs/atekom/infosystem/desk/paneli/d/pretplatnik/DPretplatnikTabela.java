package rs.atekom.infosystem.desk.paneli.d.pretplatnik;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnik;
import rs.atekom.infosystem.baza.d.pretplatnik.DPretplatnikPodaci;
import rs.atekom.infosystem.desk.app.pomocne.KolonaInteger;
import rs.atekom.infosystem.desk.app.pomocne.tabela.OpstaTabela;

public class DPretplatnikTabela extends OpstaTabela<DPretplatnikPodaci>{

	private TableColumn<DPretplatnikPodaci, String> agencija, naziv, punNaziv, pib, mb, telefon, fax, sifraDelatnosti, email, odgovornoLice;
	private TableColumn<DPretplatnikPodaci, Boolean> obveznikPDV, aktivan;
	private KolonaInteger<DPretplatnikPodaci, Integer> poreskiPeriod, vrstaVlasnistva, vrstaPosla, velicinaObveznika, vrstaIzvestaja, racunovodstvenaRegulativa;
	
	public DPretplatnikTabela(ResourceBundle resource) {
		super(resource);
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public void postaviTabelu() {
		agencija = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.agencija"));
		agencija.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getAgencija() == null ? "" : pretplatnik.getAgencija().getNaziv());
				}
			});
		naziv = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.naziv"));
		//naziv.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.naziv"));
		naziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getNaziv() == null ? "" : pretplatnik.getNaziv());
				}
			});
		punNaziv = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.punnaziv"));
		punNaziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getPunNaziv() == null ? "" : pretplatnik.getPunNaziv());
				}
			});
		pib = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.pib"));
		pib.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getPib() == null ? "" : pretplatnik.getPib());
				}
			});
		mb = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.mb"));
		mb.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getMb() == null ? "" : pretplatnik.getMb());
				}
			});
		telefon = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.telefon"));
		telefon.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getTelefon() == null ? "" : pretplatnik.getTelefon());
				}
			});
		fax = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.fax"));
		fax.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getFax() == null ? "" : pretplatnik.getFax());
				}
			});
		sifraDelatnosti = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.sifradelatnosti"));
		sifraDelatnosti.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getSifraDelatnosti() == null ? "" : pretplatnik.getSifraDelatnosti());
				}
			});
		email = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.email"));
		email.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getEmail() == null ? "" : pretplatnik.getEmail());
				}
			});
		odgovornoLice = new TableColumn<DPretplatnikPodaci, String>(resource.getString("lbl.odgovornolice"));
		odgovornoLice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<DPretplatnikPodaci, String> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleStringProperty(pretplatnik == null ? "" : pretplatnik.getOdgovornoLice() == null ? "" : pretplatnik.getOdgovornoLice());
				}
			});
		obveznikPDV = new TableColumn<DPretplatnikPodaci, Boolean>(resource.getString("lbl.obveznikpdv"));
		obveznikPDV.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<DPretplatnikPodaci, Boolean> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleBooleanProperty(pretplatnik == null ? null : pretplatnik.getObveznikPDV() == null ? false : pretplatnik.getObveznikPDV());
				}
			});
		obveznikPDV.setCellFactory(new Callback<TableColumn<DPretplatnikPodaci,Boolean>, TableCell<DPretplatnikPodaci,Boolean>>() {
			@Override
			public TableCell<DPretplatnikPodaci, Boolean> call(TableColumn<DPretplatnikPodaci, Boolean> param) {
				CheckBoxTableCell<DPretplatnikPodaci, Boolean> cell = new CheckBoxTableCell<DPretplatnikPodaci, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		aktivan = new TableColumn<DPretplatnikPodaci, Boolean>(resource.getString("lbl.aktivan"));
		aktivan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DPretplatnikPodaci,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<DPretplatnikPodaci, Boolean> param) {
				DPretplatnik pretplatnik = param.getValue().getPretplatnik();
				return new SimpleBooleanProperty(pretplatnik == null ? null : pretplatnik.getAktivan() == null ? false : pretplatnik.getAktivan());
				}
			});
		aktivan.setCellFactory(new Callback<TableColumn<DPretplatnikPodaci,Boolean>, TableCell<DPretplatnikPodaci,Boolean>>() {
			@Override
			public TableCell<DPretplatnikPodaci, Boolean> call(TableColumn<DPretplatnikPodaci, Boolean> param) {
				CheckBoxTableCell<DPretplatnikPodaci, Boolean> cell = new CheckBoxTableCell<DPretplatnikPodaci, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		poreskiPeriod = new KolonaInteger<DPretplatnikPodaci, Integer>(resource.getString("lbl.poreskiperiod"));
		//poreskiPeriod.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.poreskiPeriod"));
		//bathroomColumn.setCellValueFactory(tf -> tf.getValue().getHouse().bathroomProperty());
		poreskiPeriod.setCellValueFactory(pp -> new SimpleIntegerProperty(pp.getValue().getPretplatnik().getPoreskiPeriod()).asObject());
		vrstaVlasnistva = new KolonaInteger<DPretplatnikPodaci, Integer>(resource.getString("lbl.vrstavlasnistva"));
		vrstaVlasnistva.setCellValueFactory(pp -> new SimpleIntegerProperty(pp.getValue().getPretplatnik().getVrstaVlasnistva()).asObject());
		//vrstaVlasnistva.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.vrstaVlasnistva"));
		vrstaPosla = new KolonaInteger<DPretplatnikPodaci, Integer>(resource.getString("lbl.vrstaposla"));
		vrstaPosla.setCellValueFactory(pp -> new SimpleIntegerProperty(pp.getValue().getPretplatnik().getVrstaPosla()).asObject());
		//vrstaPosla.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.vrstaPosla"));
		velicinaObveznika = new KolonaInteger<DPretplatnikPodaci, Integer>(resource.getString("lbl.velicinaobveznika"));
		velicinaObveznika.setCellValueFactory(pp -> new SimpleIntegerProperty(pp.getValue().getPretplatnik().getVelicinaObveznika()).asObject());
		//velicinaObveznika.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.velicinaObveznika"));
		vrstaIzvestaja = new KolonaInteger<DPretplatnikPodaci, Integer>(resource.getString("lbl.vrstaizvestaja"));
		vrstaIzvestaja.setCellValueFactory(pp -> new SimpleIntegerProperty(pp.getValue().getPretplatnik().getVrstaIzvestaja()).asObject());
		//vrstaIzvestaja.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.vrstaIzvestaja"));
		racunovodstvenaRegulativa = new KolonaInteger<DPretplatnikPodaci, Integer>(resource.getString("lbl.racunregulativa"));
		racunovodstvenaRegulativa.setCellValueFactory(pp -> new SimpleIntegerProperty(pp.getValue().getPretplatnik().getRacunovodstvenaRegulativa()).asObject());
		//racunovodstvenaRegulativa.setCellValueFactory(new PropertyValueFactory<>("pretplatnik.racunovodstvenaRegulativa"));
		
		getColumns().addAll(agencija, naziv, punNaziv, pib, mb, sifraDelatnosti, telefon, fax, email, odgovornoLice, obveznikPDV, 
				poreskiPeriod, vrstaVlasnistva, vrstaPosla, velicinaObveznika, vrstaIzvestaja, racunovodstvenaRegulativa, aktivan);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
	}
