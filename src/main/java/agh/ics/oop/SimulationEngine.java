package agh.ics.oop;

import agh.ics.oop.gui.SimulationController;
import agh.ics.oop.gui.Variants;
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
    private int simulationTime = 0;
    private SimulationController simulationController;

    private final ArrayList<Animal> deadAnimals = new ArrayList<>();
    private final Variants variants;
    private Boolean runThread = true;

    public SimulationEngine(IMap map, Variants variants, SimulationController simulationController)  {
        this.simulationController = simulationController;
        this.map = map;
        this.variants = variants;
        this.energyNeededToCopulate = this.variants.getCopulationEnergy();
        this.numberOfPlantsGrowEveryday = this.variants.getGrowthNumber();
        this.numberOfGens = this.variants.getGenomLength();

        Vector2d mapBoundary = this.map.getMapBorders();

        for (int i = 0; i < this.variants.getAnimalsStartingNumber(); i++) {
            Vector2d position = new Vector2d(getRandomNumber(mapBoundary.x), getRandomNumber(mapBoundary.y));

            Animal animal = new Animal(position, this.variants.getAnimalStartingEnergy(), this.map, this.variants);
            animals.add(animal);
            this.map.place(animal);
        }
    }

    public SimulationEngine(IMap map, Variants variants, Vector2d[] animalPositions, MapDirection[] mapDirections)  { // For testing purposes
        this.map = map;
        this.variants = variants;
        this.energyNeededToCopulate = this.variants.getCopulationEnergy();
        this.numberOfPlantsGrowEveryday = this.variants.getGrowthNumber();
        this.numberOfGens = this.variants.getGenomLength();

        for (int i = 0; i < this.variants.getAnimalsStartingNumber(); i++) {
            Vector2d position = animalPositions[i];
            MapDirection direction = mapDirections[i];
            int[] genotype = new int[]{0};
            Animal animal = new Animal(position, this.variants.getAnimalStartingEnergy(), this.map,
                    genotype, this.variants, direction);
            animals.add(animal);
            this.map.place(animal);
        }
    }

    @Override
    public void run() {
        if (simulationController == null) {
            checkDead();
            moveAnimals();
            eatPlants();
            createYoungAnimal();
            growPlants();
            incrementSimulationTime();
            System.out.println(this.map);
        }
        else {
            while (animals.size() > 0 && simulationController.getIsStarted()) {
                checkDead();
                moveAnimals();
                eatPlants();
                createYoungAnimal();
                growPlants();
                incrementSimulationTime();
                Platform.runLater(simulationController::updateChart);
                Platform.runLater(simulationController::renderGridPane);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
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
            deadAnimal.setDeadDay(this.simulationTime);
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
            animal.increaseOld();
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

            if (animalsList.size() > 0) {
                animalsList.sort(new CustomComparator());
            }

            if (animalsList.size() > 1 && animalsList.get(1).getEnergy() > this.energyNeededToCopulate) {
                Animal strongerAnimal = animalsList.get(0);
                Animal weakerAnimal = animalsList.get(1);

                strongerAnimal.increaseKids();
                weakerAnimal.increaseKids();

                int lowerPercent = weakerAnimal.getEnergy() * 100 / (weakerAnimal.getEnergy() + strongerAnimal.getEnergy());
                int higherPercent = 100 - lowerPercent;
                int newAnimalEnergy = (lowerPercent * weakerAnimal.getEnergy() / 100) +
                        (strongerAnimal.getEnergy() * higherPercent / 100);

                strongerAnimal.decreaseEnergy(variants.getCopulationEnergyLost());
                weakerAnimal.decreaseEnergy(variants.getCopulationEnergyLost());

                int midPoint = this.numberOfGens * higherPercent / 100;

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

    private void incrementSimulationTime() {
        this.simulationTime += 1;
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }

    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    public ArrayList<Animal> getDeadAnimals() {
        return this.deadAnimals;
    }

    public int getSimulationTime() {
        return this.simulationTime;
    }
}
