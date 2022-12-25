package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HellMapTest {
    @Test
    public void testMapCorrectPosition() {
        /*Variants variants = new Variants(
                5, 5, "HellMap", "lekka korekta", "pełna predestynacja",
                2, 0, 0, 1, 50,
                3, 0, 2, 1, 2
        );
        IMap map = new HellMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(1, 2)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.EAST};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();
        Animal animal = animalsLists.get(new Vector2d(1, 2)).get(0);

        Vector2d position = new Vector2d(1, 2);
        for (int i = 0; i < 3; i++) {
            engine.run();
            position = new Vector2d(position.x + 1, position.y);
            assertEquals(position, animal.getPosition());
        }
        engine.run();
        assertNotEquals(new Vector2d(4, 2), animal.getPosition());*/
    }
}