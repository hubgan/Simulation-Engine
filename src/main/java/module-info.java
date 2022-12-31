module project.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens agh.ics.oop.gui to javafx.fxml;
    exports agh.ics.oop.gui;
    exports agh.ics.oop;
    opens agh.ics.oop to javafx.fxml;

    exports agh.ics.oop.engine;
    exports agh.ics.oop.enums;
    exports agh.ics.oop.map_elements;
    exports agh.ics.oop.maps;
    exports agh.ics.oop.utils;
}