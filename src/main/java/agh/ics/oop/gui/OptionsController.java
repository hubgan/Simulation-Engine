package agh.ics.oop.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OptionsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
