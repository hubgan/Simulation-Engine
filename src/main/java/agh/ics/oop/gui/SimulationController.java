package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
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
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    private Animal targetedAnimal;

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
    private VBox targetedVBox;
    @FXML
    private Button stopObserving;

    @FXML
    protected void clearTargetedAnimal() {
        targetedAnimal.changeTargeted();
        this.targetedAnimal = null;
        stopObserving.setVisible(false);
        clearTargetedVBox();
        renderGridPane();
    }


    public void addTargetedVBox() {
        clearTargetedVBox();
        List<Label> list = new ArrayList<>();
        list.add(new Label("Energy: " + targetedAnimal.getEnergy()));
        list.add(new Label("Genom: " + Arrays.toString(targetedAnimal.getGenotype()).replace(",", "").replace("[", "").replace("]", "").trim()));
        list.add(new Label("Active genom: " + targetedAnimal.getGenotype()[targetedAnimal.getCurrentGenIndex()]));
        list.add(new Label("Eaten plants: " + targetedAnimal.getEatenPlants()));
        list.add(new Label("Children: " + targetedAnimal.getKids()));
        list.add(new Label("Days lived: " + targetedAnimal.getOld()));
        if (targetedAnimal.getDeadDay() != 0) {
            list.add(new Label("Day when died: " + targetedAnimal.getDeadDay()));
        }
        targetedVBox.getChildren().addAll(list);
    }

    public void clearTargetedVBox() {
        targetedVBox.getChildren().clear();
    }

    @FXML
    public void initialize(Variants variants) {
        stopObserving.setVisible(false);
        this.isStarted = true;
        this.variants = variants;
        this.map = createMap(this.variants);
        this.engine = new SimulationEngine(this.map, this.variants, this);
        this.statistics = new Statistics(this.map, this.engine);
        renderGridPane();
        initMapSize();
        renderChart();
        updateChart();
        this.container.getChildren().add(this.grid);
        this.chart.getChildren().add(this.lineChart);
        this.thread = new Thread(this.engine);
        this.thread.start();
    }

    private void initMapSize() {
        this.cellWidth = 400.0 / (this.map.getWidth() - 1);
        this.cellHeight = 400.0 / (this.map.getHeight() - 1);
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

        lineChart.getData().add(this.numberOfAnimalsSeries);
        lineChart.getData().add(this.numberOfPlantsSeries);
        lineChart.getData().add(this.numberOfFreeFieldsSeries);
        lineChart.getData().add(this.numberOfAverageEnergySeries);
        lineChart.getData().add(this.numberOfAverageLifeTimeSeries);

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

        for (int i = 0; i < rightX - leftX + 1; i++)
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.cellWidth));

        for (int i = 0; i < topY - bottomY + 1; i++)
            this.grid.getRowConstraints().add(new RowConstraints(this.cellHeight));

        for (int i = 0; i < rightX - leftX + 1; i++) {
            for (int j = 0; j < topY - bottomY + 1; j++) {
                Node element = getNode(new Vector2d(leftX + i, topY - j));
                if (element != null) {
                    this.grid.add(element, i, j);
                    //GridPane.setHalignment(element, HPos.CENTER);
                }
            }
        }
        if (targetedAnimal != null) {
            System.out.println(targetedAnimal.getPosition());
            addTargetedVBox();
        }
    }
    public void stopSimulation() {
        this.isStarted = false;

    }
    public Boolean getIsStarted() {
        return this.isStarted;
    }

    @FXML
    protected void switchSimulationState() {
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

    private Shape getNode(Vector2d currentPosition) {
        if (this.map.isOccupied(currentPosition)) {
            Rectangle rectangle;
            Animal animal = this.map.getAnimals().get(currentPosition).get(0);

            Color color = getColor(animal.getEnergy());
            rectangle = new Rectangle(this.cellWidth, this.cellHeight, color);

            if (animal.getTargeted()) {
                rectangle.setFill(Color.BLUE);
            }


            rectangle.setOnMousePressed(event -> {
                if (!isStarted && targetedAnimal == null) {
                    animal.changeTargeted();
                    targetedAnimal = animal;
                    addTargetedVBox();
                    stopObserving.setVisible(true);

                    renderGridPane();

                } else if (!isStarted && animal.getTargeted()) {
                    animal.changeTargeted();
                    targetedAnimal = null;
                    clearTargetedVBox();
                    stopObserving.setVisible(false);
                    renderGridPane();
                }
            });
            return rectangle;
        }

        if (this.map.getPlants().containsKey(currentPosition)) {
            return new Circle(this.cellHeight/2, Color.GREEN);
        }
        return null;
    }

    private Color getColor(int value) {
        int calculatePercentage = value * 100 / variants.getAnimalStartingEnergy();

        if (calculatePercentage >= 184) {
            return Color.rgb(0, 177, 0);
        }
        else if (calculatePercentage >= 172) {
            return Color.rgb(0, 203, 0);
        }
        else if (calculatePercentage >= 160) {
            return Color.rgb(0, 226, 0);
        }
        else if (calculatePercentage >= 148) {
            return Color.rgb(0, 241, 0);
        }
        else if (calculatePercentage >= 136) {
            return Color.rgb(8, 255, 0);
        }
        else if (calculatePercentage >= 122) {
            return Color.rgb(165, 125, 0);
        }
        else if (calculatePercentage >= 110) {
            return Color.rgb(204, 159, 19);
        }
        else if (calculatePercentage >= 96) {
            return Color.rgb(236, 189, 44);
        }
        else if (calculatePercentage >= 84) {
            return Color.rgb(249, 198, 44);
        }
        else if (calculatePercentage >= 72) {
            return Color.rgb(249, 216, 137);
        }
        else if (calculatePercentage >= 60) {
            return Color.rgb(168,28,28);
        }
        else if (calculatePercentage >= 48) {
            return Color.rgb(217,35,35);
        }
        else if (calculatePercentage >= 36) {
            return Color.rgb(191,28,28);
        }
        else if (calculatePercentage >= 24) {
            return Color.rgb(178,30,30);
        }
        else {
            return Color.rgb(168,28,28);
        }



//        int MIN = 0;
//        int MAX = this.variants.getAnimalStartingEnergy();
//
//        double hue = Color.BLUE.getHue() + (Color.GREEN.getHue() - Color.BLUE.getHue()) * (value - MIN) / (MAX - MIN);
//        return Color.hsb(hue, 1.0, 1.0);
    }

}
