package agh.ics.oop;

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
            return new Vector2d(getRandomNumber(this.width), getRandomNumber(this.height));
        }

        return position;
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
