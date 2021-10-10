package rs.atekom.infosystem.desk.app.pomocne;

import javafx.scene.control.TextField;

public class TekstPoljeSaDimenzijama extends TextField{

	public TekstPoljeSaDimenzijama() {
		setMinWidth(Dimenzije.MIN_SIRINA);
		setMaxWidth(Dimenzije.MAX_SIRINA);
		}
	
	public TekstPoljeSaDimenzijama(Boolean forma) {
		if(forma) {
			setMinWidth(Dimenzije.FORMA_MIN_SIRINA);
			setMaxWidth(Dimenzije.FORMA_MAX_SIRINA);
			}else {
				setMinWidth(Dimenzije.MIN_SIRINA);
				setMaxWidth(Dimenzije.MAX_SIRINA);
				}
		}
	
	}
