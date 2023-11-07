package src.IslandLivingObject.Plants;

import src.IslandLivingObject.IslandEntityType;

public class Plant extends AbstractPlant {
    public Plant() {
        super();
        maxAmount = 200;
        weight = 1;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PLANT;
    }

    @Override
    public boolean isReproduced() {
        return false;
    }

    @Override
    public void setReproduced(boolean b) {
    }
}
