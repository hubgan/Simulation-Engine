package agh.ics.oop;

import agh.ics.oop.gui.Variants;

import java.util.Random;

public class Animal {
    private MapDirection direction = MapDirection.genToDirection(getRandomNumber(8));
    private Vector2d position;
    private Boolean targeted = false;
    private final Genotype genotype;
    private final IMap map;
    private final int numberOfGens;
    private final Variants variants;
    private int energy;
    private int old = 0;
    private int kids = 0;
    private int currentGenIndex = 0;
    private int eatenPlants = 0;
    private int deadDay;

    public Animal(Vector2d position, int energy, IMap map, Variants variants) {
        this.map = map;
        this.position = position;
        this.energy = energy;
        this.variants = variants;
        this.numberOfGens = variants.getGenomLength();
        this.genotype = new Genotype(this.numberOfGens);
    }

    public Animal(Vector2d position, int energy, IMap map, int[] genotype, Variants variants, MapDirection direction) { // For testing purposes
        this.map = map;
        this.position = position;
        this.direction = direction;
        this.energy = energy;
        this.variants = variants;
        this.numberOfGens = variants.getGenomLength();
        this.genotype = new Genotype(genotype);
    }

    public Animal(Vector2d position, IMap map, int energy,
                  int[] strongerGenotype, int[] weakerGenotype, int midPoint, Variants variants) {
        this.position = position;
        this.map = map;
        this.energy = energy;
        this.variants = variants;
        this.numberOfGens = this.variants.getGenomLength();

        if (new Random().nextInt(2) == 0) { // Left side of stronger genotype
            this.genotype = new Genotype(this.numberOfGens, strongerGenotype, weakerGenotype, midPoint,
                    this.variants);
        }
        else { // Right side of stronger genotype
            this.genotype = new Genotype(this.numberOfGens, weakerGenotype, strongerGenotype,
                    this.numberOfGens - midPoint, this.variants);
        }
    }

    public void move() {
        Vector2d oldPosition = this.position;

        changeDirection();
        if (this.map.canMoveTo(this.position.add(this.direction.toUnitVector()))) {
            Vector2d newPositionBeforeCorrection = this.position.add(this.direction.toUnitVector());
            this.position = this.map.correctPosition(newPositionBeforeCorrection);

            if (!this.position.equals(newPositionBeforeCorrection)) {
                this.decreaseEnergy(variants.getCopulationEnergyLost());
            }

            positionChanged(oldPosition, this.position);
        }
    }

    private void changeDirection() {
        this.direction = this.direction.changeDirection(this.genotype.getGens()[this.currentGenIndex]);
        changeGenIndex();
    }

    private void changeGenIndex() {
        switch (this.variants.getBehaviourVariant()) {
            case PREDESTINATION -> {
                this.currentGenIndex = (this.currentGenIndex + 1) % this.numberOfGens;
            }
            case MADNESS -> {
                int randomNumber = this.getRandomNumber(10);
                if (randomNumber < 8) {
                    this.currentGenIndex = (this.currentGenIndex + 1) % this.numberOfGens;
                }
                else {
                    int lastGenIndex = this.currentGenIndex;

                    while (lastGenIndex == this.currentGenIndex) {
                        this.currentGenIndex = this.getRandomNumber(this.numberOfGens);
                    }
                }
            }
        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.map.positionChanged(oldPosition, newPosition, this);
    }

    public void eat(Plant plant) {
        this.eatenPlants++;
        this.energy += plant.getEnergyGain();
    }

    public void decreaseEnergy(int value) {
        this.energy -= value;

    }

    public void increaseOld() {
        this.old += 1;
    }

    public void increaseKids() {
        this.kids += 1;
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

    private int getRandomNumber(int bound) {
        return new Random().nextInt(bound);
    }
    public Boolean getTargeted() {
        return targeted;
    }
    public void changeTargeted() {
        this.targeted = !this.targeted;
    }

    public int getCurrentGenIndex() {
        return currentGenIndex;
    }

    public int getEatenPlants() {
        return eatenPlants;
    }

    public int getDeadDay() {
        return deadDay;
    }
    public void setDeadDay(int deadDay) {
        this.deadDay = deadDay;
    }
}