package agh.ics.oop;

public class EarthMap extends AbstractWorldMap implements IMap {
    private final Vector2d lowerBorder;
    private final Vector2d upperBorder;

    EarthMap(int width, int height) {
        super(width, height);
        this.lowerBorder = new Vector2d(Integer.MIN_VALUE, 0);
        this.upperBorder = new Vector2d(Integer.MAX_VALUE, this.height - 1);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.lowerBorder) && position.precedes(this.upperBorder);
    }

    @Override
    public Vector2d correctPosition(Vector2d position) {
        if (position.x >= 0) {
            return new Vector2d(position.x % this.width, position.y);
        }

        return new Vector2d(this.width - 1, position.y);
    }
}