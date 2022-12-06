package agh.ics.oop;

import java.util.HashMap;

public interface IMap {
    boolean canMoveTo(Vector2d position);

    void place(Animal animal);

    int numberOfAnimals(Vector2d position);

    Vector2d correctPosition(Vector2d position);

    Vector2d getMapBorders();

    HashMap getAnimals();

    boolean isOccupied(Vector2d position);
}
