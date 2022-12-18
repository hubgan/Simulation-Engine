package agh.ics.oop;

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

    public String toString() {
        return "*";
    }
}
