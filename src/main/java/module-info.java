module project.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens agh.ics.oop.gui to javafx.fxml;
    exports agh.ics.oop.gui;
}