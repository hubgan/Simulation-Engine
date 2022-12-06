package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractWorldMap implements IMap {
    protected final int width;
    protected final int height;
    protected final HashMap<Vector2d, ArrayList<Animal>> animals = new HashMap<>();

    AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
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
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }
}