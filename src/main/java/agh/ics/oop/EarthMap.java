package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class EarthMap implements IMap {
    private final int width;
    private final int height;
    private final Vector2d lowerBorder;
    private final Vector2d upperBorder;
    private final HashMap<Vector2d, ArrayList<Animal>> animals = new HashMap<>();

    EarthMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.lowerBorder = new Vector2d(Integer.MIN_VALUE, 0);
        this.upperBorder = new Vector2d(Integer.MAX_VALUE, this.height);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.lowerBorder) && position.precedes(this.upperBorder);
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
    public HashMap<Vector2d, ArrayList<Animal>> getAnimals() {
        return this.animals;
    }

    @Override
    public int numberOfAnimals(Vector2d position) {
        return this.animals.get(position).size();
    }

    @Override
    public Vector2d correctPosition(Vector2d position) {
        if (position.x >= 0) {
            return new Vector2d(position.x % this.width, position.y);
        }

        return new Vector2d(this.width - 1, position.y);
    }

    @Override
    public Vector2d getMapBorders() {
        return new Vector2d(this.width, this.height);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.get(position) != null && this.animals.get(position).size() > 0;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }
}
