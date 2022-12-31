package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.gui.Variants;
import agh.ics.oop.map_elements.Animal;
import agh.ics.oop.maps.HellMap;
import agh.ics.oop.maps.IMap;
import agh.ics.oop.utils.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class HellMapTest {
    @Test
    public void testMapCorrectPosition() {
        Map<String, String> variantsMap = new HashMap<>();
        variantsMap.put("width", "5");
        variantsMap.put("height", "5");
        variantsMap.put("mapVariant", "infernal portal");
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
        variantsMap.put("moveEnergyLoss", "5");
        variantsMap.put("copulationEnergyLoss", "4");
        Variants variants = new Variants(variantsMap);
        IMap map = new HellMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(1, 2)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.EAST};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();
        Animal animal = animalsLists.get(new Vector2d(1, 2)).get(0);

        Vector2d position = new Vector2d(1, 2);
        int[] assumedEnergy = new int[]{45, 40, 35, 26};
        for (int i = 0; i < 3; i++) {
            engine.run();
            position = new Vector2d(position.x + 1, position.y);
            assertEquals(position, animal.getPosition());
            assertEquals(assumedEnergy[i], animal.getEnergy());
        }
        engine.run();
        assertNotEquals(new Vector2d(4, 2), animal.getPosition());
        assertEquals(assumedEnergy[assumedEnergy.length - 1], animal.getEnergy());
    }
}