package agh.ics.oop.maps;

import agh.ics.oop.map_elements.Animal;
import agh.ics.oop.map_elements.Plant;
import agh.ics.oop.utils.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMap {
    boolean canMoveTo(Vector2d position);

    void place(Animal animal);

    int getNumberOfAnimals(Vector2d position);

    Vector2d correctPosition(Vector2d position);

    Vector2d getMapBorders();
    IGrassField getGarden();

    HashMap<Vector2d, ArrayList<Animal>> getAnimals();
    HashMap<Vector2d, Plant> getPlants();

    boolean isOccupied(Vector2d position);

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
    void eatPlant(Vector2d position);
    void generateRandomNumberOfPlants(int quantity);

    void deadAnimal(Animal animal);
    int getHeight();
    int getWidth();
}
