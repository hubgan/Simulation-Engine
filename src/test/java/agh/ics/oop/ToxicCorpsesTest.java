package agh.ics.oop;

import agh.ics.oop.gui.Variants;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ToxicCorpsesTest {
    @Test
    public void testAnimalDead() {
        Map<String, String> variantsMap = new HashMap<>();
        variantsMap.put("width", "5");
        variantsMap.put("height", "5");
        variantsMap.put("mapVariant", "earth globe");
        variantsMap.put("mutationVariant", "slight correction");
        variantsMap.put("animalsVariant", "full predestination");
        variantsMap.put("plantsVariant", "toxic corpses");
        variantsMap.put("energyGain", "2");
        variantsMap.put("plantsStartingNumber", "0");
        variantsMap.put("growthNumber", "0");
        variantsMap.put("animalStartingNumber", "100");
        variantsMap.put("startingEnergy", "6");
        variantsMap.put("copulationEnergy", "3");
        variantsMap.put("minMutations", "0");
        variantsMap.put("maxMutations", "2");
        variantsMap.put("genomLength", "1");
        variantsMap.put("moveEnergyLoss", "10");
        variantsMap.put("copulationEnergyLoss", "4");
        Variants variants = new Variants(variantsMap);
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[100];
        MapDirection[] mapDirections = new MapDirection[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Vector2d position = new Vector2d(random.nextInt(variants.getWidth()), random.nextInt(variants.getHeight()));
            animalPositions[i] = position;
            mapDirections[i] = MapDirection.NORTH;
        }

        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        engine.run();
        engine.run();

        HashMap<Vector2d, Integer> freePositions = ((ToxicCorpses) map.getGarden()).getFreePositions();

        int deadCount = 0;
        for (Vector2d position: freePositions.keySet()) {
            deadCount += freePositions.get(position);
        }

        assertEquals(100, deadCount);

        variantsMap.replace("animalStartingNumber", "3");
        variants = new Variants(variantsMap);
        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        animalPositions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(2, 2)};
        mapDirections = new MapDirection[]{MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH};

        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        engine.run();
        engine.run();

        freePositions = ((ToxicCorpses) map.getGarden()).getFreePositions();
        assertEquals(3, freePositions.get(new Vector2d(2, 3)));
    }
}