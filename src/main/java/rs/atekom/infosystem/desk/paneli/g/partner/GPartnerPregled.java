package rs.atekom.infosystem.desk.paneli.g.partner;

import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import rs.atekom.infosystem.baza.c.CMesto;
import rs.atekom.infosystem.baza.f.grupapartnera.FGrupaPartnera;
import rs.atekom.infosystem.baza.f.preduzece.FPreduzece;
import rs.atekom.infosystem.baza.g.GPartner;
import rs.atekom.infosystem.baza.g.GPartnerOdgovorPodaci;
import rs.atekom.infosystem.baza.i.IAdresa;
import rs.atekom.infosystem.desk.a.OsnovniPregled;
import rs.atekom.infosystem.desk.app.pomocne.LabelaBold;
import rs.atekom.infosystem.desk.app.pomocne.LabelaObaveznaBold;
import rs.atekom.infosystem.desk.app.pomocne.TekstCelobrojni;
import rs.atekom.infosystem.desk.app.pomocne.TekstDecimalni;
import rs.atekom.infosystem.desk.paneli.c.mesto.CMestoComboBox;

public class GPartnerPregled extends OsnovniPregled{

	private LabelaObaveznaBold lblSifra, lblNaziv, lblAdresa, lblPib, lblMb;
	private LabelaBold lblGrupaPartnera, lblPunNaziv, lblPdv, lblTel, lblNapomena, lblEmail, lblOdgovornoLice,
	lblKupacKonto, lblDobavljacKonto, lblKupac, lblDobavljac, lblBanka, lblUstanova, lblKupacRabat, lblDobavljacRabat, lblMesto;
	private TextField txtSifra, txtNaziv, txtPunNaziv, txtPib, txtMb, txtTel, txtNapomena, txtEmail, txtOdgovornoLice, txtMesto, txtAdresa;
	private TekstCelobrojni txtKupacKonto, txtDobavljacKonto;
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
        lblSifra = new LabelaObaveznaBold(resource.getString("lbl.sifra"));
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
    	
