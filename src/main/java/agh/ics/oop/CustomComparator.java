package agh.ics.oop;

import java.util.Comparator;
import java.util.Random;

// Comparator used to sorting ArrayList of animals.

public class CustomComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal a1, Animal a2) {
        if (a1.getEnergy() != a2.getEnergy()) {
            return a2.getEnergy() - a1.getEnergy();
        }

        if (a1.getOld() != a2.getOld()) {
            return a2.getOld() - a1.getOld();
        }

        if (a1.getKids() != a2.getKids()) {
            return a2.getKids() - a1.getKids();
        }

        return new Random().ints(1, -1, 2).findFirst().getAsInt();
    }
}
