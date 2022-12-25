package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void testAnimal() {
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

        int xPosition = 2;
        for (int i = 0; i < 10; i++) {
            engine.run();
            xPosition = Math.floorMod(xPosition + 1, map.getWidth());
            assertEquals(new Vector2d(xPosition, 2), animal.getPosition());
        }

        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        mapDirections = new MapDirection[]{MapDirection.WEST};
        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        animalsLists = map.getAnimals();
        animal = animalsLists.get(new Vector2d(2, 2)).get(0);

        xPosition = 2;
        for (int i = 0; i < 10; i++) {
            engine.run();
            xPosition = Math.floorMod(xPosition - 1, map.getWidth());
            assertEquals(new Vector2d(xPosition, 2), animal.getPosition());
        }*/
    }
}