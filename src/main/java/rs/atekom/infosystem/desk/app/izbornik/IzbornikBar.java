package rs.atekom.infosystem.desk.app.izbornik;

import javafx.scene.control.MenuBar;
import rs.atekom.infosystem.desk.a.OsnovniLayout;

public class IzbornikBar extends MenuBar{
	
	private OsnovniLayout osnovniLayout;

	public IzbornikBar(OsnovniLayout osnovniLayout) {
		this.osnovniLayout = osnovniLayout;
		postaviNоviMeni();
		}
	
	private void postaviNоviMeni() {
		AdministracijaIzbornik administracijaIzbornik = new AdministracijaIzbornik(osnovniLayout);
		PodaciIzbornik podaciIzbornik = new PodaciIzbornik(osnovniLayout);
		RobaIzbornik prodajaMeni = new RobaIzbornik(osnovniLayout);
		//NabavkaIzbornik nabavkaMeni = new NabavkaIzbornik(osnovniLayout);
		//SkladistaIzbornik magacinMeni = new SkladistaIzbornik(osnovniLayout);
		FinansijeIzbornik finansijeIzbornik = new FinansijeIzbornik(osnovniLayout);
		PreglediIzbornik izvestajiIzbornik = new PreglediIzbornik(osnovniLayout);
		ServisIzbornik sifarniciMeni = new ServisIzbornik(osnovniLayout);
		KrajIzbornik krajIzbornik = new KrajIzbornik(osnovniLayout);
		//SistemIzbornik sistemMeni = new SistemIzbornik(osnovniLayout);
		OpsteIzbornik opste = new OpsteIzbornik(osnovniLayout);
		
		switch (osnovniLayout.vratiKorisnika().getUloga().getSr()) {
		case "SISTEM": 
			getMenus().addAll(administracijaIzbornik, sifarniciMeni, opste, krajIzbornik);
			break;
		case "AGENCIJA" :
			getMenus().addAll(podaciIzbornik, prodajaMeni, finansijeIzbornik, izvestajiIzbornik, administracijaIzbornik, krajIzbornik);
			break;
		case "ADMINISTRATOR": 
			getMenus().addAll(podaciIzbornik, prodajaMeni, finansijeIzbornik, izvestajiIzbornik, administracijaIzbornik, krajIzbornik);
			break;
		case "KORISNIK": 
			getMenus().addAll(podaciIzbornik, prodajaMeni, finansijeIzbornik, izvestajiIzbornik, krajIzbornik);
			break;
		default:
			//getMenus().addAll(podaciIzbornik, prodajaMeni, finansijeIzbornik, izvestajiIzbornik, administracijaIzbornik, sifarniciMeni, opste, krajIzbornik);
			throw new IllegalArgumentException("Unexpected value: " + osnovniLayout.vratiKorisnika().getUloga().getSr());
			}
		
		}
	
	}
