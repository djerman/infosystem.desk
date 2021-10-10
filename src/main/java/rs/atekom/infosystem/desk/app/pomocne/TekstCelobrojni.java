package rs.atekom.infosystem.desk.app.pomocne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class TekstCelobrojni extends TextField{

	public TekstCelobrojni() {
		textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}?")) {
                    setText(oldValue);
                    }
                }
            });
		setAlignment(Pos.CENTER_RIGHT);
		}
	
	public TekstCelobrojni(String v) {
		super(v);
		textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}?")) {
                    setText(oldValue);
                    }
                }
            });
		setAlignment(Pos.CENTER_RIGHT);
		}
	}
