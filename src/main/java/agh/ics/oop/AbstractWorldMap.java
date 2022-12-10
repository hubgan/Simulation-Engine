package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class AbstractWorldMap implements IMap {
    protected final int width;
    protected final int height;
    protected final HashMap<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    protected final Random random = new Random();
    protected final ArrayList<Vector2d> freeEquatorPositions = new ArrayList<>();
    protected final ArrayList<Vector2d> freeGrassLandPositions = new ArrayList<>();
    protected final HashMap<Vector2d, Plant> plantsPositions = new HashMap<>(); // Position of actually planted plants

    AbstractWorldMap(int width, int height, int startNumberOfPlants) {
        this.width = width;
        this.height = height;
        fillPositionsLists();
        generateRandomNumberOfPlants(startNumberOfPlants);
        System.out.println(freeEquatorPositions);
        System.out.println(freeGrassLandPositions);
    }

    private void fillPositionsLists() {
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
//        if (this.height % 2 == 0) {
//            for (int x = 0; x < this.width; x++) {
//                for (int y = 0; y < this.height; y++) {
//                    Vector2d position = new Vector2d(x, y);
//                    if (y >= this.height / 2 - 1 && y <= this.height / 2) {
//                        freeEquatorPositions.add(position);
//                    }
//                    else {
//                        freeGrassLandPositions.add(position);
//                    }
//                }
//            }
//        }
//        else {
//            for (int x = 0; x < this.width; x++) {
//                for (int y = 0; y < this.height; y++) {
//                    Vector2d position = new Vector2d(x, y);
//                    if (y >= this.height / 2 - 1 && y <= this.height / 2 + 1) {
//                        freeEquatorPositions.add(position);
//                    }
//                    else {
//                        freeGrassLandPositions.add(position);
//                    }
//                }
//            }
//        }
    }

    @Override
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
        this.plantsPositions.put(position, new Plant(position));
    }

    @Override
    public void eatPlant(Vector2d position) {
        if (isInEquatorBoundary(position.y)) {
            freeEquatorPositions.add(position);
        }
        else {
            freeGrassLandPositions.add(position);
        }

        plantsPositions.remove(position);
    }

    private boolean isInEquatorBoundary(int y) {
        return y >= this.height / 3 && y <= (this.height / 3) * 2;
    }

    @Override
    public void place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (!this.animals.containsKey(animalPosition)) {
            this.animals.put(animalPosition, new ArrayList<>());
        }

        this.animals.get(animalPosition).add(animal);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        this.animals.get(oldPosition).remove(animal);

        if (this.animals.get(oldPosition).size() == 0) {
            this.animals.remove(oldPosition);
        }

        place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.get(position) != null && this.animals.get(position).size() > 0;
    }

    @Override
    public Vector2d getMapBorders() {
        return new Vector2d(this.width, this.height);
    }

    @Override
    public int getNumberOfAnimals(Vector2d position) {
        return this.animals.get(position).size();
    }

    @Override
    public HashMap<Vector2d, ArrayList<Animal>> getAnimals() {
        return this.animals;
    }

    @Override
    public HashMap<Vector2d, Plant> getPlants() {
        return this.plantsPositions;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }
}
