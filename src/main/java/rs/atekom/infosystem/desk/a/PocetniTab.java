package rs.atekom.infosystem.desk.a;

import java.util.Locale;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.ChartType;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import eu.hansolo.tilesfx.tools.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class PocetniTab extends Tab{
	private ScrollPane root;
    //private static final Random RND = new Random();
    private static final double TILE_WIDTH  = 200;
    private static final double TILE_HEIGHT = 200;
    //private int noOfNodes = 0;
	private Tile procenat, sat, gaugeTile, sparkLine, areaChart, lineChartTile;
	
	public PocetniTab() {
		napravi();
		root = new ScrollPane();
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		//noOfNodes = RND.nextInt();
		//noOfNodes++;
		//setText("PREGLED " + noOfNodes); 
		FlowPane paneli = new FlowPane();
		paneli.setPadding(new Insets(5,5,5,5));
		paneli.setVgap(4);
		paneli.setHgap(4);
		//paneli.setStyle("-fx-background-color: DAE6F3;");
		paneli.getChildren().addAll(procenat, sat, gaugeTile, sparkLine, areaChart, lineChartTile, dodajBarChart(), dodajLineChart(), dodajPieChart());
		paneli.setOrientation(Orientation.HORIZONTAL);
		root.setContent(paneli);
		setContent(root);
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void napravi() {
		procenat = TileBuilder.create()
				.skinType(SkinType.PERCENTAGE)
				.prefSize(TILE_WIDTH, TILE_HEIGHT)
				.title("PROCENAT PRIKAZ")
				.unit(Helper.PERCENTAGE)
				.description("TEST")
				.maxValue(60)
				.build();
		
		sat = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("SAT")
                .text("PRIKAZ VREMENA")
                .dateVisible(true)
                .locale(Locale.US)
                .running(true)
                .build();
		
		gaugeTile =  TileBuilder.create()
                .skinType(SkinType.GAUGE)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("SKALA")
                .unit("V")
                .maxValue(240)
                .threshold(100)
                .build();
		
		gaugeTile.setValue(218.75);
		
        sparkLine = TileBuilder.create()
        		.skinType(SkinType.SPARK_LINE)
        		.prefSize(TILE_WIDTH, TILE_HEIGHT)
        		.title("PRODAJA U ODNOSU NA PLAN")
        		.unit("%")
        		.maxValue(300)
        		.gradientStops(new Stop(0, Tile.GREEN),
        				new Stop(0.5, Tile.YELLOW),
        				new Stop(1.0, Tile.RED))
        		.strokeWithGradient(true)
        		//.smoothing(true)
        		.build();
        sparkLine.setValue(121);
        
		XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.setName("PRODAJA PO DANIM AU HILJADAM DIN");
        series1.getData().add(new XYChart.Data("PO", 232));
        series1.getData().add(new XYChart.Data("UT", 210));
        series1.getData().add(new XYChart.Data("SR", 205));
        series1.getData().add(new XYChart.Data("ČE", 229));
        series1.getData().add(new XYChart.Data("PE", 241));
        series1.getData().add(new XYChart.Data("SU", 222));
        series1.getData().add(new XYChart.Data("NE", 20));
        
        areaChart = TileBuilder.create()
        		.skinType(SkinType.SMOOTHED_CHART)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title(series1.getName())
                .chartType(ChartType.AREA)
                //.animated(true)
                .smoothing(true)
                .tooltipTimeout(1000)
                .tilesFxSeries(new TilesFXSeries<>(series1, Tile.BLUE,
                                            new LinearGradient(0, 0, 0, 1,
                                                               true, CycleMethod.NO_CYCLE,
                                                               new Stop(0, Tile.BLUE),
                                                               new Stop(1, Color.TRANSPARENT))))
                .build();
        
        // LineChart Data
        XYChart.Series<String, Number> series2 = new XYChart.Series();
        series2.setName("UNUTRA");
        series2.getData().add(new XYChart.Data("PO", 8));
        series2.getData().add(new XYChart.Data("UT", 5));
        series2.getData().add(new XYChart.Data("SR", 0));
        series2.getData().add(new XYChart.Data("ČE", 2));
        series2.getData().add(new XYChart.Data("PE", 4));
        series2.getData().add(new XYChart.Data("SU", 3));
        series2.getData().add(new XYChart.Data("NE", 5));

        XYChart.Series<String, Number> series3 = new XYChart.Series();
        series3.setName("SPOLJA");
        series3.getData().add(new XYChart.Data("PO", 8));
        series3.getData().add(new XYChart.Data("UT", 5));
        series3.getData().add(new XYChart.Data("SR", 0));
        series3.getData().add(new XYChart.Data("ČE", 2));
        series3.getData().add(new XYChart.Data("PE", 4));
        series3.getData().add(new XYChart.Data("SU", 3));
        series3.getData().add(new XYChart.Data("NE", 5));
        
        lineChartTile = TileBuilder.create()
                .skinType(SkinType.SMOOTHED_CHART)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("SmoothedChart Tile")
                //.animated(true)
                .smoothing(false)
                .series(series2, series3)
                .build();
        }
	
	public void stop() {
		sat.setRunning(false);
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BarChart<CategoryAxis, NumberAxis> dodajBarChart() {
		CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("UREĐAJI");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("POSETE");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2020");

        dataSeries1.getData().add(new XYChart.Data("RAČUNARI", 567));
        dataSeries1.getData().add(new XYChart.Data("TELEFONI"  , 65));
        dataSeries1.getData().add(new XYChart.Data("TABLETI"  , 23));

        barChart.getData().add(dataSeries1);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("2019");

        dataSeries2.getData().add(new XYChart.Data("RAČUNARI", 540));
        dataSeries2.getData().add(new XYChart.Data("TELEFONI"  , 120));
        dataSeries2.getData().add(new XYChart.Data("TABLETI"  , 36));

        barChart.getData().add(dataSeries2);

        return barChart;
        }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LineChart<NumberAxis, NumberAxis> dodajLineChart(){
		NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("BROJ ZAPOSLENIH");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("PRIHOD PO ZAPESLONOM");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2019");

        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);

        return lineChart;
        }
	
	public PieChart dodajPieChart() {
        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("RAČUNARI", 213);
        PieChart.Data slice2 = new PieChart.Data("TELEFONI"  , 67);
        PieChart.Data slice3 = new PieChart.Data("TABLETI" , 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        return pieChart;
        }
	
	}
