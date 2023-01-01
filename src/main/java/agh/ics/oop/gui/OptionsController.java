package agh.ics.oop.gui;


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
    @FXML
    private Label configurationLabel;
    @FXML
    private Label warning;
    @FXML
    private CheckBox checkBoxCSV;
    @FXML
    private Label labelCSV;
    @FXML
    private TextField textFieldCSV;

    private final UnaryOperator<Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("(^[0-9]*$)")) {
            return change;
        }
        return null;
    };

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
            warning.setText("");
            if (newValue.equals("no configuration")) {
                clearAllValues();
                configurationLabel.setVisible(true);
                configurationName.setVisible(true);
                configButton.setText("Create config");
            } else {
                setAllValues(newValue);
                configurationLabel.setVisible(false);
                configurationName.setVisible(false);
                configurationName.setText(newValue);
                configButton.setText("Edit config");
            }
        });

        checkBoxCSV.setSelected(true);
        checkBoxCSV.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                textFieldCSV.setText("");
            }
            labelCSV.setVisible(newValue);
            textFieldCSV.setVisible(newValue);
        });
    }

    private Boolean checkIfAllFiled() {
        for (String value : getAllValues().values()) {
            if (value.equals("")) return false;
        }
        return true;
    }

    @FXML
    protected void startSimulation() throws IOException {
        if (checkIfAllFiled()) {
            warning.setText("");

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/simulation-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> {
                stage.close();
                ((SimulationController) fxmlLoader.getController()).stopSimulation();

            });
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.setMaximized(true);
            ((SimulationController) fxmlLoader.getController()).initialize(new Variants(getAllValues()), checkBoxCSV.isSelected(), textFieldCSV.getText());
            stage.show();
        } else {
            warning.setText("All values must be filled!");
        }


    }

    @FXML
    protected void createConfiguration() {
        Configuration configuration = new Configuration(configurationName.getText());
        configuration.writeConfiguration(getAllValues());
    }

    //Set methods
    @FXML
    private void setIntegerFilter(ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                ((TextField) node).setTextFormatter(new TextFormatter<>(integerFilter));
            }
        }
    }

    private void setValues(Map<String, String> configuration, ObservableList<Node> children) {
        for (Node node : children) {
            if (node instanceof TextField) {
                ((TextField) node).setText(configuration.get(node.getId()));
            }
        }
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

    //get methods
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

    // clear methods
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
        configurationName.setText("");
    }
}
