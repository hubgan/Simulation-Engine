package agh.ics.oop.enums;

import agh.ics.oop.utils.Vector2d;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public int directionToGen() {
        return switch (this) {
            case NORTH -> 0;
            case NORTHEAST -> 1;
            case EAST -> 2;
            case SOUTHEAST -> 3;
            case SOUTH -> 4;
            case SOUTHWEST -> 5;
            case WEST -> 6;
            case NORTHWEST -> 7;
        };
    }


    public static MapDirection genToDirection(int gen) {
        return switch (gen) {
            case 0 -> NORTH;
            case 1 -> NORTHEAST;
            case 2 -> EAST;
            case 3 -> SOUTHEAST;
            case 4 -> SOUTH;
            case 5 -> SOUTHWEST;
            case 6 -> WEST;
            case 7 -> NORTHWEST;
            default -> throw new IllegalStateException("Unexpected value: " + gen);
        };
    }

    public MapDirection changeDirection(int gen) {
        return genToDirection((directionToGen() + gen) % 8);
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