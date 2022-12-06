package agh.ics.oop;

import java.util.Random;

public class HellMap extends AbstractWorldMap implements IMap {
    private final Random random = new Random();

    HellMap(int width, int height) {
        super(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public Vector2d correctPosition(Vector2d position) {
        if (position.x >= this.width || position.x < 0 || position.y >= this.height || position.y < 0) {
            return new Vector2d(getRandomNumber(this.width), getRandomNumber(this.height));
        }

        return position;
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
