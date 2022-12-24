package agh.ics.oop.gui;


import agh.ics.oop.Configuration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

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
    @FXML
    private ComboBox<String> configurationComboBox;
    @FXML
    private Button configButton;

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

    private void getValues(Map<String, String> configuration, ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                configuration.put(node.getId(), ((TextField) node).getText().replaceFirst("^0+(?!$)", ""));
            }
            if (node instanceof ChoiceBox<?>) {
                configuration.put(node.getId(), ((ChoiceBox<?>) node).getValue().toString());
            }
        }
    }

    private Map<String, String> getAllValues() {
        LinkedHashMap<String, String> configuration = new LinkedHashMap<>();

        getValues(configuration, leftTopGrid.getChildren());
        getValues(configuration, leftBottomGrid.getChildren());
        getValues(configuration, rightTopGrid.getChildren());
        getValues(configuration, rightBottomGrid.getChildren());
        return configuration;
    }

    private void setAllValues(String name) {
        Configuration config = new Configuration(name);
        Map<String, String> configuration = config.readConfiguration();
        setValues(configuration, leftTopGrid.getChildren());
        setValues(configuration, leftBottomGrid.getChildren());
        setValues(configuration, rightTopGrid.getChildren());
        setValues(configuration, rightBottomGrid.getChildren());
        plantsVariant.setValue(configuration.get(plantsVariant.getId()));
        mapVariant.setValue(configuration.get(mapVariant.getId()));
        mutationVariant.setValue(configuration.get(mutationVariant.getId()));
        animalsVariant.setValue(configuration.get(animalsVariant.getId()));

    }

    private void setValues(Map<String, String> configuration, ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                ((TextField) node).setText(configuration.get(node.getId()));
            }
        }
    }
    private void clearValues(ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                ((TextField) node).setText("");
            }
        }
    }
    private void clearAllValues() {
        clearValues(leftTopGrid.getChildren());
        clearValues(leftBottomGrid.getChildren());
        clearValues(rightTopGrid.getChildren());
        clearValues(rightBottomGrid.getChildren());

        plantsVariant.setValue("forested equatorial");
        mapVariant.setValue("earth globe");
        mutationVariant.setValue("full randomness");
        animalsVariant.setValue("full predestination");
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

        configurationComboBox.getItems().add("no configuration");
        configurationComboBox.setValue("no configuration");
        configurationComboBox.getItems().addAll(Configuration.getAllNames());

        configurationComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (configurationComboBox.getValue().equals("no configuration")) {
                clearAllValues();
                configurationName.setVisible(true);
                configButton.setText("Creat config");
            } else {
                setAllValues(configurationComboBox.getValue());
                configurationName.setVisible(false);
                configButton.setText("Edit config");
            }
        });
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
