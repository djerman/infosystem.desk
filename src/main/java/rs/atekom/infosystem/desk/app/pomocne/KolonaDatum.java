package rs.atekom.infosystem.desk.app.pomocne;

import java.text.SimpleDateFormat;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class KolonaDatum<S, T> extends TableColumn<S, T> {

	private T t;
	private S s;
	private SimpleDateFormat format;
	
	public KolonaDatum(String naziv, String polje) {
		format = new SimpleDateFormat("dd.MM.yyyy");
		setText(naziv);
		setCellValueFactory(new PropertyValueFactory<>(polje));
		setCellFactory(col -> {
			TableCell<S, T> cell = new TableCell<S, T>(){
				@Override
				protected void updateItem(T item, boolean empty) {
					super.updateItem(item, empty);
					if(empty) {
						setText(null);
					}else {
						if(item != null) {
							setText(format.format(item));
						}else {
							setText(null);
						}
					}
				}
			};
			return cell;
		});
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

	public SimpleDateFormat getFormat() {
		return format;
	}

	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	
}
