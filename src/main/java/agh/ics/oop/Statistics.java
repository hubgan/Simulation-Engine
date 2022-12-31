package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    private final IMap map;
    private final SimulationEngine engine;
    private final int mapSize;

    public Statistics(IMap map, SimulationEngine engine) {
        this.map = map;
        this.engine = engine;
        this.mapSize = map.getWidth() * map.getHeight();
    }

    public int getNumberOfAnimals() {
        return this.engine.getAnimals().size();
    }

    public int getNumberOfPlants() {
        return this.map.getPlants().size();
    }

    public int getNumberOfFreeFields() {
        HashMap<Vector2d, Plant> plants = this.map.getPlants();
        HashMap<Vector2d, ArrayList<Animal>> animals = this.map.getAnimals();

        int size = plants.size();
        for (Vector2d position : animals.keySet()) {
            if (!(plants.containsKey(position))) {
                size += 1;
            }
        }

        return this.mapSize - size;
    }

    public  ArrayList<Animal> getMostPopularGenotype() {
        HashMap<int[], ArrayList<Animal>> genotypes = new HashMap<>();

        for (Animal animal : this.engine.getAnimals()) {
            int[] genotype = animal.getGenotype();
            if (!(genotypes.containsKey(genotype))) {

                genotypes.put(genotype, new ArrayList<>());
            }
            ArrayList<Animal> list = genotypes.get(genotype);
            list.add(animal);
        }
        ArrayList<Animal> maxList = new ArrayList<>();
        for (ArrayList<Animal> animals : genotypes.values()) {
            if (animals.size() > maxList.size()) maxList = animals;
        }
        return maxList;
    }

    public float getAverageEnergy() {
        int numberOfAnimals = this.engine.getAnimals().size();
        if (numberOfAnimals == 0) return 0;

        int totalEnergy = 0;

        for (Animal animal : this.engine.getAnimals()) {
            totalEnergy += animal.getEnergy();
        }

        return (float) totalEnergy / numberOfAnimals;
    }

    public float getAverageLifeTime() {
        int numberOfDeadAnimals = this.engine.getDeadAnimals().size();
        if (numberOfDeadAnimals == 0) return 0;

        int totalLifeTime = 0;

        for (Animal animal : this.engine.getDeadAnimals()) {
            totalLifeTime += animal.getOld();
        }

        return (float) totalLifeTime / numberOfDeadAnimals;
    }
}
