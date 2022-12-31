package agh.ics.oop.map_elements;

import agh.ics.oop.utils.Vector2d;

public class Plant {
    private final Vector2d position;
    private final int energyGain;

    public Plant(Vector2d position, int energyGain) {
        this.position = position;
        this.energyGain = energyGain;
    }

    public int getEnergyGain() {
        return this.energyGain;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString() {
        return "*";
    }
}