    	//txtGrupaPartnera = new TextField();
    	cbGrupe = new SearchableComboBox<FGrupaPartnera>();
    	//txtGrupaPartnera.setDisable(true);
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
    	txtKupacKonto = new TekstCelobrojni();
    	txtDobavljacKonto = new TekstCelobrojni();
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
		}
	
	private void popakujElemente() {
		Double minSirina = 100.0;
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setMinWidth(minSirina);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		prvi.getColumnConstraints().addAll(col1, col2);
		
		ColumnConstraints col21 = new ColumnConstraints();
		col21.setHalignment(HPos.RIGHT);
		col21.setMinWidth(minSirina);
		ColumnConstraints col22 = new ColumnConstraints();
		col22.setHgrow(Priority.ALWAYS);
		ColumnConstraints col23 = new ColumnConstraints();
		col23.setHalignment(HPos.RIGHT);
		col23.setMinWidth(minSirina);
		ColumnConstraints col24 = new ColumnConstraints();
		col24.setHgrow(Priority.ALWAYS);
		ColumnConstraints col25 = new ColumnConstraints();
		col25.setHalignment(HPos.RIGHT);
		col25.setMinWidth(minSirina);
		ColumnConstraints col26 = new ColumnConstraints();
		col26.setHgrow(Priority.ALWAYS);
		drugi.getColumnConstraints().addAll(col21, col22, col23, col24, col25, col26);
		
		prvi.addColumn(0, lblGrupaPartnera); prvi.addColumn(1, cbGrupe);
		prvi.addColumn(0, lblSifra); prvi.addColumn(1, txtSifra);
		prvi.addColumn(0, lblNaziv); prvi.addColumn(1, txtNaziv);
		prvi.addColumn(0, lblPunNaziv); prvi.addColumn(1, txtPunNaziv);
		prvi.addColumn(0, lblMesto); prvi.addColumn(1, cbMesto);
		prvi.addColumn(0, lblAdresa); prvi.addColumn(1, txtAdresa);
		prvi.addColumn(0, lblNapomena); prvi.addColumn(1, txtNapomena);
		
		drugi.addColumn(0, lblPdv); drugi.addColumn(1, cbPDV); drugi.addColumn(2, lblPib); drugi.addColumn(3, txtPib); 
		drugi.addColumn(4, lblMb); drugi.addColumn(5, txtMb);
		drugi.addColumn(0, lblKupac); drugi.addColumn(1, cbKupac); drugi.addColumn(2, lblKupacKonto); drugi.addColumn(3, txtKupacKonto); 
		drugi.addColumn(4, lblKupacRabat); drugi.addColumn(5, txtKupacRabat);
		drugi.addColumn(0, lblDobavljac); drugi.addColumn(1, cbDobavljac); drugi.addColumn(2, lblDobavljacKonto); drugi.addColumn(3, txtDobavljacKonto); 
		drugi.addColumn(4, lblDobavljacRabat); drugi.addColumn(5, txtDobavljacRabat);
		drugi.addColumn(0, lblTel); drugi.addColumn(1, txtTel); drugi.addColumn(2, lblEmail); drugi.addColumn(3, txtEmail); 
		drugi.addColumn(4, lblOdgovornoLice); drugi.addColumn(5, txtOdgovornoLice);
		drugi.addColumn(2, lblBanka); drugi.addColumn(3, cbBanka); drugi.addColumn(4, lblUstanova); drugi.addColumn(5, cbUstanova);
		
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
			txtKupacKonto.setText(String.valueOf(partner == null ? "" : partner.getKupacKonto()));
			txtKupacRabat.setText(String.valueOf(partner == null ? "" : partner.getKupacRabat()));
			
			cbDobavljac.setSelected(partner.getDobavljac());
			txtDobavljacKonto.setText(String.valueOf(partner == null ? "" : partner.getDobavljacKonto()));
			txtDobavljacRabat.setText(String.valueOf(partner == null ? "" : partner.getDobavljacRabat()));
			
			if(partnerPodaci.getSediste() != null) {
				sediste = partnerPodaci.getSediste();
				txtAdresa.setText(sediste.getAdresa());
				cbMesto.setValue(sediste.getMesto());
				}else {
					sediste = null;
					txtAdresa.setText("");
					cbMesto.setValue(null);
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
		txtKupacKonto.setText("");
		txtKupacRabat.setText("0,00");
		txtDobavljacKonto.setText("");
		txtDobavljacRabat.setText("0,00");
		cbMesto.setValue(null);
		txtAdresa.setText("");
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
			partner.setKupacKonto(null);
			partner.setDobavljac(cbDobavljac.isSelected());
			partner.setDobavljacKonto(null);
			try {
				partner.setKupacRabat(txtKupacRabat.vratiDecimalniBroj(txtKupacRabat.getText()));//new BigDecimal(txtKupacRabat.getText() == null ? "0.00" : txtKupacRabat.getText().trim())
				}catch (Exception e) {
					e.printStackTrace();
					partner.setKupacRabat(txtKupacRabat.vratiDecimalniBroj("0,00"));
					}
			try {
				partner.setDobavljacRabat(txtDobavljacRabat.vratiDecimalniBroj(txtDobavljacRabat.getText()));
				}catch (Exception e) {
					partner.setDobavljacRabat(txtDobavljacRabat.vratiDecimalniBroj("0,00"));//new BigDecimal(txtDobavljacRabat.getText() == null ? "0.00" : txtDobavljacRabat.getText().trim())
					}
			
			if(sediste == null) {
				sediste = new IAdresa();
				sediste.setPretplatnik(partner.getPretplatnik());
				sediste.setPartner(partner);
				}
			sediste.setMesto(cbMesto.getValue());
			sediste.setAdresa(txtAdresa.getText());

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
		if(txtSifra.getText() == null || txtSifra.getText().equals("")) {
			unos = false;
			}
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
	
	}
