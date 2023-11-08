package src.IslandLivingObject;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Predators.Wolf;
import src.IslandLivingObject.Plants.*;

import java.util.HashMap;
import java.util.Map;

public enum IslandEntityType {
    WOLF(3, 3, 8, 50),
    PYTHON(1, 3, 3, 15),
    FOX(2, 3, 2, 8),
    BEAR(2, 5, 80, 500),
    EAGLE(3, 2, 1, 6),
    HORSE(4, 2, 60, 400),
    DEER(4, 2, 50, 300),
    RABBIT(2, 5, 0.45, 2),
    MOUSE(1, 5, 0.01, 0.05),
    GOAT(3, 5, 10, 60),
    SHEEP(3, 5, 15, 70),
    WILD_BOAR(2, 5, 50, 400),
    BUFFALO(3, 5, 100, 700),
    DUCK(4, 10, 0.45, 1.0),
    CATERPILLAR(0, 1, 0, 0),

    PLANT(0, 200, 0, 1);

    private final int maxMove;   private final int maxAmount;
    private final double fullSaturation;
    private final double weight;

    IslandEntityType(int maxMove, int maxAmount, double fullSaturation, double weight) {
        this.maxMove = maxMove;
        this.maxAmount = maxAmount;
        this.fullSaturation = fullSaturation;
        this.weight = weight;
    }

    public int getMaxMove() {
        return maxMove;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public double getWeight() {
        return weight;
    }
}
