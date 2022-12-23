package agh.ics.oop;

import agh.ics.oop.gui.OptionsApplication;
import agh.ics.oop.gui.OptionsController;
import javafx.application.Application;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class World {
    public static void main(String[] args) {
        /*System.out.println("Start");
        Application.launch(OptionsApplication.class, args);
        System.out.println("Stop");*/

        LinkedHashMap<String, String> map

                = new LinkedHashMap<>();




        // key-value pairs

        map.put("rohit", "one");

        map.put("Sam", "two");

        map.put("jainie", "three");
        Configuration elo = new Configuration("elo");
        elo.writeConfiguration(map);
        System.out.println(elo.readConfiguration());
        map.put("Sam", "3");
        elo.writeConfiguration(map);
        System.out.println(elo.readConfiguration());
        LinkedHashMap<String, String> mapx

                = new LinkedHashMap<>();
        mapx.put("elo", "W");
        elo.writeConfiguration(mapx);
        System.out.println(elo.readConfiguration());





        /*Variants variants = new Variants(5, 5, "HellMap", "lekka korekta",
                "nieco szaleństwa", 1, 2, 1
                , 4, 50, 5, 0,
                2, 5, 30);
        IMap map = createMap(variants);
        SimulationEngine engine = new SimulationEngine(map, variants);
        System.out.println(map);
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());*/
    }

    private static IMap createMap(Variants variants) {
        return switch (variants.getMapVariant()) {
            case EARTHMAP -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
            case HELLMAP -> new HellMap(variants.getWidth(), variants.getHeight(), variants);
            default -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        };
    }
}
