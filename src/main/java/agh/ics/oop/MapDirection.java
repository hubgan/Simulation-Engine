package agh.ics.oop;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public int directionToAngle() {
        return switch (this) {
            case NORTH -> 0;
            case NORTHEAST -> 45;
            case EAST -> 90;
            case SOUTHEAST -> 135;
            case SOUTH -> 180;
            case SOUTHWEST -> 225;
            case WEST -> 270;
            case NORTHWEST -> 315;
        };
    }

    public MapDirection angleToDirection(int angle) {
        return switch (angle) {
            case 45 -> NORTHEAST;
            case 90 -> EAST;
            case 135 -> SOUTHEAST;
            case 180 -> SOUTH;
            case 225 -> SOUTHWEST;
            case 270 -> WEST;
            case 315 -> NORTHWEST;
            default -> NORTH;
        };
    }


    public int getAngleFromGen(int gen) {
        return switch (gen) {
            case 1 -> 45;
            case 2 -> 90;
            case 3 -> 135;
            case 4 -> 180;
            case 5 -> 225;
            case 6 -> 270;
            case 7 -> 315;
            default -> 0;
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case NORTHEAST -> new Vector2d(1, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTHEAST -> new Vector2d(1, -1);
            case SOUTH -> new Vector2d(0, -1);
            case SOUTHWEST -> new Vector2d(-1, -1);
            case WEST -> new Vector2d(-1, 0);
            case NORTHWEST -> new Vector2d(-1, 1);
        };
    }
}
