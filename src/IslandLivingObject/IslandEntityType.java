package src.IslandLivingObject;

import java.util.HashMap;
import java.util.Map;

public enum IslandEntityType {
    WOLF(3, 30, 8, 50, initEdibleSpecies()),
    PYTHON(1, 30, 3, 15, initEdibleSpecies()),
    FOX(2, 30, 2, 8, initEdibleSpecies()),
    BEAR(2, 5, 80, 500, initEdibleSpecies()),
    EAGLE(3, 20, 1, 6, initEdibleSpecies()),
    HORSE(4, 20, 60, 400, initEdibleSpecies()),
    DEER(4, 20, 50, 300, initEdibleSpecies()),
    RABBIT(2, 150, 0.45, 2, initEdibleSpecies()),
    MOUSE(1, 500, 0.01, 0.05, initEdibleSpecies()),
    GOAT(3, 140, 10, 60, initEdibleSpecies()),
    SHEEP(3, 140, 15, 70, initEdibleSpecies()),
    WILD_BOAR(2, 50, 50, 400, initEdibleSpecies()),
    BUFFALO(3, 10, 100, 700, initEdibleSpecies()),
    DUCK(4, 1000, 0.45, 1.0, initEdibleSpecies()),
    CATERPILLAR(0, 1000, 0, 0, initEdibleSpecies()),
    PLANT(0, 200, 0, 0, initEdibleSpecies());

    private final int maxMove;
    private final int maxAmount;
    private final double fullSaturation;
    private final double weight;

    public final  Map<IslandEntityType, Integer> edibleSpecies;

    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
    }


    public double getFullSaturation() {
        return fullSaturation;
    }

    public double getWeight() {
        return weight;
    }

    IslandEntityType(int maxMove, int maxAmount, double fullSaturation, double weight, Map<IslandEntityType, Integer> edibleSpecies) {
        this.maxMove = maxMove;
        this.maxAmount = maxAmount;
        this.fullSaturation = fullSaturation;
        this.weight = weight;
        this.edibleSpecies = initEdibleSpecies();
    }

    public int getMaxMove() {
        return maxMove;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public static Map<IslandEntityType, Integer> initEdibleSpecies() {
        Map<IslandEntityType, Integer> islandEntityTypeIntegerMap = new HashMap<>();


        return islandEntityTypeIntegerMap;
    }
}
