package src.IslandLivingObject.Plants;

import src.IslandLivingObject.IslandEntity;

public abstract class AbstractPlants implements IslandEntity {

    private final int maxAmount;
    public AbstractPlants(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    abstract protected void grow();
}
