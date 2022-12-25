package agh.ics.oop.enums;

public enum BehaviourVariants {
    PREDESTINATION,
    MADNESS;
    public static BehaviourVariants stringToEnum(String behaviourVariant) {
        return switch (behaviourVariant) {
            case "full predestination" -> BehaviourVariants.PREDESTINATION;
            case "bit of madness" -> BehaviourVariants.MADNESS;
            default -> throw new IllegalStateException("Unexpected value: " + behaviourVariant);
        };
    }
}
