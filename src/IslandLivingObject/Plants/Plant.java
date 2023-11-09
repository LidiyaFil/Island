package src.IslandLivingObject.Plants;

import src.IslandLivingObject.IslandEntityType;

public class Plant extends AbstractPlant {
    public Plant(int x, int y) {
        super(x, y);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PLANT;
    }
}