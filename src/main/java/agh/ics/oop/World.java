package agh.ics.oop;


public class World {
    public static void main(String[] args) {


        Variants variants = new Variants(5, 5, "HellMap", "pełna losowość",
                "nieco szaleństwa", 1, 2, 1
                , 4, 50, 5, 0,
                2, 5, 30);
        IMap map = createMap(variants);
        SimulationEngine engine = new SimulationEngine(map, variants);
        System.out.println(map);
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
//        System.out.println(map.getAnimals());
//        System.out.println(map.getPlants());
//        engine.run();
//        System.out.println(map.getAnimals());
//        System.out.println(map.getPlants());
//        engine.run();
//        System.out.println(map.getAnimals());
//        System.out.println(map.getPlants());
    }

    private static IMap createMap(Variants variants) {
        return switch (variants.getMapVariant()) {
            case EARTHMAP -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
            case HELLMAP -> new HellMap(variants.getWidth(), variants.getHeight(), variants);
            default -> new EarthMap(variants.getWidth(), variants.getHeight(), variants);
        };
    }
}
