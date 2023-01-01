package agh.ics.oop.gui;

import agh.ics.oop.enums.BehaviourVariants;
import agh.ics.oop.enums.MapVariants;
import agh.ics.oop.enums.MutationVariants;
import agh.ics.oop.enums.PlantsVariants;

import java.util.Map;

public class Variants {
    private final int width;
    private final int height;
    private final MapVariants mapVariant;
    private final MutationVariants mutationVariant;
    private final BehaviourVariants behaviourVariant;
    private final PlantsVariants plantsVariant;
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

    public Variants(Map<String,String> map) {
        System.out.println(map);
        this.width = Integer.parseInt(map.get("width")) ;
        this.height = Integer.parseInt(map.get("height"));
        this.mapVariant = MapVariants.stringToEnum(map.get("mapVariant"));
        this.mutationVariant = MutationVariants.stringToEnum(map.get("mutationVariant"));
        this.behaviourVariant = BehaviourVariants.stringToEnum(map.get("animalsVariant"));
        this.plantsVariant = PlantsVariants.stringToEnum(map.get("plantsVariant"));
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

    public PlantsVariants getPlantsVariant() {
        return plantsVariant;
    }
}
