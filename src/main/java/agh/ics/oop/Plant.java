package agh.ics.oop;

public class Plant {
    private Vector2d position;
    private int energyGain = 1;

    public Plant(Vector2d position) {
        this.position = position;
    }

    public int getEnergyGain() {
        return this.energyGain;
    }

    public String toString() {
        return "*";
    }
}
