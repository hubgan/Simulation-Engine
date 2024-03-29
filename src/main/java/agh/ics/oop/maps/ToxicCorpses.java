package agh.ics.oop.maps;

import agh.ics.oop.map_elements.Plant;
import agh.ics.oop.utils.Vector2d;
import agh.ics.oop.gui.Variants;

import java.util.*;

public class ToxicCorpses implements IGrassField {
    protected final HashMap<Vector2d, Integer> freePositions = new HashMap<>();
    protected final HashMap<Vector2d, Integer> takenPositions = new HashMap<>();
    protected final HashMap<Vector2d, Plant> plantsPositions = new HashMap<>();

    private final int width;
    private final int height;
    private final Variants variants;
    private final Random random = new Random();

    public ToxicCorpses(int width, int height, Variants variants) {
        this.width = width;
        this.height = height;
        this.variants = variants;
        fillPositionsList();
        generatePlantsWithoutSort(this.variants.getPlantsStartingNumber());
    }

    public void deadAnimal(Vector2d position) {
        if (freePositions.containsKey(position)) freePositions.put(position, freePositions.get(position) + 1);
        else takenPositions.put(position, takenPositions.get(position) + 1);
    }

    private void fillPositionsList() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                freePositions.put(new Vector2d(x, y), 0);
            }
        }
    }


    private void generatePlantsWithoutSort(int n) {
        if (n > this.freePositions.size()) n = this.freePositions.size();
        for (int i = 0; i < n; i++) {
            List<Vector2d> keysAsArray = new ArrayList<>(freePositions.keySet());
            Vector2d position = keysAsArray.get(this.random.nextInt(keysAsArray.size()));

            this.plantsPositions.put(position, new Plant(position, this.variants.getPlantEnergyGain()));
            this.takenPositions.put(position, this.freePositions.get(position));
            this.freePositions.remove(position);
        }
    }

    private void generatePlantsWithSort(int n) {
        if (n > this.freePositions.size()) n = this.freePositions.size();

        // Sort HashMap by values
        Iterator<Map.Entry<Vector2d, Integer>> sorted = this.freePositions.entrySet().stream().sorted(Map.Entry.comparingByValue()).iterator();

        for (int i = 0; i < n; i++) {
            Vector2d position = sorted.next().getKey();
            this.plantsPositions.put(position, new Plant(position, this.variants.getPlantEnergyGain()));
            this.takenPositions.put(position, this.freePositions.get(position));
            this.freePositions.remove(position);
        }
    }

    @Override
    public void addPlant(int quantity) {
        int randInt = this.random.nextInt(10);

        if (randInt < 8) generatePlantsWithSort(quantity); // 80% for generating plants where most animals died
        else generatePlantsWithoutSort(quantity); // 20% for generating plants on random positions
    }

    @Override
    public void deletePlant(Vector2d position) {
        this.freePositions.put(position, this.takenPositions.get(position));
        this.takenPositions.remove(position);
        this.plantsPositions.remove(position);
    }

    @Override
    public HashMap<Vector2d, Plant> getPlants() {
        return this.plantsPositions;
    }

    public HashMap<Vector2d, Integer> getFreePositions() {
        return this.freePositions;
    }
}
