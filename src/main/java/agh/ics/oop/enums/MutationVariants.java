package agh.ics.oop.enums;

public enum MutationVariants {
    FULLRANDOM,
    SLIGHTCHANGES;
    public static MutationVariants stringToEnum(String mutationVariant) {
        return switch (mutationVariant) {
            case "full randomness" -> MutationVariants.FULLRANDOM;
            case "slight correction" -> MutationVariants.SLIGHTCHANGES;
            default -> throw new IllegalStateException("Unexpected value: " + mutationVariant);
        };
    }
}
