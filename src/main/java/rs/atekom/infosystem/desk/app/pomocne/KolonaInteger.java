package rs.atekom.infosystem.desk.app.pomocne;

import javafx.scene.control.TableColumn;

public class KolonaInteger <S, T> extends TableColumn<S, T>{
	
	private T t;
	private S s;
	
	public KolonaInteger(String naziv) {
		setText(naziv);;
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
