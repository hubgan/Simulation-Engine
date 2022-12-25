package agh.ics.oop;

import agh.ics.oop.enums.BehaviourVariants;
import agh.ics.oop.enums.MapVariants;
import agh.ics.oop.enums.MutationVariants;

import java.util.Map;

public class Variants {
    private final int width;
    private final int height;
    private  String mapVariant = null;
    private  MapVariants mapVariant2 = MapVariants.HELLMAP;
    private  String mutationVariant = "test";
    private  MutationVariants mutationVariant2 = MutationVariants.FULLRANDOM;
    private  String behaviourVariant = "test";
    private  BehaviourVariants behaviourVariant2 = BehaviourVariants.MADNESS;
    private final int plantEnergyGain;
    private final int plantsStartingNumber;
    private final int growthNumber;
    private final int animalsStartingNumber;
    private final int startingEnergyOfAnimals;
    private final int energyNeededToCopulation;
    private final int minimumNumberOfMutations;
    private final int maximumNumberOfMutations;
    private final int numberOfGens;
    private final int energyLost;







    public Variants(int width, int height, String mapVariant, String mutationVariant, String behaviourVariant
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
    }
    public Variants(Map<String,String> map) {
        this.width = Integer.parseInt(map.get("width")) ;
        this.height = Integer.parseInt(map.get("height"));
        this.mapVariant2 = MapVariants.stringToEnum(map.get("mapVariant"));
        this.mutationVariant2 = MutationVariants.stringToEnum(map.get("mutationVariant"));
        this.behaviourVariant2 = BehaviourVariants.stringToEnum(map.get("animalsVariant"));
        this.plantEnergyGain = Integer.parseInt(map.get("energyGain"));
        this.plantsStartingNumber = Integer.parseInt(map.get("plantsStartingNumber"));
        this.growthNumber = Integer.parseInt(map.get("growthNumber"));
        this.startingEnergyOfAnimals = Integer.parseInt(map.get("startingEnergy"));
        this.animalsStartingNumber = Integer.parseInt(map.get("animalStartingNumber"));
        this.energyNeededToCopulation = Integer.parseInt(map.get("copulationEnergy"));
        this.minimumNumberOfMutations = Integer.parseInt(map.get("minMutations"));
        this.maximumNumberOfMutations = Integer.parseInt(map.get("maxMutations"));
        this.numberOfGens = Integer.parseInt(map.get("genomLength"));
        this.energyLost = 1;

    }
    

    public MapVariants getMapVariant() {
        return switch (this.mapVariant) {
            case "EarthMap" -> MapVariants.EARTHMAP;
            case "HellMap" -> MapVariants.HELLMAP;
            default -> MapVariants.EARTHMAP;
        };
    }

    public MutationVariants getMutationVariant() {
        return switch (this.mutationVariant) {
            case "pełna losowość" -> MutationVariants.FULLRANDOM;
            case "lekka korekta" -> MutationVariants.SLIGHTCHANGES;
            default -> MutationVariants.FULLRANDOM;
        };
    }

    public BehaviourVariants getBehaviourVariant() {
        return switch (this.behaviourVariant) {
            case "pełna predestynacja" -> BehaviourVariants.PREDESTINATION;
            case "nieco szaleństwa" -> BehaviourVariants.MADNESS;
            default -> BehaviourVariants.PREDESTINATION;
        };
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

    public int getStartingEnergyOfAnimals() {
        return this.startingEnergyOfAnimals;
    }

    public int getEnergyNeededToCopulation() {
        return this.energyNeededToCopulation;
    }

    public int getMinimumNumberOfMutations() {
        return this.minimumNumberOfMutations;
    }

    public int getMaximumNumberOfMutations() {
        return this.maximumNumberOfMutations;
    }

    public int getNumberOfGens() {
        return this.numberOfGens;
    }

    public int getEnergyLost() {
        return this.energyLost;
    }
}
