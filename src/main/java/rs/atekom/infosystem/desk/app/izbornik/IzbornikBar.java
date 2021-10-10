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
		getMenus().addAll(podaciIzbornik, prodajaMeni, finansijeIzbornik, izvestajiIzbornik, sifarniciMeni, opste, krajIzbornik);
		}
	
	}
