package agh.ics.oop.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void startSimulation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OptionsApplication.class.getResource("/simulation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(scene);
        stage.show();
    }

}
