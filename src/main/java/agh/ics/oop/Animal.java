package agh.ics.oop;

import java.util.Random;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private final Genotype genotype;
    private final IMap map;
    private final int numberOfGens = 5;
    private int energy;
    private int old = 0;
    private int kids = 0;
    private int currentGenIndex = 0;

    public Animal() {
        this.direction = MapDirection.EAST;
        this.position = new Vector2d(2, 2);
        this.energy = 10;
        this.map = new EarthMap(5, 5);
        this.genotype = new Genotype(this.numberOfGens);
    }

    public Animal(int energy, int old, int kids) {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.energy = energy;
        this.old = old;
        this.kids = kids;
        this.map = new EarthMap(5, 5);
        this.genotype = new Genotype(this.numberOfGens);
    }

    public Animal(Vector2d position, MapDirection direction, int energy, IMap map) {
        this.map = map;
        this.position = position;
        this.direction = direction;
        this.energy = energy;
        this.genotype = new Genotype(this.numberOfGens);
    }

    public Animal(Vector2d position, MapDirection direction, IMap map, int energy,
                  int[] strongerGenotype, int[] weakerGenotype, int midPoint) {
        this.position = position;
        this.direction = direction;
        this.map = map;
        this.energy = energy;

        if (new Random().nextInt(2) == 0) {
            this.genotype = new Genotype(this.numberOfGens, strongerGenotype, weakerGenotype, midPoint);
        }
        else {
            this.genotype = new Genotype(this.numberOfGens, weakerGenotype, strongerGenotype, midPoint);
        }
    }

    public void move() {
        Vector2d oldPosition = this.position;

        changeDirection();
        if (this.map.canMoveTo(this.position.add(this.direction.toUnitVector()))) {
            this.position = this.map.correctPosition(this.position.add(this.direction.toUnitVector()));
            positionChanged(oldPosition, this.position);
        }
    }

    public void changeDirection() {
        this.direction = this.direction.changeDirection(this.genotype.getGens()[this.currentGenIndex]);
        this.currentGenIndex = (this.currentGenIndex + 1) % this.numberOfGens;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.map.positionChanged(oldPosition, newPosition, this);
    }

    public void eat(Plant plant) {
        this.energy += plant.getEnergyGain();
    }

    public void decreaseEnergy(int value) {
        this.energy -= value;
    }

    // Getters methods

    public MapDirection getDirection() {
        return this.direction;
    }

    public int[] getGenotype() {
        return this.genotype.getGens();
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getOld() {
        return this.old;
    }

    public int getKids() {
        return this.kids;
    }

    public String toString() {
        return this.position + "," + this.direction + ", " + this.genotype + ", " + this.energy;
    }
}