package agh.ics.oop;

import agh.ics.oop.gui.Variants;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GenotypeTest {
    @Test
    void testCreatingGenotypeFromParents() {
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
        variantsMap.put("copulationEnergy", "5");
        variantsMap.put("minMutations", "0");
        variantsMap.put("maxMutations", "2");
        variantsMap.put("genomLength", "10");
        variantsMap.put("moveEnergyLoss", "2");
        variantsMap.put("copulationEnergyLoss", "10");
        Variants variants = new Variants(variantsMap);

        int[] strongerGenotype = new int[]{0, 2, 4, 6, 1, 3, 5, 7, 2, 4};
        int[] weakerGenotype = new int[]{1, 3, 7, 2, 5, 4, 7, 3, 1, 2};

        // assume that the animal with the stronger genotype contains 70% of the total energy from both animals
        // stronger animal energy: 70, weaker animal energy: 30
        int midPoint = 7;
        int[] assumingGenotypeWhenLeftSideTaken = new int[]{0, 2, 4, 6, 1, 3, 5, 3, 1, 2};
        int[] assumingGenotypeWhenRightSideTaken = new int[]{1, 3, 7, 6, 1, 3, 5, 7, 2, 4};

        // Left side from stronger genotype taken
        Genotype firstCaseGenotype = new Genotype(variants.getGenomLength(), strongerGenotype, weakerGenotype, midPoint,
                variants, true);

        // Right side from stronger genotype taken
        Genotype secondCaseGenotype = new Genotype(variants.getGenomLength(), weakerGenotype, strongerGenotype,
                variants.getGenomLength() - midPoint, variants, true);

        for (int i = 0; i < 10; i++) {
            assertEquals(assumingGenotypeWhenLeftSideTaken[i], firstCaseGenotype.getGens()[i]);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(assumingGenotypeWhenRightSideTaken[i], secondCaseGenotype.getGens()[i]);
        }
    }
}