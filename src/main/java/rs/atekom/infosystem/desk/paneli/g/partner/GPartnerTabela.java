package rs.atekom.infosystem.desk.paneli.g.partner;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import rs.atekom.infosystem.baza.f.preduzece.FPreduzece;
import rs.atekom.infosystem.baza.g.GPartner;
import rs.atekom.infosystem.baza.g.GPartnerOdgovorPodaci;
import rs.atekom.infosystem.desk.app.pomocne.OpstaTabela;

public class GPartnerTabela extends OpstaTabela<GPartnerOdgovorPodaci>{

	private TableColumn<GPartnerOdgovorPodaci, String> grupaPartnera, sifra, naziv, punNaziv, pib, mb, tel, napomena, email;
	private TableColumn<GPartnerOdgovorPodaci, Boolean> kupac, dobavljac, banka, ustanova;
	
	public GPartnerTabela(ResourceBundle resource) {
		super(resource);
		postaviTabelu();
		}
	
	@SuppressWarnings("unchecked")
	public void postaviTabelu() {
		grupaPartnera = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.grupapartnera"));
		grupaPartnera.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				GPartner partner = param.getValue() == null ? null : param.getValue().getPartner();
				SimpleStringProperty grupa = new SimpleStringProperty(partner == null ? "" : partner.getGrupaPartnera() == null ? "" 
						: partner.getGrupaPartnera().getNaziv());
				return grupa;
				}
			});
		
		sifra = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.sifra"));
		sifra.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				GPartner partner = param.getValue().getPartner() == null ? null : param.getValue().getPartner();
				SimpleStringProperty sifra = new SimpleStringProperty(partner == null ? "" : partner.getSifra());
				return sifra;
				}
			});
		naziv = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.naziv"));
		naziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty naziv = new SimpleStringProperty(preduzece == null ? "" : preduzece.getNaziv());
				return naziv;
				}
			});
		punNaziv = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.punnaziv"));
		punNaziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty punNaziv = new SimpleStringProperty(preduzece == null ? "" : preduzece.getPunNaziv());
				return punNaziv;
				}
			});
		pib = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.pib"));
		pib.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty pib = new SimpleStringProperty(preduzece == null ? "" : preduzece.getPib());
				return pib;
				}
			});
		mb = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.mb"));
		mb.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty mb = new SimpleStringProperty(preduzece == null ? "" : preduzece.getMb());
				return mb;
				}
			});
		tel = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.telefon"));
		tel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty tel = new SimpleStringProperty(preduzece == null ? "" : preduzece.getTel());
				return tel;
				}
			});
		napomena = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.napomena"));
		napomena.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty napomena = new SimpleStringProperty(preduzece == null ? "" : preduzece.getNapomena());
				return napomena;
				}
			});
		email = new TableColumn<GPartnerOdgovorPodaci, String>(resource.getString("lbl.email"));
		email.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<GPartnerOdgovorPodaci, String> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				SimpleStringProperty email = new SimpleStringProperty(preduzece == null ? "" : preduzece.getEmail());
				return email;
				}
			});
		kupac = new TableColumn<GPartnerOdgovorPodaci, Boolean>(resource.getString("lbl.kupac"));
		kupac.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<GPartnerOdgovorPodaci, Boolean> param) {
				GPartner partner = param.getValue() == null ? null : param.getValue().getPartner();
				return new SimpleBooleanProperty(partner == null ? false : partner.getKupac());
				}
			});
		kupac.setCellFactory(new Callback<TableColumn<GPartnerOdgovorPodaci,Boolean>, TableCell<GPartnerOdgovorPodaci,Boolean>>() {
			@Override
			public TableCell<GPartnerOdgovorPodaci, Boolean> call(TableColumn<GPartnerOdgovorPodaci, Boolean> param) {
				CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean> cell = new CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		dobavljac = new TableColumn<GPartnerOdgovorPodaci, Boolean>(resource.getString("lbl.dobavljac"));
		dobavljac.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<GPartnerOdgovorPodaci, Boolean> param) {
				GPartner partner = param.getValue() == null ? null : param.getValue().getPartner();
				return new SimpleBooleanProperty(partner == null ? false : partner.getDobavljac());
				}
			});
		dobavljac.setCellFactory(new Callback<TableColumn<GPartnerOdgovorPodaci, Boolean>, TableCell<GPartnerOdgovorPodaci, Boolean>>() {
			@Override
			public TableCell<GPartnerOdgovorPodaci, Boolean> call(TableColumn<GPartnerOdgovorPodaci, Boolean> param) {
				CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean> cell = new CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		banka = new TableColumn<GPartnerOdgovorPodaci, Boolean>(resource.getString("lbl.banka"));
		banka.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<GPartnerOdgovorPodaci, Boolean> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				return new SimpleBooleanProperty(preduzece == null ? false : preduzece.getBanka() == null ? false : preduzece.getBanka());
				}
			});
		banka.setCellFactory(new Callback<TableColumn<GPartnerOdgovorPodaci, Boolean>, TableCell<GPartnerOdgovorPodaci, Boolean>>() {
			@Override
			public TableCell<GPartnerOdgovorPodaci, Boolean> call(TableColumn<GPartnerOdgovorPodaci, Boolean> param) {
				CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean> cell = new CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		ustanova = new TableColumn<GPartnerOdgovorPodaci, Boolean>(resource.getString("lbl.ustanova"));
		ustanova.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GPartnerOdgovorPodaci, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<GPartnerOdgovorPodaci, Boolean> param) {
				FPreduzece preduzece = param.getValue() == null ? null : param.getValue().getPartner() == null ? null : param.getValue().getPartner() .getPreduzece();
				return new SimpleBooleanProperty(preduzece == null ? false : preduzece.getUstanova() == null ? false : preduzece.getUstanova());
				}
			});
		ustanova.setCellFactory(new Callback<TableColumn<GPartnerOdgovorPodaci, Boolean>, TableCell<GPartnerOdgovorPodaci, Boolean>>() {
			@Override
			public TableCell<GPartnerOdgovorPodaci, Boolean> call(TableColumn<GPartnerOdgovorPodaci, Boolean> param) {
				CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean> cell = new CheckBoxTableCell<GPartnerOdgovorPodaci, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
				}
			});
		
		getColumns().addAll(grupaPartnera, sifra, naziv, punNaziv, pib, mb, tel, napomena, email, kupac, dobavljac, banka, ustanova);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
	
	}
