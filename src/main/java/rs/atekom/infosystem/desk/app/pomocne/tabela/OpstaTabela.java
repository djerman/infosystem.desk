package rs.atekom.infosystem.desk.app.pomocne.tabela;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public abstract class OpstaTabela<T> extends TableView<T> implements OpstaTabelaInterface{
	
	private T t;
	public SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	public ResourceBundle resource;

	public OpstaTabela(ResourceBundle resource) {
		super();
		this.resource = resource;
		setMaxWidth(Double.MAX_VALUE);
		setPlaceholder(new Label(resource.getString("lbl.nemapodataka")));
		postaviTabelu();
		}
	
	public void set(T t) {
		this.t = t;
		}
	
	public T get() {
		return t;
		}
	
	public void resize() {
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		refresh();
		}
	
	public static void postaviSirinuKolona(TableView<?> tabela) {
	    tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	    tabela.refresh();
	    }

	public static void autoResizeColumns(TableView<?> table){
	    //Set the right policy
	    table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	    table.getColumns().stream().forEach((column) -> {
	        //Minimal width = columnheader
	        Text t = new Text( column.getText() );
	        double max = t.getLayoutBounds().getWidth();
	        for(int i = 0; i < table.getItems().size(); i++){
	            //cell must not be empty
	            if( column.getCellData( i ) != null ){
	            	t = new Text( column.getCellData( i ).toString() );
	                double calcwidth = t.getLayoutBounds().getWidth();
	                //remember new max-width
	                if(calcwidth > max ){
	                	max = calcwidth;
	                	}
	                }
	            }
	        //set the new max-widht with some extra space
	        column.setPrefWidth( max + 10.0d );
	        });
	    table.refresh();
	    }
	
	}
