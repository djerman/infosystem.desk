package rs.atekom.infosystem.desk.a;

import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class ComboSaBrisanjem extends HBox{
	
	private Button ocisti;
	
	public <T> ComboSaBrisanjem(SearchableComboBox<T> combo) {
		ocisti = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.CLOSE).color(Color.RED));
		ocisti.getStyleClass().clear();
		ocisti.setMaxWidth(20.0);
		ocisti.setMinWidth(20.0);
		ocisti.setPadding(new Insets(2));
		setSpacing(5.0);
		getChildren().addAll(combo, ocisti);
		HBox.setHgrow(combo, Priority.ALWAYS);
		ocisti.setOnAction(e -> {
			combo.getSelectionModel().clearSelection();
			combo.setValue(null);
		});
	}
	
	
	
}
