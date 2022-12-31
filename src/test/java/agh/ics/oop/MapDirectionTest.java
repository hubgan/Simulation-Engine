package agh.ics.oop;

import agh.ics.oop.enums.MapDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    public void testMapDirection() {
        // For NORTH direction
        MapDirection north = MapDirection.NORTH;
        assertEquals(MapDirection.NORTH, north.changeDirection(0));
        assertEquals(MapDirection.NORTHEAST, north.changeDirection(1));
        assertEquals(MapDirection.EAST, north.changeDirection(2));
        assertEquals(MapDirection.SOUTHEAST, north.changeDirection(3));
        assertEquals(MapDirection.SOUTH, north.changeDirection(4));
        assertEquals(MapDirection.SOUTHWEST, north.changeDirection(5));
        assertEquals(MapDirection.WEST, north.changeDirection(6));
        assertEquals(MapDirection.NORTHWEST, north.changeDirection(7));

        // For NORTHEAST direction
        MapDirection northeast = MapDirection.NORTHEAST;
        assertEquals(MapDirection.NORTHEAST, northeast.changeDirection(0));
        assertEquals(MapDirection.EAST, northeast.changeDirection(1));
        assertEquals(MapDirection.SOUTHEAST, northeast.changeDirection(2));
        assertEquals(MapDirection.SOUTH, northeast.changeDirection(3));
        assertEquals(MapDirection.SOUTHWEST, northeast.changeDirection(4));
        assertEquals(MapDirection.WEST, northeast.changeDirection(5));
        assertEquals(MapDirection.NORTHWEST, northeast.changeDirection(6));
        assertEquals(MapDirection.NORTH, northeast.changeDirection(7));

        // For EAST direction
        MapDirection east = MapDirection.EAST;
        assertEquals(MapDirection.EAST, east.changeDirection(0));
        assertEquals(MapDirection.SOUTHEAST, east.changeDirection(1));
        assertEquals(MapDirection.SOUTH, east.changeDirection(2));
        assertEquals(MapDirection.SOUTHWEST, east.changeDirection(3));
        assertEquals(MapDirection.WEST, east.changeDirection(4));
        assertEquals(MapDirection.NORTHWEST, east.changeDirection(5));
        assertEquals(MapDirection.NORTH, east.changeDirection(6));
        assertEquals(MapDirection.NORTHEAST, east.changeDirection(7));

        // For SOUTHEAST direction
        MapDirection southeast = MapDirection.SOUTHEAST;
        assertEquals(MapDirection.SOUTHEAST, southeast.changeDirection(0));
        assertEquals(MapDirection.SOUTH, southeast.changeDirection(1));
        assertEquals(MapDirection.SOUTHWEST, southeast.changeDirection(2));
        assertEquals(MapDirection.WEST, southeast.changeDirection(3));
        assertEquals(MapDirection.NORTHWEST, southeast.changeDirection(4));
        assertEquals(MapDirection.NORTH, southeast.changeDirection(5));
        assertEquals(MapDirection.NORTHEAST, southeast.changeDirection(6));
        assertEquals(MapDirection.EAST, southeast.changeDirection(7));

        // For SOUTH direction
        MapDirection south = MapDirection.SOUTH;
        assertEquals(MapDirection.SOUTH, south.changeDirection(0));
        assertEquals(MapDirection.SOUTHWEST, south.changeDirection(1));
        assertEquals(MapDirection.WEST, south.changeDirection(2));
        assertEquals(MapDirection.NORTHWEST, south.changeDirection(3));
        assertEquals(MapDirection.NORTH, south.changeDirection(4));
        assertEquals(MapDirection.NORTHEAST, south.changeDirection(5));
        assertEquals(MapDirection.EAST, south.changeDirection(6));
        assertEquals(MapDirection.SOUTHEAST, south.changeDirection(7));

        // For SOUTHWEST direction
        MapDirection southwest = MapDirection.SOUTHWEST;
        assertEquals(MapDirection.SOUTHWEST, southwest.changeDirection(0));
        assertEquals(MapDirection.WEST, southwest.changeDirection(1));
        assertEquals(MapDirection.NORTHWEST, southwest.changeDirection(2));
        assertEquals(MapDirection.NORTH, southwest.changeDirection(3));
        assertEquals(MapDirection.NORTHEAST, southwest.changeDirection(4));
        assertEquals(MapDirection.EAST, southwest.changeDirection(5));
        assertEquals(MapDirection.SOUTHEAST, southwest.changeDirection(6));
        assertEquals(MapDirection.SOUTH, southwest.changeDirection(7));

        // For WEST direction
        MapDirection west = MapDirection.WEST;
        assertEquals(MapDirection.WEST, west.changeDirection(0));
        assertEquals(MapDirection.NORTHWEST, west.changeDirection(1));
        assertEquals(MapDirection.NORTH, west.changeDirection(2));
        assertEquals(MapDirection.NORTHEAST, west.changeDirection(3));
        assertEquals(MapDirection.EAST, west.changeDirection(4));
        assertEquals(MapDirection.SOUTHEAST, west.changeDirection(5));
        assertEquals(MapDirection.SOUTH, west.changeDirection(6));
        assertEquals(MapDirection.SOUTHWEST, west.changeDirection(7));

        // For NORTHWEST direction
        MapDirection northwest = MapDirection.NORTHWEST;
        assertEquals(MapDirection.NORTHWEST, northwest.changeDirection(0));
        assertEquals(MapDirection.NORTH, northwest.changeDirection(1));
        assertEquals(MapDirection.NORTHEAST, northwest.changeDirection(2));
        assertEquals(MapDirection.EAST, northwest.changeDirection(3));
        assertEquals(MapDirection.SOUTHEAST, northwest.changeDirection(4));
        assertEquals(MapDirection.SOUTH, northwest.changeDirection(5));
        assertEquals(MapDirection.SOUTHWEST, northwest.changeDirection(6));
        assertEquals(MapDirection.WEST, northwest.changeDirection(7));
    }
}