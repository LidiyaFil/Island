package src.IslandLivingObject.Plants;

import src.IslandLivingObject.IslandEntity;

public abstract class AbstractPlants implements IslandEntity {
    protected int maxAmount;
    protected int weight;

    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Plant grow() {
        return new Plant();
    }
}

