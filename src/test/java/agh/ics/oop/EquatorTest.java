package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import agh.ics.oop.gui.Variants;
import agh.ics.oop.maps.Equator;
import agh.ics.oop.utils.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EquatorTest {
    @Test
    public void testEquatorGenerating() {
        Map<String, String> variantsMap = new HashMap<>();
        variantsMap.put("width", "5");
        variantsMap.put("height", "5");
        variantsMap.put("mapVariant", "earth globe");
        variantsMap.put("mutationVariant", "slight correction");
        variantsMap.put("animalsVariant", "full predestination");
        variantsMap.put("plantsVariant", "forested equatorial");
        variantsMap.put("energyGain", "2");
        variantsMap.put("plantsStartingNumber", "0");
        variantsMap.put("growthNumber", "0");
        variantsMap.put("animalStartingNumber", "1");
        variantsMap.put("startingEnergy", "50");
        variantsMap.put("copulationEnergy", "3");
        variantsMap.put("minMutations", "0");
        variantsMap.put("maxMutations", "2");
        variantsMap.put("genomLength", "1");
        variantsMap.put("moveEnergyLoss", "2");
        variantsMap.put("copulationEnergyLoss", "4");
        Variants variants = new Variants(variantsMap);
        Equator equator = new Equator(variants.getWidth(), variants.getHeight(), variants);
        ArrayList<Vector2d> freeEquatorPositions = equator.getFreeEquatorPositions();
        Vector2d[] assumedFreeEquatorPositions = new Vector2d[]{
                new Vector2d(0, 1),
                new Vector2d(1, 1),
                new Vector2d(2, 1),
                new Vector2d(3, 1),
                new Vector2d(4, 1),
                new Vector2d(0, 2),
                new Vector2d(1, 2),
                new Vector2d(2, 2),
                new Vector2d(3, 2),
                new Vector2d(4, 2),
        };

        assertEquals(assumedFreeEquatorPositions.length, freeEquatorPositions.size());
        for (Vector2d position: assumedFreeEquatorPositions) {
            assertTrue(freeEquatorPositions.contains(position));
        }

        variantsMap.replace("height", "20");
        variants = new Variants(variantsMap);
        equator = new Equator(variants.getWidth(), variants.getHeight(), variants);
        freeEquatorPositions = equator.getFreeEquatorPositions();
        System.out.println(freeEquatorPositions);
        assumedFreeEquatorPositions = new Vector2d[]{
                new Vector2d(0, 6),
                new Vector2d(0, 7),
                new Vector2d(0, 8),
                new Vector2d(0, 9),
                new Vector2d(0, 10),
                new Vector2d(0, 11),
                new Vector2d(0, 12),
                new Vector2d(1, 6),
                new Vector2d(1, 7),
                new Vector2d(1, 8),
                new Vector2d(1, 9),
                new Vector2d(1, 10),
                new Vector2d(1, 11),
                new Vector2d(1, 12),
                new Vector2d(2, 6),
                new Vector2d(2, 7),
                new Vector2d(2, 8),
                new Vector2d(2, 9),
                new Vector2d(2, 10),
                new Vector2d(2, 11),
                new Vector2d(2, 12),
                new Vector2d(3, 6),
                new Vector2d(3, 7),
                new Vector2d(3, 8),
                new Vector2d(3, 9),
                new Vector2d(3, 10),
                new Vector2d(3, 11),
                new Vector2d(3, 12),
                new Vector2d(4, 6),
                new Vector2d(4, 7),
                new Vector2d(4, 8),
                new Vector2d(4, 9),
                new Vector2d(4, 10),
                new Vector2d(4, 11),
                new Vector2d(4, 12),
        };

        assertEquals(assumedFreeEquatorPositions.length, freeEquatorPositions.size());
        for (Vector2d position: assumedFreeEquatorPositions) {
            assertTrue(freeEquatorPositions.contains(position));
        }
    }
}