package agh.ics.oop;

import java.util.HashMap;

public interface IGrassField {
    void addPlant(int quantity);
    void deletePlant(Vector2d position);
    HashMap<Vector2d, Plant> getPlants();
}
