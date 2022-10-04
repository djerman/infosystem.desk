package rs.atekom.infosystem.desk.a;

import java.util.ResourceBundle;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class OsnovniPregled extends HBox{
	
	public OsnovniGrid grid, gridPrevod, gridKnjigovodstvo;
	public TabPane tabPane;
	public Tab tabPodaci, tabPrevod, tabKnjigovodstvo;
	private HBox podaci, prevod, knjigovodstvo;
	public GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
	
	public OsnovniPregled() {
		postaviiPodesavanja(this);
		}
	
	public void napraviGrid() {
		grid = new OsnovniGrid();
		}
	
	public void napraviTabove(ResourceBundle resource) {
		ukloniPodesavanja(this);
		
		napraviGrid();
		napraviBoxove();
		
		tabPane = new TabPane();
		tabPane.getStyleClass().add("desni-tab");

		gridPrevod = new OsnovniGrid();
		
		gridKnjigovodstvo = new OsnovniGrid();

		podaci.getChildren().add(grid);
		tabPodaci = new Tab("", podaci);
		tabPodaci.setGraphic(fontAwesome.create(FontAwesome.Glyph.DATABASE).color(Color.BLACK));
		tabPodaci.setClosable(false);
		tabPodaci.setTooltip(new Tooltip(resource.getString("lbl.tabpodaci")));
		
		knjigovodstvo.getChildren().add(gridKnjigovodstvo);
		tabKnjigovodstvo = new Tab("", knjigovodstvo);
		tabKnjigovodstvo.setGraphic(fontAwesome.create(FontAwesome.Glyph.CALCULATOR).color(Color.BLACK));
		tabKnjigovodstvo.setTooltip(new Tooltip(resource.getString("lbl.tabknigovodstvo")));
		tabKnjigovodstvo.setClosable(false);
		
		prevod.getChildren().add(gridPrevod);
		tabPrevod = new Tab("", prevod);
		tabPrevod.setGraphic(fontAwesome.create(FontAwesome.Glyph.PENCIL).color(Color.BLACK));
		tabPrevod.setTooltip(new Tooltip(resource.getString("lbl.tabprevod")));
		tabPrevod.setClosable(false);
		
		tabPane.getTabs().addAll(tabPodaci, tabKnjigovodstvo, tabPrevod);
		
		tabPane.setSide(Side.RIGHT);   
		HBox.setHgrow(grid, Priority.ALWAYS);
		HBox.setHgrow(gridKnjigovodstvo, Priority.ALWAYS);
		HBox.setHgrow(gridPrevod, Priority.ALWAYS);
	}
	
	public void setPodaci() {
		
	}
	
	public void napraviBoxove() {
		podaci = new HBox();
		postaviiPodesavanja(podaci);
		
		prevod = new HBox();
		postaviiPodesavanja(prevod);
		
		knjigovodstvo = new HBox();
		postaviiPodesavanja(knjigovodstvo);
	}
	
	private void postaviiPodesavanja(HBox hbox) {
		hbox.getStyleClass().add("pregled-sa-pozadinom");
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(3));
	}
	
	private void ukloniPodesavanja(HBox hbox) {
		getStyleClass().remove("pregled-sa-pozadinom");
		setSpacing(0);
		setPadding(new Insets(0));
	}
	
	}
