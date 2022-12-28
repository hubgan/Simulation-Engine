package agh.ics.oop.enums;

public enum PlantsVariants {
    FORESTEDEQUATORS,
    TOXICCORPSES;

    public static PlantsVariants stringToEnum(String plantsVariant) {
        return switch (plantsVariant) {
            case "forested equatorial" -> PlantsVariants.FORESTEDEQUATORS;
            case "toxic corpses" -> PlantsVariants.TOXICCORPSES;
            default -> throw new IllegalStateException("Unexpected value: " + plantsVariant);
        };
    }
}
