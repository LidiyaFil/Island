package src.IslandLivingObject;

public enum IslandEntityType {
    WOLF(3, 30, 8, 50, "\uD83D\uDC3A"),
    PYTHON(1, 20, 3, 15, "\uD83D\uDC0D"),
    FOX(2, 30, 2, 8, "\uD83E\uDD8A"),
    BEAR(2, 5, 80, 500, "\uD83D\uDC3B"),
    EAGLE(3, 20, 1, 6, "\uD83E\uDD85"),
    HORSE(4, 20, 60, 400, "\uD83D\uDC0E"),
    DEER(4, 20, 50, 300, "\uD83E\uDD8C"),
    RABBIT(2, 150, 0.45, 2, "\uD83D\uDC07"),
    MOUSE(1, 500, 0.01, 0.05, "\uD83D\uDC01"),
    GOAT(3, 140, 10, 60, "\uD83D\uDC10"),
    SHEEP(3, 140, 15, 70, " \uD83D\uDC11"),
    WILD_BOAR(2, 50, 50, 400, "\uD83D\uDC17"),
    BUFFALO(3, 20, 100, 700, "\uD83D\uDC03"),
    DUCK(4, 200, 0.45, 1.0, "\uD83E\uDD86"),
    CATERPILLAR(0, 1000, 0.05, 0.1, "\uD83D\uDC1B"),
    PLANT(0, 200, 0, 1, "\uD83C\uDF33");

    private final int maxMove;
    private final int maxAmount;
    private final double fullSaturation;
    private final double weight;
    private final String icon;

    IslandEntityType(int maxMove, int maxAmount, double fullSaturation, double weight, String icon) {
        this.maxMove = maxMove;
        this.maxAmount = maxAmount;
        this.fullSaturation = fullSaturation;
        this.weight = weight;
        this.icon = icon;
    }

    public int getMaxMove() {
        return this.maxMove;
    }

    public int getMaxAmount() {
        return this.maxAmount;
    }

    public double getFullSaturation() {
        return this.fullSaturation;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getIcon() {
        return icon;
    }
}
