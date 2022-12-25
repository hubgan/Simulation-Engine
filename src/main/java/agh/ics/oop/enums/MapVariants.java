package agh.ics.oop.enums;

public enum MapVariants {
    EARTHMAP,
    HELLMAP;
    public static MapVariants stringToEnum(String mapVariant) {
        return switch (mapVariant) {
            case "earth globe" -> MapVariants.EARTHMAP;
            case "infernal portal" -> MapVariants.HELLMAP;
            default -> throw new IllegalStateException("Unexpected value: " + mapVariant);
        };
    }
}

