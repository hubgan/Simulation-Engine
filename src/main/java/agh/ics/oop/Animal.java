package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private final Genotype genotype = new Genotype(1);
    private final IMap map;
    private int energy;
    private int old = 0;
    private int kids = 0;
    private int currentGenIndex = 0;

    public Animal() {
        this.direction = MapDirection.EAST;
        this.position = new Vector2d(2, 2);
        this.energy = 10;
        this.map = new EarthMap(5, 5);
    }

    public Animal(int energy, int old, int kids) {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.energy = energy;
        this.old = old;
        this.kids = kids;
        this.map = new EarthMap(5, 5);
    }

    public Animal(Vector2d position, MapDirection direction, int energy, IMap map) {
        this.map = map;
        this.position = position;
        this.direction = direction;
        this.energy = energy;
    }

    public void move() {
        Vector2d oldPosition = this.position;

        changeDirection();
        if (this.map.canMoveTo(this.position.add(this.direction.toUnitVector()))) {
            this.position = this.map.correctPosition(this.position.add(this.direction.toUnitVector()));
            positionChanged(oldPosition, this.position);
        }
    }

    public void changeDirection() {
        this.direction = this.direction.angleToDirection((this.direction.directionToAngle() +
                this.direction.getAngleFromGen(this.genotype.getGens()[this.currentGenIndex])) % 360);
        this.currentGenIndex = (this.currentGenIndex + 1) % this.genotype.getGens().length;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.map.positionChanged(oldPosition, newPosition, this);
    }

    // Getters methods

    public MapDirection getDirection() {
        return this.direction;
    }

    public int[] getGenotype() {
        return this.genotype.getGens();
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getOld() {
        return this.old;
    }

    public int getKids() {
        return this.kids;
    }

    public String toString() {
        return this.position + "," + this.direction + ", " + this.genotype;
    }
}