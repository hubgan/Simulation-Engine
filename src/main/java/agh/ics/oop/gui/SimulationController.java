package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class SimulationController {
    private final GridPane grid = new GridPane();
    private boolean isStarted;
    private Variants variants;
    private IMap map;
    private SimulationEngine engine;
    private Statistics statistics;
    private double cellHeight = 50;
    private double cellWidth = 50;
    private Thread thread;
    private Variants test;

    // charts
    private LineChart<Number, Number> lineChart;
    final int CHART_SIZE = 10;
    XYChart.Series<Number, Number> numberOfAnimalsSeries = new XYChart.Series<>();
    XYChart.Series<Number, Number> numberOfPlantsSeries = new XYChart.Series<>();
    XYChart.Series<Number, Number> numberOfFreeFieldsSeries = new XYChart.Series<>();
    XYChart.Series<Number, Number> numberOfAverageEnergySeries = new XYChart.Series<>();
    XYChart.Series<Number, Number> numberOfAverageLifeTimeSeries = new XYChart.Series<>();

    @FXML
    private Button stateButton;

    @FXML
    private VBox container;

    @FXML
    private VBox chart;

    @FXML
    public void initialize(Variants test) {
        this.isStarted = true;
        this.variants = test;
        this.map = createMap(variants);
        this.engine = new SimulationEngine(this.map, this.variants, this);
        this.statistics = new Statistics(this.map, this.engine);
        renderGridPane();
//        initMapSize();
        renderChart();
        updateChart();
        this.container.getChildren().add(this.grid);
        this.chart.getChildren().add(this.lineChart);
        System.out.println(this.test);
        this.thread = new Thread(this.engine);
        this.thread.start();
    }

    private void initMapSize() {
        this.cellWidth = this.container.getPrefWidth() / (this.map.getWidth() - 1);
        this.cellHeight = this.container.getPrefHeight() / (this.map.getHeight() - 1);
    }

    private void renderChart() {
        // Defining axis
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Days");
        xAxis.setAnimated(false);
        // Show only last 10 values on chart
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(-1);
        xAxis.setUpperBound(10);
        xAxis.setTickUnit(1);

        yAxis.setLabel("Quantity");
        yAxis.setAnimated(false);

        // Creating line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistics");
        lineChart.setAnimated(false);

        // Defining series to display data
        this.numberOfAnimalsSeries.setName("Number of Animals");
        this.numberOfPlantsSeries.setName("Number of Plants");
        this.numberOfFreeFieldsSeries.setName("Number of Free Fields");
        this.numberOfAverageEnergySeries.setName("Average Energy of Living Animals");
        this.numberOfAverageLifeTimeSeries.setName("Average Life Time of Dead Animals");

//        lineChart.getData().add(this.numberOfAnimalsSeries);
//        lineChart.getData().add(this.numberOfPlantsSeries);
//        lineChart.getData().add(this.numberOfFreeFieldsSeries);
//        lineChart.getData().add(this.numberOfAverageEnergySeries);
//        lineChart.getData().add(this.numberOfAverageLifeTimeSeries);
        lineChart.getData().addAll(
          this.numberOfAnimalsSeries,
          this.numberOfPlantsSeries,
          this.numberOfFreeFieldsSeries,
          this.numberOfAverageEnergySeries,
          this.numberOfAverageLifeTimeSeries
        );

        this.lineChart = lineChart;
    }

    public void updateChart() {
        int simulationTime = this.engine.getSimulationTime();
        int numberOfAnimals = this.statistics.getNumberOfAnimals();
        int numberOfPlants = this.statistics.getNumberOfPlants();
        int numberOfFreeFields = this.statistics.getNumberOfFreeFields();
        float averageEnergy = this.statistics.getAverageEnergy();
        float averageLifeTime = this.statistics.getAverageLifeTime();
        this.numberOfAnimalsSeries.getData().add(new XYChart.Data<>(simulationTime, numberOfAnimals));
        this.numberOfPlantsSeries.getData().add(new XYChart.Data<>(simulationTime, numberOfPlants));
        this.numberOfFreeFieldsSeries.getData().add(new XYChart.Data<>(simulationTime, numberOfFreeFields));
        this.numberOfAverageEnergySeries.getData().add(new XYChart.Data<>(simulationTime, averageEnergy));
        this.numberOfAverageLifeTimeSeries.getData().add(new XYChart.Data<>(simulationTime, averageLifeTime));

        if (this.numberOfAnimalsSeries.getData().size() > CHART_SIZE) {
            // Show only last 10 values on chart
            NumberAxis xAxis = ((NumberAxis) this.lineChart.getXAxis());
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);

            this.numberOfAnimalsSeries.getData().remove(0);
            this.numberOfPlantsSeries.getData().remove(0);
            this.numberOfFreeFieldsSeries.getData().remove(0);
            this.numberOfAverageEnergySeries.getData().remove(0);
            this.numberOfAverageLifeTimeSeries.getData().remove(0);
        }
    }

    public void renderGridPane() {
        this.grid.setGridLinesVisible(false);
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();
        this.grid.getChildren().clear();
        this.grid.setGridLinesVisible(true);

        int leftX = 0;
        int bottomY = 0;
        int rightX = this.map.getWidth() - 1;
        int topY = this.map.getHeight() - 1;

        Label yxLabel = new Label("y/x");
        this.grid.add(yxLabel, 0, 0, 1, 1);
        this.grid.getColumnConstraints().add(new ColumnConstraints(this.cellWidth));
        this.grid.getRowConstraints().add(new RowConstraints(this.cellHeight));
        GridPane.setHalignment(yxLabel, HPos.CENTER);

        for (int i = 1; i < rightX - leftX + 2; i++) {
            int value = leftX + i - 1;
            Label label = new Label(value + "");
            this.grid.add(label, i, 0, 1, 1);
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < topY - bottomY + 2; i++) {
            int value = topY - i + 1;
            Label label = new Label(value + "");
            this.grid.add(label, 0, i, 1, 1);
            this.grid.getRowConstraints().add(new RowConstraints(this.cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < rightX - leftX + 2; i++) {
            for (int j = 1; j < topY - bottomY + 2; j++) {
                Node element = getNode(new Vector2d(leftX + i - 1, topY - j + 1));
                if (element != null) {
                    this.grid.add(element, i, j);
                    GridPane.setHalignment(element, HPos.CENTER);
                }

            }
        }
    }

    @FXML
    protected void switchSimulationState() {
        this.engine.changeThreadState();
        if (this.isStarted) {
            stateButton.setText("Start");
        } else {
            stateButton.setText("Stop");
            this.thread = new Thread(this.engine);
            this.thread.start();
        }
        this.isStarted = !this.isStarted;
    }

    private IMap createMap(Variants variants) {
        return switch (variants.getMapVariant()) {
            case EARTHMAP -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
            case HELLMAP -> new HellMap(variants.getWidth(), variants.getHeight(), variants);
        };
    }

    private Node getNode(Vector2d currentPosition) {
        if (this.map.isOccupied(currentPosition)) {
            StackPane stackPane = new StackPane();
            Label label = new Label(String.valueOf(this.map.getNumberOfAnimals(currentPosition)));
            Color color = getColor(this.map.getAnimals().get(currentPosition).get(0).getEnergy());
            Rectangle rectangle = new Rectangle(20, 20, color);

            label.setTextFill(Color.WHITE);
            stackPane.getChildren().addAll(rectangle, label);
            return stackPane;
        }

        if (this.map.getPlants().containsKey(currentPosition)) {
            return new Circle(10, Color.GREEN);
        }
        return null;
    }

    private Color getColor(int value) {
        int MIN = 0;
        int MAX = this.variants.getAnimalStartingEnergy();

        double hue = Color.BLUE.getHue() + (Color.GREEN.getHue() - Color.BLUE.getHue()) * (value - MIN) / (MAX - MIN);
        return Color.hsb(hue, 1.0, 1.0);
    }

}
