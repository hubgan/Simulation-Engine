package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ToxicCorpsesTest {
    //@Test
    /*public void testAnimalDead() {
        Variants variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 0, 100, 6,
                3, 0, 2, 1, 10
        );
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

       variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 0, 3, 6,
                3, 0, 2, 1, 10
        );
        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        animalPositions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(2, 2)};
        mapDirections = new MapDirection[]{MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH};

        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        engine.run();
        engine.run();

        freePositions = ((ToxicCorpses) map.getGarden()).getFreePositions();
        assertEquals(3, freePositions.get(new Vector2d(2, 3)));
    }*/
}