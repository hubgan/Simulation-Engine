package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.gui.Variants;
import agh.ics.oop.map_elements.Animal;
import agh.ics.oop.maps.EarthMap;
import agh.ics.oop.maps.IMap;
import agh.ics.oop.utils.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EarthMapTest {
    @Test
    public void testMapCorrectPosition() {
        Map<String, String> variantsMap = new HashMap<>();
        variantsMap.put("width", "5");
        variantsMap.put("height", "5");
        variantsMap.put("mapVariant", "earth globe");
        variantsMap.put("mutationVariant", "slight correction");
        variantsMap.put("animalsVariant", "full predestination");
        variantsMap.put("plantsVariant", "forested equatorial");
        variantsMap.put("energyGain", "2");
        variantsMap.put("plantsStartingNumber", "0");
        variantsMap.put("growthNumber", "0");
        variantsMap.put("animalStartingNumber", "1");
        variantsMap.put("startingEnergy", "50");
        variantsMap.put("copulationEnergy", "3");
        variantsMap.put("minMutations", "0");
        variantsMap.put("maxMutations", "2");
        variantsMap.put("genomLength", "1");
        variantsMap.put("moveEnergyLoss", "2");
        variantsMap.put("copulationEnergyLoss", "4");
        Variants variants = new Variants(variantsMap);
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(2, 2)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.EAST};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();
        Animal animal = animalsLists.get(new Vector2d(2, 2)).get(0);

        Vector2d position = new Vector2d(2, 2);
        int[] assumedEnergy = new int[]{48, 46, 44, 42, 40, 38, 36, 34, 32, 30};
        for (int i = 0; i < 10; i++) {
            engine.run();
            position = new Vector2d(Math.floorMod(position.x + 1, map.getWidth()), position.y);
            assertEquals(position, animal.getPosition());
            assertEquals(assumedEnergy[i], animal.getEnergy());
        }

        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        mapDirections = new MapDirection[]{MapDirection.WEST};
        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        animalsLists = map.getAnimals();
        animal = animalsLists.get(new Vector2d(2, 2)).get(0);

        position = new Vector2d(2, 2);
        for (int i = 0; i < 10; i++) {
            engine.run();
            position = new Vector2d(Math.floorMod(position.x - 1, map.getWidth()), position.y);
            assertEquals(position, animal.getPosition());
            assertEquals(assumedEnergy[i], animal.getEnergy());
        }

        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        mapDirections = new MapDirection[]{MapDirection.NORTH};
        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        animalsLists = map.getAnimals();
        animal = animalsLists.get(new Vector2d(2, 2)).get(0);

        position = new Vector2d(2, 2);
        for (int i = 0; i < 10; i++) {
            engine.run();
            position = new Vector2d(position.x, Math.min(variants.getHeight() - 1, position.y + 1));
            assertEquals(position, animal.getPosition());
            assertEquals(assumedEnergy[i], animal.getEnergy());
        }

        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        mapDirections = new MapDirection[]{MapDirection.SOUTH};
        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        animalsLists = map.getAnimals();
        animal = animalsLists.get(new Vector2d(2, 2)).get(0);

        position = new Vector2d(2, 2);
        for (int i = 0; i < 10; i++) {
            engine.run();
            position = new Vector2d(position.x, Math.max(0, position.y - 1));
            assertEquals(position, animal.getPosition());
            assertEquals(assumedEnergy[i], animal.getEnergy());
        }
    }
}