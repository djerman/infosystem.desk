package rs.atekom.infosystem.desk.app.pomocne;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class KolonaDecimalna<S, T> extends TableColumn<S, T>{

	private T t;
	private S s;
	
	public KolonaDecimalna(String naziv, String polje) {
		setText(naziv);
		PropertyValueFactory<S, T> valueFactory = new PropertyValueFactory<>(polje);
		//setCellValueFactory(new PropertyValueFactory<S, T>(polje));
		setCellValueFactory(valueFactory);
		setStyle( "-fx-alignment: CENTER-RIGHT;");
		}

	public T getT() {
		return t;
		}

	public void setT(T t) {
		this.t = t;
		}

	public S getS() {
		return s;
		}

	public void setS(S s) {
		this.s = s;
		}
	}
