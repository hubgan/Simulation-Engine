package agh.ics.oop.maps;

import agh.ics.oop.map_elements.Plant;
import agh.ics.oop.utils.Vector2d;

import java.util.HashMap;

public interface IGrassField {
    void addPlant(int quantity);
    void deletePlant(Vector2d position);
    HashMap<Vector2d, Plant> getPlants();
}
