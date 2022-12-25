package agh.ics.oop;

import agh.ics.oop.enums.BehaviourVariants;
import agh.ics.oop.enums.MapVariants;
import agh.ics.oop.enums.MutationVariants;

import java.util.Map;

public class Variants {
    private final int width;
    private final int height;
    /*private  String mapVariant = null;*/
    private final MapVariants mapVariant;
    /*private  String mutationVariant = "test";*/
    private final MutationVariants mutationVariant;
    /*private  String behaviourVariant = "test";*/
    private final BehaviourVariants behaviourVariant;
    private final int plantEnergyGain;
    private final int plantsStartingNumber;
    private final int growthNumber;
    private final int animalsStartingNumber;
    private final int animalStartingEnergy;
    private final int copulationEnergy;
    private final int minMutations;
    private final int maxMutations;
    private final int genomLength;
    private final int energyLost;
    private final int copulationEnergyLost;







   /* public Variants(int width, int height, String mapVariant, String mutationVariant, String behaviourVariant
            , int plantEnergyGain, int startingNumberOfPlants,
                    int amountOfPlantsGrowEveryDay,
                    int startingNumberOfAnimals, int startingEnergyOfAnimals, int energyNeededToCopulation,
                    int minimumNumberOfMutations, int maximumNumberOfMutations, int numberOfGens, int energyLost) {
        this.width = width;
        this.height = height;
        this.mapVariant = mapVariant;
        this.mutationVariant = mutationVariant;
        this.behaviourVariant = behaviourVariant;
        this.plantEnergyGain = plantEnergyGain;
        this.plantsStartingNumber = startingNumberOfPlants;
        this.growthNumber = amountOfPlantsGrowEveryDay;
        this.startingEnergyOfAnimals = startingEnergyOfAnimals;
        this.animalsStartingNumber = startingNumberOfAnimals;
        this.energyNeededToCopulation = energyNeededToCopulation;
        this.minimumNumberOfMutations = minimumNumberOfMutations;
        this.maximumNumberOfMutations = maximumNumberOfMutations;
        this.numberOfGens = numberOfGens;
        this.energyLost = energyLost;
    }*/
    public Variants(Map<String,String> map) {
        this.width = Integer.parseInt(map.get("width")) ;
        this.height = Integer.parseInt(map.get("height"));
        this.mapVariant = MapVariants.stringToEnum(map.get("mapVariant"));
        this.mutationVariant = MutationVariants.stringToEnum(map.get("mutationVariant"));
        this.behaviourVariant = BehaviourVariants.stringToEnum(map.get("animalsVariant"));
        this.plantEnergyGain = Integer.parseInt(map.get("energyGain"));
        this.plantsStartingNumber = Integer.parseInt(map.get("plantsStartingNumber"));
        this.growthNumber = Integer.parseInt(map.get("growthNumber"));
        this.animalStartingEnergy = Integer.parseInt(map.get("startingEnergy"));
        this.animalsStartingNumber = Integer.parseInt(map.get("animalStartingNumber"));
        this.copulationEnergy = Integer.parseInt(map.get("copulationEnergy"));
        this.minMutations = Integer.parseInt(map.get("minMutations"));
        this.maxMutations = Integer.parseInt(map.get("maxMutations"));
        this.genomLength = Integer.parseInt(map.get("genomLength"));
        this.copulationEnergyLost = Integer.parseInt(map.get("copulationEnergyLoss"));
        this.energyLost = Integer.parseInt(map.get("moveEnergyLoss"));
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getPlantEnergyGain() {
        return this.plantEnergyGain;
    }

    public int getPlantsStartingNumber() {
        return this.plantsStartingNumber;
    }

    public int getGrowthNumber() {
        return this.growthNumber;
    }

    public int getAnimalsStartingNumber() {
        return this.animalsStartingNumber;
    }

    public int getAnimalStartingEnergy() {
        return this.animalStartingEnergy;
    }

    public int getCopulationEnergy() {
        return this.copulationEnergy;
    }

    public int getMinMutations() {
        return this.minMutations;
    }

    public int getMaxMutations() {
        return this.maxMutations;
    }

    public int getGenomLength() {
        return this.genomLength;
    }

    public int getEnergyLost() {
        return this.energyLost;
    }

    public int getCopulationEnergyLost() {
        return copulationEnergyLost;
    }

    public BehaviourVariants getBehaviourVariant() {
        return behaviourVariant;
    }

    public MapVariants getMapVariant() {
        return mapVariant;
    }

    public MutationVariants getMutationVariant() {
        return mutationVariant;
    }
}
