package agh.ics.oop;

import agh.ics.oop.gui.SimulationController;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimulationEngine implements IEngine, Runnable {
    private final IMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Random random = new Random();
    private final int energyNeededToCopulate;
    private final int numberOfPlantsGrowEveryday;
    private final int numberOfGens;
    private SimulationController simulationController;

    private final ArrayList<Animal> deadAnimals = new ArrayList<>();
    private final Variants variants;
    private Boolean runThread = true;

    public SimulationEngine(IMap map, Variants variants, SimulationController simulationController)  {
        this.simulationController = simulationController;
        this.map = map;
        this.variants = variants;
        this.energyNeededToCopulate = this.variants.getEnergyNeededToCopulation();
        this.numberOfPlantsGrowEveryday = this.variants.getGrowthNumber();
        this.numberOfGens = this.variants.getNumberOfGens();

        Vector2d mapBoundary = this.map.getMapBorders();

        for (int i = 0; i < this.variants.getAnimalsStartingNumber(); i++) {
            Vector2d position = new Vector2d(getRandomNumber(mapBoundary.x), getRandomNumber(mapBoundary.y));

            Animal animal = new Animal(position, this.variants.getStartingEnergyOfAnimals(), this.map, this.variants);
            animals.add(animal);
            this.map.place(animal);
        }
    }

    @Override
    public void run() {
        while (animals.size() > 0 && runThread) {
            checkDead();
            moveAnimals();
            eatPlants();
            createYoungAnimal();
            growPlants();
            System.out.println(this.map);
            Platform.runLater(simulationController::renderGridPane);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void changeThreadState() {
        this.runThread = !this.runThread;
    }

    private void checkDead() {
        ArrayList<Animal> toRemove = new ArrayList<>();
        for (Animal animal : this.animals) {
            if (animal.getEnergy() <= 0) {
                this.deadAnimals.add(animal);
                toRemove.add(animal);
            }

        }
        for (Animal deadAnimal : toRemove) {
            this.animals.remove(deadAnimal);
            this.map.deadAnimal(deadAnimal);
            if ((this.map.getGarden()) instanceof ToxicCorpses) {
                ((ToxicCorpses) this.map.getGarden()).deadAnimal(deadAnimal.getPosition());
            }
        }

    }

    private void moveAnimals() {
        for (Animal animal : this.animals) {
            animal.move();
            animal.decreaseEnergy(this.variants.getEnergyLost());
        }
    }

    private void eatPlants() {
        HashMap<Vector2d, ArrayList<Animal>> animalsMap = this.map.getAnimals();
        HashMap<Vector2d, Plant> plantsPositions = this.map.getPlants();

        for (Vector2d position : animalsMap.keySet()) {
            if (plantsPositions.containsKey(position)) {
                ArrayList<Animal> animalsList = animalsMap.get(position);
                animalsList.sort(new CustomComparator());
                Animal animal = animalsList.get(0);
                animal.eat(plantsPositions.get(position));
                this.map.eatPlant(position);
            }
        }
    }

    private void createYoungAnimal() {
        HashMap<Vector2d, ArrayList<Animal>> animalsMap = this.map.getAnimals();

        for (Vector2d position : animalsMap.keySet()) {
            ArrayList<Animal> animalsList = animalsMap.get(position);
            animalsList.sort(new CustomComparator());

            if (animalsList.size() > 1 && animalsList.get(0).getEnergy() > this.energyNeededToCopulate) {
                Animal strongerAnimal = animalsList.get(0);
                Animal weakerAnimal = animalsList.get(1);

                int lowerPercent = weakerAnimal.getEnergy() * 100 / (weakerAnimal.getEnergy() + strongerAnimal.getEnergy());
                int higherPercent = 100 - lowerPercent;
                int newAnimalEnergy = (lowerPercent * weakerAnimal.getEnergy() / 100) +
                        (strongerAnimal.getEnergy() * higherPercent / 100);

                strongerAnimal.decreaseEnergy(higherPercent * strongerAnimal.getEnergy() / 100);
                weakerAnimal.decreaseEnergy(lowerPercent * weakerAnimal.getEnergy() / 100);

                int midPoint = this.numberOfGens * lowerPercent / 100;

                Animal youngAnimal = new Animal(position, this.map,
                        newAnimalEnergy, strongerAnimal.getGenotype(), weakerAnimal.getGenotype(), midPoint,
                        this.variants);

                this.animals.add(youngAnimal);
                this.map.place(youngAnimal);
            }
        }
    }

    private void growPlants() {
        this.map.generateRandomNumberOfPlants(this.numberOfPlantsGrowEveryday);
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
