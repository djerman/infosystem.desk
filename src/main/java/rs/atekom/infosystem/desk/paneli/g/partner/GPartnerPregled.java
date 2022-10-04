package rs.atekom.infosystem.desk.paneli.g.partner;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.baza.e.konto.EKonto;
import rs.atekom.infosystem.baza.f.grupapartnera.FGrupaPartnera;
import rs.atekom.infosystem.baza.f.preduzece.FPreduzece;
import rs.atekom.infosystem.baza.g.GPartner;
import rs.atekom.infosystem.baza.g.GPartnerOdgovorPodaci;
import rs.atekom.infosystem.baza.i.IAdresa;
import rs.atekom.infosystem.desk.a.ComboJezici;
import rs.atekom.infosystem.desk.a.DesniColumnConstraint;
import rs.atekom.infosystem.desk.a.Jezik;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.TekstDecimalni;
import rs.atekom.infosystem.desk.paneli.c.mesto.CMestoComboBox;
import rs.atekom.infosystem.desk.paneli.e.konto.EKontoComboBox;

public class GPartnerPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblNaziv, lblAdresa, lblPib, lblMb;
	private LabelaBold lblSifra, lblGrupaPartnera, lblPunNaziv, lblPdv, lblTel, lblNapomena, lblEmail, lblOdgovornoLice,
	lblKupacKonto, lblDobavljacKonto, lblKupac, lblDobavljac, lblBanka, lblUstanova, lblKupacRabat, lblDobavljacRabat, lblMesto, lblJezik;
	private TextField txtSifra, txtNaziv, txtPunNaziv, txtPib, txtMb, txtTel, txtNapomena, txtEmail, txtOdgovornoLice, txtMesto, txtAdresa;
	private TekstDecimalni txtKupacRabat, txtDobavljacRabat;
	private CheckBox cbPDV, cbKupac, cbDobavljac, cbBanka, cbUstanova;
	private CMestoComboBox cbMesto;
	private SearchableComboBox<FGrupaPartnera> cbGrupe;
	private Boolean kupac;
	private GPartnerPanel panel;
	private GridPane noseci, prvi, drugi;
	private GPartner partner;
	private FPreduzece preduzece;
	private IAdresa sediste;
	private GPartnerOdgovorPodaci partnerPodaci;
	private EKontoComboBox cmbKontoKupac, cmbKontoDobavljac;
	private ComboJezici cmbJezik;
	
	public GPartnerPregled(GPartnerPanel panel, ResourceBundle resource, Boolean kupac) {
		setSpacing(10);
		this.panel = panel;
		this.kupac = kupac;//ako je null vuče sve, ako je true samo kupce, ako je false samo dobavljače
		napraviElemente(resource);
		popakujElemente();
		}
	
	private void napraviElemente(ResourceBundle resource) {
		noseci = new GridPane();
		noseci.setHgap(10);
		
		ColumnConstraints ccOsnovno = new ColumnConstraints();
		ccOsnovno.setHalignment(HPos.CENTER);
		ccOsnovno.setPercentWidth(35);
		ColumnConstraints ccDesno = new ColumnConstraints();
		ccDesno.setHalignment(HPos.CENTER);
		ccDesno.setPercentWidth(65);
        noseci.getColumnConstraints().addAll(ccOsnovno, ccDesno);
        
        prvi = new GridPane();
        prvi.setVgap(5);
        prvi.setHgap(5);
        
        drugi = new GridPane();
        drugi.setVgap(5);
        drugi.setHgap(5);
        
        lblGrupaPartnera = new LabelaBold(resource.getString("lbl.grupapartnera"));
        //lblGrupaPartnera.setStyle("-fx-underline: true;");
        lblSifra = new LabelaBold(resource.getString("lbl.sifra"));
        lblNaziv = new LabelaObaveznaBold(resource.getString("lbl.naziv"));
        lblPunNaziv = new LabelaBold(resource.getString("lbl.punnaziv"));
        lblPdv = new LabelaBold(resource.getString("lbl.updv"));
        lblPib = new LabelaObaveznaBold(resource.getString("lbl.pib"));
        lblMb = new LabelaObaveznaBold(resource.getString("lbl.mb"));
        lblTel = new LabelaBold(resource.getString("lbl.telefon"));
        lblNapomena = new LabelaBold(resource.getString("lbl.napomena"));
        lblEmail = new LabelaBold(resource.getString("lbl.email"));
        lblOdgovornoLice = new LabelaBold(resource.getString("lbl.odgovornolice"));
    	lblKupacKonto = new LabelaBold(resource.getString("lbl.kupackonto"));
    	lblDobavljacKonto = new LabelaBold(resource.getString("lbl.dobavljackonto"));
    	lblKupac = new LabelaBold(resource.getString("lbl.kupac"));
    	lblDobavljac = new LabelaBold(resource.getString("lbl.dobavljac"));
    	lblBanka = new LabelaBold(resource.getString("lbl.banka"));
    	lblUstanova = new LabelaBold(resource.getString("lbl.ustanova"));
    	lblKupacRabat = new LabelaBold(resource.getString("lbl.kupacrabat"));
    	lblDobavljacRabat = new LabelaBold(resource.getString("lbl.dobavljacrabat"));
    	lblMesto = new LabelaBold(resource.getString("lbl.mesto"));
    	lblAdresa = new LabelaObaveznaBold(resource.getString("lbl.adresa"));
    	lblJezik = new LabelaBold(resource.getString("lbl.jezik"));
    	
    	//txtGrupaPartnera = new TextField();
    	cbGrupe = new SearchableComboBox<FGrupaPartnera>();
    	//txtGrupaPartnera.setDisable(true);
    	cbGrupe.valueProperty().addListener(new ChangeListener<FGrupaPartnera>() {
			@Override
			public void changed(ObservableValue<? extends FGrupaPartnera> observable, FGrupaPartnera oldValue,
					FGrupaPartnera newValue) {
				if(newValue != null) {
					cmbKontoKupac.setValue(newValue.getPrihod());
					cmbKontoDobavljac.setValue(newValue.getRashod());
				}else {
					cmbKontoKupac.setValue(null);
					cmbKontoDobavljac.setValue(null);
				}
			}
		});
    	txtSifra = new TextField();
    	txtNaziv = new TextField();
    	txtPunNaziv = new TextField();
    	txtPib = new TextField();
    	txtMb = new TextField();
    	txtTel = new TextField();
    	txtNapomena = new TextField();
    	txtEmail = new TextField();
    	txtOdgovornoLice = new TextField();
    	txtMesto = new TextField();
    	txtMesto.setDisable(true);
    	txtAdresa = new TextField();
		cmbKontoKupac = new EKontoComboBox(resource);
		cmbKontoDobavljac = new EKontoComboBox(resource);
    	txtKupacRabat = new TekstDecimalni();
    	txtKupacRabat.setText("0,00");
    	txtDobavljacRabat = new TekstDecimalni();
    	txtDobavljacRabat.setText("0,00");
    	
    	cbPDV = new CheckBox();
    	cbKupac = new CheckBox();
    	if(kupac != null && kupac) {
    		cbKupac.setSelected(true);
    		cbKupac.setDisable(true);
    		}
    	cbDobavljac = new CheckBox();
    	if(kupac != null && !kupac) {
    		cbDobavljac.setSelected(true);
    		cbDobavljac.setDisable(true);
    		}
    	cbBanka = new CheckBox();
    	cbUstanova = new CheckBox();
    	cbMesto = new CMestoComboBox(resource);
    	cmbJezik = new ComboJezici(resource);
    	cmbJezik.setMaxWidth(Double.MAX_VALUE);
    	
		}
	
	private void popakujElemente() {
		ColumnConstraints col1 = new DesniColumnConstraint();
		ColumnConstraints col2 = new ColumnConstraints();

		prvi.getColumnConstraints().addAll(col1, col2);
		prvi.getColumnConstraints().get(0).setPercentWidth(25);
		prvi.getColumnConstraints().get(1).setPercentWidth(75);
		
		prvi.addColumn(0, lblGrupaPartnera); prvi.addColumn(1, cbGrupe);
		prvi.addColumn(0, lblSifra); prvi.addColumn(1, txtSifra);
		prvi.addColumn(0, lblNaziv); prvi.addColumn(1, txtNaziv);
		prvi.addColumn(0, lblPunNaziv); prvi.addColumn(1, txtPunNaziv);
		prvi.addColumn(0, lblMesto); prvi.addColumn(1, cbMesto);
		prvi.addColumn(0, lblAdresa); prvi.addColumn(1, txtAdresa);
		prvi.addColumn(0, lblNapomena); prvi.addColumn(1, txtNapomena);
		
		ColumnConstraints col21 = new DesniColumnConstraint();
		ColumnConstraints col22 = new ColumnConstraints();
		ColumnConstraints col23 = new DesniColumnConstraint();;
		ColumnConstraints col24 = new ColumnConstraints();
		ColumnConstraints col25 = new DesniColumnConstraint();
		ColumnConstraints col26 = new ColumnConstraints();

		drugi.getColumnConstraints().addAll(col21, col22, col23, col24, col25, col26);
		drugi.getColumnConstraints().get(0).setPercentWidth(10);
		drugi.getColumnConstraints().get(1).setPercentWidth(2);
		drugi.getColumnConstraints().get(2).setPercentWidth(20);
		drugi.getColumnConstraints().get(3).setPercentWidth(30);
		drugi.getColumnConstraints().get(4).setPercentWidth(20);
		drugi.getColumnConstraints().get(5).setPercentWidth(15);
		
		drugi.addColumn(0, lblPdv); drugi.addColumn(1, cbPDV); drugi.addColumn(2, lblPib); drugi.addColumn(3, txtPib); 
		drugi.addColumn(4, lblMb); drugi.addColumn(5, txtMb);
		drugi.addColumn(0, lblKupac); drugi.addColumn(1, cbKupac); drugi.addColumn(2, lblKupacKonto); drugi.addColumn(3, cmbKontoKupac); 
		drugi.addColumn(4, lblKupacRabat); drugi.addColumn(5, txtKupacRabat);
		drugi.addColumn(0, lblDobavljac); drugi.addColumn(1, cbDobavljac); drugi.addColumn(2, lblDobavljacKonto); drugi.addColumn(3, cmbKontoDobavljac); 
		drugi.addColumn(4, lblDobavljacRabat); drugi.addColumn(5, txtDobavljacRabat);
		drugi.addColumn(0, lblBanka); drugi.addColumn(1, cbBanka); drugi.addColumn(2, lblEmail); drugi.addColumn(3, txtEmail); 
		drugi.addColumn(4, lblOdgovornoLice); drugi.addColumn(5, txtOdgovornoLice);
		drugi.addColumn(0, lblUstanova); drugi.addColumn(1, cbUstanova); drugi.addColumn(2, lblTel); drugi.addColumn(3, txtTel); 
		drugi.addColumn(4, lblJezik); drugi.addColumn(5, cmbJezik);
		
		noseci.addColumn(0, prvi); noseci.addColumn(1, drugi);
		getChildren().add(noseci);
		HBox.setHgrow(noseci, Priority.ALWAYS);
		}
	
	public void postaviObjekat(GPartnerOdgovorPodaci partnerSaPodacima) {
		partnerPodaci = partnerSaPodacima;
		partner = partnerPodaci.getPartner();
		preduzece = partner.getPreduzece();
		if(partner != null) {
			cbGrupe.setValue(partner == null ? null : partner.getGrupaPartnera());
			txtSifra.setText(partner.getSifra());
			
			cbKupac.setSelected(partner.getKupac());
			cmbKontoKupac.setValue(partner.getKupacKonto());
			txtKupacRabat.setText(partner == null || partner.getKupacRabat() == null ? "0,00" : txtKupacRabat.vratiFormatiranBroj(partner.getKupacRabat()));
			
			cbDobavljac.setSelected(partner.getDobavljac());
			cmbKontoDobavljac.setValue(partner.getDobavljacKonto());
			txtDobavljacRabat.setText(partner == null || partner.getDobavljacRabat() == null ? "0,00" : txtDobavljacRabat.vratiFormatiranBroj(partner.getDobavljacRabat()));
			
			if(partnerPodaci.getSediste() != null) {
				sediste = partnerPodaci.getSediste();
				txtAdresa.setText(sediste.getUlicaBroj());
				cbMesto.setValue(sediste.getMesto());
				}else {
					sediste = null;
					txtAdresa.setText("");
					cbMesto.setValue(null);
					}
			String jzk = partner.getJezik();
			if(jzk == null) {
				cmbJezik.setValue(null);
			}else {
				for(int i= 0; i < cmbJezik.getItems().size(); i++) {
					Jezik jezik = cmbJezik.getItems().get(i);
					if(jezik.getOznaka().equals(partner.getJezik())) {
						cmbJezik.setValue(jezik);
						break;
					}
				}
			}
			
			if(preduzece != null) {
				txtNaziv.setText(preduzece.getNaziv());
				txtPunNaziv.setText(preduzece.getPunNaziv());
				txtPib.setText(preduzece.getPib());
				txtMb.setText(preduzece.getMb());
				cbPDV.setSelected(preduzece.getPdv());
				txtTel.setText(preduzece.getTel());
				txtNapomena.setText(preduzece.getNapomena());
				txtEmail.setText(preduzece.getEmail());
				cbBanka.setSelected(preduzece.getBanka());
				cbUstanova.setSelected(preduzece.getUstanova());
				txtOdgovornoLice.setText(preduzece.getOdgovornoLice());
				}else {
					postaviNovoPreduzece();
					}
			}else {
				postaviNovo();
				}
		}
	
	public void postaviNovo() {
		partnerPodaci = null;
		partner = null;
		sediste = null;
		cbGrupe.setValue(null);
    	if(kupac != null && kupac) {
    		cbKupac.setSelected(true);
    		cbDobavljac.setSelected(false);
    		}
    	cbDobavljac = new CheckBox();
    	if(kupac != null && !kupac) {
    		cbDobavljac.setSelected(true);
    		cbKupac.setSelected(false);
    		}
		cmbKontoKupac.setValue(null);
		txtKupacRabat.setText("0,00");
		cmbKontoDobavljac.setValue(null);
		txtDobavljacRabat.setText("0,00");
		cbMesto.setValue(null);
		txtAdresa.setText("");
		cmbJezik.setValue(null);
		preduzece = null;
		postaviNovoPreduzece();
		}
	
	private void postaviNovoPreduzece() {
		txtSifra.setText("");
		txtNaziv.setText("");
		txtPunNaziv.setText("");
		txtPib.setText("");
		txtMb.setText("");
		cbPDV.setSelected(false);
		txtTel.setText("");
		txtNapomena.setText("");
		txtEmail.setText("");
		cbBanka.setSelected(false);
		cbUstanova.setSelected(false);
		txtOdgovornoLice.setText("");
		}
	
	public GPartnerOdgovorPodaci preuzmiObjekat() {
		try {
			if(this.partnerPodaci == null ) {
				partnerPodaci = new GPartnerOdgovorPodaci();
				partner = new GPartner();
				partner.setPretplatnik(panel.vratiPretplatnika());
				partner.setSifra(txtSifra.getText() == null ? "" : txtSifra.getText().trim());
				}
			partner.setGrupaPartnera(cbGrupe.getValue());
			partner.setKupac(cbKupac.isSelected());
			partner.setKupacKonto(cmbKontoKupac.getValue());
			partner.setDobavljac(cbDobavljac.isSelected());
			partner.setDobavljacKonto(cmbKontoDobavljac.getValue());
			try {
				partner.setKupacRabat(txtKupacRabat.vratiDecimalniBroj(txtKupacRabat.getText()));//new BigDecimal(txtKupacRabat.getText() == null ? "0.00" : txtKupacRabat.getText().trim())
				}catch (Exception e) {
					e.printStackTrace();
					partner.setKupacRabat(txtKupacRabat.vratiDecimalniBroj("0,00"));
					}
			try {
				partner.setDobavljacRabat(txtDobavljacRabat.vratiDecimalniBroj(txtDobavljacRabat.getText()));
				}catch (Exception e) {
					partner.setDobavljacRabat(txtDobavljacRabat.vratiDecimalniBroj("0,00"));
					}
			partner.setJezik(cmbJezik.getValue() == null ? null : cmbJezik.getValue().getOznaka());
			
			if(sediste == null) {
				sediste = new IAdresa();
				sediste.setPretplatnik(partner.getPretplatnik());
				sediste.setPartner(partner);
				}
			sediste.setMesto(cbMesto.getValue());
			sediste.setUlicaBroj(txtAdresa.getText());

			if(preduzece == null) {
				preduzece = new FPreduzece();
				preduzece.setPretplatnik(partner.getPretplatnik());
				}
			
			preduzece.setNaziv(txtNaziv.getText() == null ? "" : txtNaziv.getText().trim());
			preduzece.setPunNaziv(txtPunNaziv.getText() == null ? "" : txtPunNaziv.getText().trim());
			preduzece.setPib(txtPib.getText() == null ? "" : txtPib.getText().trim());
			preduzece.setMb(txtMb.getText() == null ? "" : txtMb.getText().trim());
			preduzece.setPdv(cbPDV.isSelected());
			preduzece.setTel(txtTel.getText() == null ? "" : txtTel.getText().trim());
			preduzece.setNapomena(txtNapomena.getText() == null ? "" : txtNapomena.getText().trim());
			preduzece.setEmail(txtEmail.getText() == null ? "" : txtEmail.getText().trim());
			preduzece.setBanka(cbBanka.isSelected());
			preduzece.setUstanova(cbUstanova.isSelected());
			preduzece.setOdgovornoLice(txtOdgovornoLice.getText() == null ? "" : txtOdgovornoLice.getText().trim());
			
			partner.setPreduzece(preduzece);
			
			//this.partnerPodaci.setPartner(partner);
			this.partnerPodaci.setPartner(partner);
			this.partnerPodaci.setSediste(sediste);
			//System.out.println(this.partnerPodaci.getPartner().getGrupaPartnera() == null ? " nema grupe" :  "ime grupe " + this.partnerPodaci.getPartner().getGrupaPartnera().getNaziv());
			return this.partnerPodaci;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
				}
		}
	
	public Boolean proveraUnosa() {
		Boolean unos = true;
		if(txtNaziv.getText() == null || txtNaziv.getText().equals("")) {
			unos = false;
			}
		if(txtAdresa.getText() == null || txtAdresa.getText().equals("")) {
			unos = false;
			}
		if(txtPib.getText() == null || txtPib.getText().equals("")) {
			unos = false;
			}
		if(txtMb.getText() == null || txtMb.getText().equals("")) {
			unos = false;
			}
		try {
			BigDecimal dobRabat = txtDobavljacRabat.vratiDecimalniBroj(txtDobavljacRabat.getText());
			BigDecimal kupRabat = txtKupacRabat.vratiDecimalniBroj(txtKupacRabat.getText());
			if(dobRabat.intValue() > 100 || kupRabat.intValue() > 100) {
				unos = false;
			}
		}catch (Exception e) {
			unos = false;
		}
		return unos;
		}
	
	public void popuniGrupe(List<FGrupaPartnera> lista) {
		if(cbGrupe.getItems().size() > 0) {
			//cbGrupe.getItems().clear();
			}else {
				cbGrupe.getItems().add(null);
				cbGrupe.getItems().addAll(FXCollections.observableArrayList(lista));
				}
		}
	
	public void popuniMesta(List<CMesto> lista) {
		if(cbMesto.getItems().size() > 0) {
			
			}else {
				cbMesto.getItems().add(null);
				cbMesto.getItems().addAll(FXCollections.observableArrayList(lista));
				}
		}
	
	public void popuniKonta(List<EKonto> konta) {
		if(cmbKontoKupac.getItems() != null)
			cmbKontoKupac.getItems().clear();
		
		if(cmbKontoKupac.getItems() != null) {
			cmbKontoDobavljac.getItems().clear();
		}
		
		if(konta != null) {
			cmbKontoKupac.getItems().addAll(FXCollections.observableArrayList(konta));
			cmbKontoDobavljac.getItems().addAll(FXCollections.observableArrayList(konta));
		}
	}
	
	}
