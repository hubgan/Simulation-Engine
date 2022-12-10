package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMap {
    boolean canMoveTo(Vector2d position);

    void place(Animal animal);

    int getNumberOfAnimals(Vector2d position);

    Vector2d correctPosition(Vector2d position);

    Vector2d getMapBorders();

    HashMap<Vector2d, ArrayList<Animal>> getAnimals();
    HashMap<Vector2d, Plant> getPlants();

    boolean isOccupied(Vector2d position);

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
    void generateRandomNumberOfPlants(int n);
    void eatPlant(Vector2d position);
}
