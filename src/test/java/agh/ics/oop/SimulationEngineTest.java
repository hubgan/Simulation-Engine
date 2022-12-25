package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {
    // Testing increasing old, decreasing energy of animals and checking checkDead function
    /*@Test
    public void testSimulationEngine() {
        Variants variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 0, 2, 6,
                3, 0, 2, 1, 2
                );
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 3)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.NORTH, MapDirection.NORTH};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();

        for (int i = 1; i < 4; i++) {
            engine.run();
            for (Vector2d position: animalsLists.keySet()) {
                for (Animal animal: animalsLists.get(position)) {
                    assertEquals(variants.getStartingEnergyOfAnimals() - i * variants.getEnergyLost(), animal.getEnergy());
                    assertEquals(i, animal.getOld());
                }
            }
        }

        engine.run();
        int size = 0;
        for (Vector2d position: animalsLists.keySet()) {
            for (Animal ignored : animalsLists.get(position)) {
                size += 1;
            }
        }
        assertEquals(0, size);
    }

    // Testing copulation - decreasing energy, increasing kids, sum of energy at certain position should be the same
    // before and after copulation
    @Test
    public void testCopulation() {
        Variants variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 0, 2, 10,
                2, 0, 2, 1, 2
        );
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 3)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.NORTH, MapDirection.WEST};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();

        engine.run();
        System.out.println(map.getAnimals());
        int assumedSumOfEnergyAfter = 16;
        int[] assumedEnergy = new int[]{4, 4, 8};
        int[] assumedKids = new int[]{1, 1, 0};

        int sumOfEnergyAfter = 0;
        for (Vector2d position: animalsLists.keySet()) {
            int i = 0;
            for (Animal animal: animalsLists.get(position)) {
                assertEquals(assumedEnergy[i], animal.getEnergy());
                assertEquals(assumedKids[i], animal.getKids());
                sumOfEnergyAfter += animal.getEnergy();
                i += 1;
            }
        }
        assertEquals(assumedSumOfEnergyAfter, sumOfEnergyAfter);
    }

    // Testing amount of plants that should grow everyday
    @Test
    public void testPlantsGrowEveryDay() {
        Variants variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 1, 0, 10,
                2, 0, 2, 1, 2
        );
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{};
        MapDirection[] mapDirections = new MapDirection[]{};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, Plant> plants = map.getPlants();

        int numberOfPlants = 0;
        for (Vector2d ignored : plants.keySet()) {
            numberOfPlants += 1;
        }
        assertEquals(0, numberOfPlants);

        for (int i = 1; i < 3; i++) {
            engine.run();
            numberOfPlants = 0;
            for (Vector2d ignored : plants.keySet()) {
                numberOfPlants += 1;
            }
            assertEquals(i * variants.getGrowthNumber(), numberOfPlants);
        }

        variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 0, 5, 0, 10,
                2, 0, 2, 1, 2
        );
        map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        plants = map.getPlants();

        numberOfPlants = 0;
        for (Vector2d ignored : plants.keySet()) {
            numberOfPlants += 1;
        }
        assertEquals(0, numberOfPlants);

        for (int i = 1; i < 3; i++) {
            engine.run();
            numberOfPlants = 0;
            for (Vector2d ignored : plants.keySet()) {
                numberOfPlants += 1;
            }
            assertEquals(i * variants.getGrowthNumber(), numberOfPlants);
        }
    }

    @Test
    public void testEatPlants() {
        Variants variants = new Variants(
                5, 5, "EarthMap", "lekka korekta", "pełna predestynacja",
                2, 25, 2, 2, 10,
                2, 0, 2, 1, 2
        );
        IMap map = new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        Vector2d[] animalPositions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 2)};
        MapDirection[] mapDirections = new MapDirection[]{MapDirection.NORTH, MapDirection.NORTH};
        SimulationEngine engine = new SimulationEngine(map, variants, animalPositions, mapDirections);
        HashMap<Vector2d, ArrayList<Animal>> animalsLists = map.getAnimals();
        System.out.println(map);

        for (Vector2d position: animalsLists.keySet()) {
            for (Animal animal: animalsLists.get(position)) {
                assertEquals(10, animal.getEnergy());
            }
        }

        for (int i = 0; i < 4; i++) {
            engine.run();
            for (Vector2d position: animalsLists.keySet()) {
                for (Animal animal: animalsLists.get(position)) {
                    assertEquals(10, animal.getEnergy());
                }
            }
        }
    }*/
}