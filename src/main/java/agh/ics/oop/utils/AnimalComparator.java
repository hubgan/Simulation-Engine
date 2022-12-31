package agh.ics.oop.utils;

import agh.ics.oop.map_elements.Animal;

import java.util.Comparator;
import java.util.Random;

// Comparator used to sorting ArrayList of animals

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal a1, Animal a2) {
        if (a1.getEnergy() != a2.getEnergy()) {
            return a2.getEnergy() - a1.getEnergy();
        }

        if (a1.getAge() != a2.getAge()) {
            return a2.getAge() - a1.getAge();
        }

        if (a1.getChildren() != a2.getChildren()) {
            return a2.getChildren() - a1.getChildren();
        }

        return new Random().ints(1, -1, 2).findFirst().getAsInt();
    }
}
