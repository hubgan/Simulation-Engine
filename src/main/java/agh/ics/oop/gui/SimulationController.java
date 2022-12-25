package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
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
    private final int cellHeight = 50;
    private final int cellWidth = 50;
    private Thread thread;
    private Variants test;

    @FXML
    private Button stateButton;

    @FXML
    private VBox container;

    @FXML
    public void initialize(Variants test) {

        this.isStarted = true;
        this.variants = test;
        this.map = createMap(variants);
        this.engine = new SimulationEngine(map, variants, this);
        renderGridPane();
        container.getChildren().add(this.grid);
        System.out.println(this.test);
        this.thread = new Thread(this.engine);
        this.thread.start();
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
