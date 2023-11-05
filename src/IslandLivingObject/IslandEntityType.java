package src.IslandLivingObject;

public enum IslandEntityType {
    WOLF(3, 30, fullSaturation, weight),
    PYTHON(1, 30, fullSaturation, weight),
    FOX(2, 30, fullSaturation, weight),
    Bear(2, 5, fullSaturation, weight),
    Eagle(3, 20, fullSaturation, weight),
    Horse(4, 20, fullSaturation, weight),
    Deer(4, 20, fullSaturation, weight),
    Rabbit(2, 150, fullSaturation, weight),
    Mouse(1, 500, fullSaturation, weight),
    Goat(3, 140, fullSaturation, weight),
    Sheep(3, 140, fullSaturation, weight),
    WildBoar(2, 50, fullSaturation, weight),
    Buffalo(3, 10, fullSaturation, weight),
    Duck(4, 1000, fullSaturation, weight),
    Caterpillar(0, 1000, fullSaturation, weight),
    Plant(0, 200, fullSaturation, weight);

    private final int maxMove;
    private final int maxAmount;
    private final double fullSaturation;
    private final double weight;

    IslandEntityType(int maxMove, int maxAmount, int fullSaturation, int weight) {
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
}
