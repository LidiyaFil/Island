package src.IslandLivingObject;

import java.util.HashMap;
import java.util.Map;

public enum IslandEntityType {
    WOLF(3, 3, 8, 50, initEdibleSpecies()),
    PYTHON(1, 3, 3, 15, initEdibleSpecies()),
    FOX(2, 3, 2, 8, initEdibleSpecies()),
    BEAR(2, 5, 80, 500, initEdibleSpecies()),
    EAGLE(3, 2, 1, 6, initEdibleSpecies()),
    HORSE(4, 2, 60, 400, initEdibleSpecies()),
    DEER(4, 2, 50, 300, initEdibleSpecies()),
    RABBIT(2, 5, 0.45, 2, initEdibleSpecies()),
    MOUSE(1, 5, 0.01, 0.05, initEdibleSpecies()),
    GOAT(3, 5, 10, 60, initEdibleSpecies()),
    SHEEP(3, 5, 15, 70, initEdibleSpecies()),
    WILD_BOAR(2, 5, 50, 400, initEdibleSpecies()),
    BUFFALO(3, 5, 100, 700, initEdibleSpecies()),
    DUCK(4, 10, 0.45, 1.0, initEdibleSpecies()),
    CATERPILLAR(0, 1, 0, 0, initEdibleSpecies());
    //TODO добавить PLANT

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
        //вот тут TODO сделать что мы забили

        return islandEntityTypeIntegerMap;
    }
    
}
