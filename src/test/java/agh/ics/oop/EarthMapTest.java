package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EarthMapTest {
    @Test
    public void testMapCorrectPosition() {
        /*Variants variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 0, 1, 50,
                3, 0, 2, 1, 2
        );
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(2, 2)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.EAST};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();
        Animal animal = animalsLists.get(new Vector2d(2, 2)).get(0);

        Vector2d position = new Vector2d(2, 2);
        for (int i = 0; i < 10; i++) {
            engine.run();
            position = new Vector2d(Math.floorMod(position.x + 1, map.getWidth()), position.y);
            assertEquals(position, animal.getPosition());
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
        }*/
    }
}