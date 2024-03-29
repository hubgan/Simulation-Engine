package agh.ics.oop.map_elements;

import agh.ics.oop.gui.Variants;

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

    Genotype(int[] genotype) { // For testing purposes
        this.gens = genotype;
    }

    Genotype(int numberOfGens, int[] firstGenotype, int[] secondGenotype, int midPoint, Variants variants) {
        this.gens = new int[numberOfGens];

        if (midPoint >= 0) System.arraycopy(firstGenotype, 0, this.gens, 0, midPoint);

        if (numberOfGens - midPoint >= 0)
            System.arraycopy(secondGenotype, midPoint, this.gens, midPoint, numberOfGens - midPoint);

        mutation(variants);
    }

    public Genotype(int numberOfGens, int[] firstGenotype, int[] secondGenotype, int midPoint, Variants variants, boolean isTest) {
        // For Test cases
        System.out.println(isTest);
        System.out.println(variants);
        this.gens = new int[numberOfGens];

        if (midPoint >= 0) System.arraycopy(firstGenotype, 0, this.gens, 0, midPoint);

        if (numberOfGens - midPoint >= 0)
            System.arraycopy(secondGenotype, midPoint, this.gens, midPoint, numberOfGens - midPoint);
    }

    private void mutation(Variants variants) {
        switch (variants.getMutationVariant()) {
            case FULLRANDOM -> {
                int randomNumber = generateRandomNumber(variants.getMaxMutations(), variants.getMinMutations());
                for (int i = 0; i < randomNumber; i++) {
                    int randomGen = generateRandomNumber(this.gens.length - 1, 0);
                    int oldGen = this.gens[randomGen];

                    while (this.gens[randomGen] == oldGen) { // change until gen is different
                        this.gens[randomGen] = generateRandomNumber(7, 0);
                    }
                }
            }
            case SLIGHTCHANGES -> {
                int randomNumber = generateRandomNumber(variants.getMaxMutations(), variants.getMinMutations());

                for (int i = 0; i < randomNumber; i++) {
                    int randomGen = generateRandomNumber(this.gens.length - 1, 0);

                    int randomOfTwoInt = new Random().nextBoolean() ? -1 : 1;
                    this.gens[randomGen] = Math.floorMod(this.gens[randomGen] + randomOfTwoInt, 8);
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
        return Arrays.toString(gens); }
}
