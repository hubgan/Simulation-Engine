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

    Genotype(int numberOfGens, int[] firstGenotype, int[] secondGenotype, int midPoint, Variants variants) {
        this.gens = new int[numberOfGens];

        for (int i = 0; i < midPoint; i++) {
            this.gens[i] = secondGenotype[i];
        }

        for (int i = midPoint; i < numberOfGens; i++) {
            this.gens[i] = firstGenotype[i];
        }

        mutation(variants);
    }

    private void mutation(Variants variants) {
        switch (variants.getMutationVariant()) {
            case FULLRANDOM -> {

                int randomNumber = generateRandomNumber(variants.getMaximumNumberOfMutations(), variants.getMinimumNumberOfMutations());
                for (int i = 0; i < randomNumber; i++) {
                    int randomGen = generateRandomNumber(this.gens.length - 1, 0);
                    int oldGen = this.gens[randomGen];

                    while (this.gens[randomGen] == oldGen) {
                        this.gens[randomGen] = generateRandomNumber(7, 0);
                    }
                }
            }
            case SLIGHTCHANGES -> {
                int randomNumber = generateRandomNumber(variants.getMaximumNumberOfMutations(), variants.getMinimumNumberOfMutations());

                for (int i = 0; i < randomNumber; i++) {
                    int randomGen = generateRandomNumber(this.gens.length - 1, 0);
                    if (generateRandomNumber(2, 0) == 0) {
                        this.gens[randomGen] = Math.floorMod(this.gens[randomGen] + 1, this.gens.length);
                    }
                    else {
                        this.gens[randomGen] = Math.floorMod(this.gens[randomGen] - 1, this.gens.length);
                    }
                }
            }
        }
    }

    private int generateRandomNumber(int max, int min) {
        return random.nextInt(max + 1 - min) + min;
    }

    public int[] getGens() {
        return this.gens;
    }

    @Override
    public String toString() {
        return Arrays.toString(gens);
    }
}
