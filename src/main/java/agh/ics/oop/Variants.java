package agh.ics.oop;

public class Variants {
    private final int mapWidth;
    private final int mapHeight;
    private final String mapVariant;
    private final int plantEnergyGain;
    private final int amountOfPlantsGrowEveryDay;
    private final int startingNumberOfAnimals;
    private final int startingEnergyOfAnimals;
    private final int energyNeededToCopulation;
    private final int minimumNumberOfMutations;
    private final int maximumNumberOfMutations;
    private final int numberOfGens;

    enum MapVariants {
        EARTHMAP,
        HELLMAP
    }

    public Variants(int width, int height, String mapVariant, int plantEnergyGain, int amountOfPlantsGrowEveryDay,
                    int startingNumberOfAnimals, int startingEnergyOfAnimals, int energyNeededToCopulation,
                    int minimumNumberOfMutations, int maximumNumberOfMutations, int numberOfGens) {
        this.mapWidth = width;
        this.mapHeight = height;
        this.mapVariant = mapVariant;
        this.plantEnergyGain = plantEnergyGain;
        this.amountOfPlantsGrowEveryDay = amountOfPlantsGrowEveryDay;
        this.startingEnergyOfAnimals = startingEnergyOfAnimals;
        this.startingNumberOfAnimals = startingNumberOfAnimals;
        this.energyNeededToCopulation = energyNeededToCopulation;
        this.minimumNumberOfMutations = minimumNumberOfMutations;
        this.maximumNumberOfMutations = maximumNumberOfMutations;
        this.numberOfGens = numberOfGens;
    }

    public MapVariants getMapVariant() {
        return switch (this.mapVariant) {
            case "EarthMap" -> MapVariants.EARTHMAP;
            case "HellMap" -> MapVariants.HELLMAP;
            default -> MapVariants.EARTHMAP;
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
}
