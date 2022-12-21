package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class SimulationController {
    private final GridPane grid = new GridPane();
    private boolean isStarted;
    private Variants variants;
    private IMap map;
    private SimulationEngine engine;
    private int cellHeight = 50;
    private int cellWidth = 50;
    private Thread thread;

    @FXML
    private Button stateButton;

    @FXML
    private VBox test;

    @FXML
    public void initialize() {
        this.isStarted = true;
        this.variants = new Variants(5, 5, "HellMap", "lekka korekta",
                "nieco szaleństwa", 1, 2, 1
                , 4, 50, 5, 0,
                2, 5, 30);
        this.map = createMap(variants);
        this.engine = new SimulationEngine(map, variants, this);
        renderGridPane();
        test.getChildren().add(this.grid);

        this.thread = new Thread(this.engine);
        this.thread.start();
    }

    public  void renderGridPane() {
        this.grid.setGridLinesVisible(false);
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();
        this.grid.getChildren().clear();
        this.grid.setGridLinesVisible(true);

        int leftX = 0;
        int bottomY = 0;
        int rightX = this.map.getWidth()-1;
        int topY = this.map.getHeight()-1;

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
                String element = getObject(new Vector2d(leftX + i - 1, topY - j + 1));

                Label label = new Label(element);
                this.grid.add(label, i, j);
                GridPane.setHalignment(label, HPos.CENTER);


            }
        }
        System.out.println(map.getAnimals().toString());
    }

    @FXML
    protected void switchSimulationState() {

        if (this.isStarted) {

            stateButton.setText("Start");
            this.isStarted = false;

        } else {

            stateButton.setText("Stop");
            this.isStarted = true;

        }
    }

    private IMap createMap(Variants variants) {
        return switch (variants.getMapVariant()) {
            case EARTHMAP -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
            case HELLMAP -> new HellMap(variants.getWidth(), variants.getHeight(), variants);
            default -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        };
    }
    private String getObject(Vector2d currentPosition) {
        if (this.map.isOccupied(currentPosition)) {
            return String.valueOf(this.map.getNumberOfAnimals(currentPosition));
        }

        if (this.map.getPlants().containsKey(currentPosition)) {
            return "*";
        }
        return "";
    }

}
