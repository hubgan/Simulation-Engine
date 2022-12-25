package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Equator implements IGrassField {
    protected final ArrayList<Vector2d> freeEquatorPositions = new ArrayList<>();
    protected final ArrayList<Vector2d> freeGrassLandPositions = new ArrayList<>();
    protected final HashMap<Vector2d, Plant> plantsPositions = new HashMap<>();
    private Random random = new Random();
    private final Variants variants;

    private int width;
    private int height;

    Equator(int width, int height, Variants variants) {
        this.width = width;
        this.height = height;
        this.variants = variants;
        fillPositionsList();
        generateRandomNumberOfPlants(this.variants.getPlantsStartingNumber());
    }

    private void fillPositionsList() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Vector2d position = new Vector2d(x, y);
                if (isInEquatorBoundary(y)) {
                    freeEquatorPositions.add(position);
                }
                else {
                    freeGrassLandPositions.add(position);
                }
            }
        }
    }

    public void generateRandomNumberOfPlants(int n) {
        int counter = 0;

        while (counter < n) {
            int randomNumber = random.nextInt(10);
            counter += 1;

            if (randomNumber < 8 && freeEquatorPositions.size() > 0) { // 80% for equatorPosition
                generateRandomPlantsUtil(this.freeEquatorPositions);
            }
            else if (freeGrassLandPositions.size() > 0) { // 20% for grassLands
                generateRandomPlantsUtil(this.freeGrassLandPositions);
            }
            else return;
        }
    }

    private void generateRandomPlantsUtil(ArrayList<Vector2d> list) {
        int randomIndexPosition = random.nextInt(list.size());
        Vector2d position = list.get(randomIndexPosition);
        list.remove(position);
        this.plantsPositions.put(position, new Plant(position, this.variants.getPlantEnergyGain()));
    }

    private boolean isInEquatorBoundary(int y) {
        return y >= this.height / 3 && y <= (this.height / 3) * 2;
    }

    @Override
    public void addPlant(int quantity) {
        generateRandomNumberOfPlants(quantity);
    }

    @Override
    public void deletePlant(Vector2d position) {
        this.plantsPositions.remove(position);

        if (isInEquatorBoundary(position.y)) {
            this.freeEquatorPositions.add(position);
        }
        else {
            this.freeGrassLandPositions.add(position);
        }
    }

    @Override
    public HashMap<Vector2d, Plant> getPlants() {
        return this.plantsPositions;
    }
}
