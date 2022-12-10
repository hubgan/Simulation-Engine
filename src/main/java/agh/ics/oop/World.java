package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class World {
    public static void main(String[] args) {
        IMap map = new HellMap(5, 5);
        SimulationEngine engine = new SimulationEngine(map, 4, 5);
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
        System.out.println(map.getPlants());
    }
}
