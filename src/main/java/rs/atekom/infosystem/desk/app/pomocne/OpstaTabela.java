package rs.atekom.infosystem.desk.app.pomocne;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class OpstaTabela<T> extends TableView<T>{
	
	private T t;
	public SimpleDateFormat format;
	public ResourceBundle resource;
	//private ScrollBar scrollBar;

	public OpstaTabela(ResourceBundle resource) {
		//scrollBar = new ScrollBar();
		format = new SimpleDateFormat("dd.MM.yyyy");
		this.resource = resource;
		//getStyleClass().add("opstatabela");
		setMaxWidth(Double.MAX_VALUE);
		setPlaceholder(new Label(resource.getString("lbl.nemapodataka")));
		//setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		//setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//postaviSirinuKolona();
		/*
		getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> 
		Platform.runLater(() -> {
	        TableViewSkin<?> ts = (TableViewSkin<?>) getSkin();
	        VirtualFlow<?> vf = (VirtualFlow<?>)ts.getChildren().get(1);

	        int first = vf.getFirstVisibleCell().getIndex();
	        int last = vf.getLastVisibleCell().getIndex();

	        if((newValue.intValue() - ((last - first) / 2)) >= 0) {
	            vf.scrollTo(newValue.intValue() - ((last - first) / 2));
	            getSelectionModel().select(newValue.intValue() - ((last - first) / 2));
	            System.out.println("ovde sam sa scrolom....");
	            }
	        }));**/
		
		/*scrollBar.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
		    @Override
		    public void handle(ScrollEvent event) {
		        ScrollBar tableViewScrolllBar = (ScrollBar) lookup(".scroll-bar:vertical");
		        tableViewScrolllBar.setValue(scrollBar.getValue() / scrollBar.getMax());
		        System.out.println("ovde sam sa scrolom....");
		        }
		    });*/
		}
	
	public void postaviTabelu() {
		//
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
		//TabelaSirinaKolona.autoFitTable(tabela);
	    //Set the right policy
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
