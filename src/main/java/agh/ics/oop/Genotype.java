package agh.ics.oop;

import java.util.Random;
import java.util.stream.IntStream;

public class Genotype {
    private final int[] gens;
    private final Random random = new Random();

    Genotype(int numberOfGens) {
        this.gens = new int[numberOfGens];
        IntStream.range(0, this.gens.length).forEach(i -> this.gens[i] = this.random.nextInt(7));
    }

    public int[] getGens() {
        return this.gens;
    }
}
