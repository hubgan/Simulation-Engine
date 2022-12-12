package agh.ics.oop;

import java.util.Arrays;
import java.util.Random;

public class Genotype {
    private final int[] gens;
    private final Random random = new Random();

    Genotype(int numberOfGens) {
        this.gens = new int[numberOfGens];

        for (int i = 0; i < numberOfGens; i++) {
            this.gens[i] = this.random.nextInt(7);
        }
    }

    Genotype(int numberOfGens, int[] firstGenotype, int[] secondGenotype, int midPoint) {
        this.gens = new int[numberOfGens];

        for (int i = 0; i < midPoint; i++) {
            this.gens[i] = secondGenotype[i];
        }

        for (int i = midPoint; i < numberOfGens; i++) {
            this.gens[i] = firstGenotype[i];
        }
    }

    public int[] getGens() {
        return this.gens;
    }

    @Override
    public String toString() {
        return Arrays.toString(gens);
    }
}
