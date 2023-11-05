package src.IslandLivingObject;

public enum IslandEntityType {
    WOLF(3, 30, fullSaturation, weight),
    PYTHON(1, 30, fullSaturation, weight),
    FOX(2, 30, fullSaturation, weight),
    BEAR(2, 5, fullSaturation, weight),
    EAGLE(3, 20, fullSaturation, weight),
    HORSE(4, 20, fullSaturation, weight),
    DEER(4, 20, fullSaturation, weight),
    RABBIT(2, 150, fullSaturation, weight),
    MOUSE(1, 500, fullSaturation, weight),
    GOAT(3, 140, fullSaturation, weight),
    SHEEP(3, 140, fullSaturation, weight),
    WILDBOAR(2, 50, fullSaturation, weight),
    BUFFALO(3, 10, fullSaturation, weight),
    DUCK(4, 1000, fullSaturation, weight),
    CATERPILLAR(0, 1000, fullSaturation, weight),
    PLANT(0, 200, fullSaturation, weight);

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
