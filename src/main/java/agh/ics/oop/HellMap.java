package agh.ics.oop;

import agh.ics.oop.gui.Variants;

public class HellMap extends AbstractWorldMap implements IMap {
    private final Vector2d lowerBorder;
    private final Vector2d upperBorder;
    public HellMap(int width, int height, Variants variants) {
        super(width, height, variants);
        this.lowerBorder = new Vector2d(0, 0);
        this.upperBorder = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public Vector2d correctPosition(Vector2d position) {
        if (!(position.follows(this.lowerBorder) && position.precedes(this.upperBorder))) {
            Vector2d newRandomPosition = new Vector2d(getRandomNumber(this.width), getRandomNumber(this.height));

            while (position.equals(newRandomPosition)) {
                newRandomPosition = new Vector2d(getRandomNumber(this.width), getRandomNumber(this.height));
            }

            return newRandomPosition;
        }

        return position;
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
