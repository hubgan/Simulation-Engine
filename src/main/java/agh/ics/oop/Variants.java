package agh.ics.oop;

import agh.ics.oop.enums.BehaviourVariants;
import agh.ics.oop.enums.MapVariants;
import agh.ics.oop.enums.MutationVariants;

public class Variants {
    private final int mapWidth;
    private final int mapHeight;
    private final String mapVariant;
    private final String mutationVariant;
    private final String behaviourVariant;
    private final int plantEnergyGain;
    private final int startingNumberOfPlants;
    private final int amountOfPlantsGrowEveryDay;
    private final int startingNumberOfAnimals;
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
        this.mapWidth = width;
        this.mapHeight = height;
        this.mapVariant = mapVariant;
        this.mutationVariant = mutationVariant;
        this.behaviourVariant = behaviourVariant;
        this.plantEnergyGain = plantEnergyGain;
        this.startingNumberOfPlants = startingNumberOfPlants;
        this.amountOfPlantsGrowEveryDay = amountOfPlantsGrowEveryDay;
        this.startingEnergyOfAnimals = startingEnergyOfAnimals;
        this.startingNumberOfAnimals = startingNumberOfAnimals;
        this.energyNeededToCopulation = energyNeededToCopulation;
        this.minimumNumberOfMutations = minimumNumberOfMutations;
        this.maximumNumberOfMutations = maximumNumberOfMutations;
        this.numberOfGens = numberOfGens;
        this.energyLost = energyLost;
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
            case "nieco szaleństwa" -> BehaviourVariants.CRAZY;
            default -> BehaviourVariants.PREDESTINATION;
        };
    }

    public int getWidth() {
        return this.mapWidth;
    }

    public int getHeight() {
        return this.mapHeight;
    }

    public int getPlantEnergyGain() {
        return this.plantEnergyGain;
    }

    public int getStartingNumberOfPlants() {
        return this.startingNumberOfPlants;
    }

    public int getAmountOfPlantsGrowEveryDay() {
        return this.amountOfPlantsGrowEveryDay;
    }

    public int getStartingNumberOfAnimals() {
        return this.startingNumberOfAnimals;
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
