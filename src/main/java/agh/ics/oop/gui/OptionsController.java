package agh.ics.oop.gui;


import agh.ics.oop.Configuration;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

import java.io.IOException;

public class OptionsController {
    @FXML
    private GridPane leftTopGrid;
    @FXML
    private GridPane leftBottomGrid;
    @FXML
    private GridPane rightTopGrid;
    @FXML
    private GridPane rightBottomGrid;
    @FXML
    private TextField configurationName;
    @FXML
    private ChoiceBox<String> plantsVariant;
    @FXML
    private ChoiceBox<String> mapVariant;
    @FXML
    private ChoiceBox<String> animalsVariant;
    @FXML
    private ChoiceBox<String> mutationVariant;
    UnaryOperator<Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("(^[0-9]*$)")) {
            return change;
        }
        return null;
    };

    @FXML
    private void setIntegerFilter(ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                ((TextField) node).setTextFormatter(new TextFormatter<>(integerFilter));
            }
        }
    }

    private void getValues(LinkedHashMap<String, String> configuration, ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                configuration.put(node.getId(), ((TextField) node).getText().replaceFirst("^0+(?!$)", ""));
            }
            if (node instanceof ChoiceBox<?>) {
                configuration.put(node.getId(), ((ChoiceBox<?>) node).getValue().toString());
            }
        }
    }

    private LinkedHashMap<String, String> getAllValues() {
        LinkedHashMap<String, String> configuration = new LinkedHashMap<>();

        getValues(configuration, leftTopGrid.getChildren());
        getValues(configuration, leftBottomGrid.getChildren());
        getValues(configuration, rightTopGrid.getChildren());
        getValues(configuration, rightBottomGrid.getChildren());
        return configuration;
    }

    public void initialize() {
        setIntegerFilter(leftTopGrid.getChildren());
        setIntegerFilter(leftBottomGrid.getChildren());
        setIntegerFilter(rightTopGrid.getChildren());
        setIntegerFilter(rightBottomGrid.getChildren());
        plantsVariant.getItems().addAll("forested equatorial", "toxic corpses");
        plantsVariant.setValue("forested equatorial");

        mapVariant.getItems().addAll("earth globe", "infernal portal");
        mapVariant.setValue("earth globe");

        mutationVariant.getItems().addAll("full randomness", "slight correction");
        mutationVariant.setValue("full randomness");

        animalsVariant.getItems().addAll("full predestination", "bit of madness");
        animalsVariant.setValue("full predestination");



    }

    @FXML
    protected void startSimulation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OptionsApplication.class.getResource("/simulation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void createConfiguration() {
        Configuration configuration = new Configuration(configurationName.getText());
        configuration.writeConfiguration(getAllValues());
    }
}
