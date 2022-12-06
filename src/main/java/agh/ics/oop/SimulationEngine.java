package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

public class SimulationEngine implements IEngine {
    private final IMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Random random = new Random();

    SimulationEngine(IMap map, int startingNumberOfAnimals, int startingNumberOfEnergy) {
        this.map = map;

        Vector2d mapBoundary = this.map.getMapBorders();

        for (int i = 0; i < startingNumberOfAnimals; i++) {
            MapDirection direction = getRandomDirection(getRandomNumber(8));
            Vector2d position = new Vector2d(getRandomNumber(mapBoundary.x), getRandomNumber(mapBoundary.y));

            Animal animal = new Animal(position, direction, startingNumberOfEnergy, this.map);
            animals.add(animal);
            this.map.place(animal);
        }
    }

    @Override
    public void run() {
        for (Animal animal : this.animals) {
            animal.move();
        }

        System.out.println(this.map);
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }

    public MapDirection getRandomDirection(int randomNumber) {
        return switch (randomNumber) {
            case 1 -> MapDirection.NORTHEAST;
            case 2 -> MapDirection.EAST;
            case 3 -> MapDirection.SOUTHEAST;
            case 4 -> MapDirection.SOUTH;
            case 5 -> MapDirection.SOUTHWEST;
            case 6 -> MapDirection.WEST;
            case 7 -> MapDirection.NORTHWEST;
            default -> MapDirection.NORTH;
        };
    }
}