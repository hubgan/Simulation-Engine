package agh.ics.oop;


public class World {
    public static void main(String[] args) {


        Variants variants = new Variants(5, 5, "HellMap", 1, 1
                , 4, 10, 10, 0,
                2, 5);
        IMap map = createMap(variants);
        SimulationEngine engine = new SimulationEngine(map, 4, 10, variants);
        System.out.println(map);
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
        engine.run();
        System.out.println(map.getAnimals());
        System.out.println(map.getPlants());
    }

    private static IMap createMap(Variants variants) {
        return switch (variants.getMapVariant()) {
            case EARTHMAP -> new EarthMap(variants.getWidth(), variants.getHeight());
            case HELLMAP -> new HellMap(variants.getWidth(), variants.getHeight());
            default -> new EarthMap(variants.getWidth(), variants.getHeight());
        };
    }
}
