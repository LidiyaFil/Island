package src.IslandLivingObject.Plants;

import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.List;

public class Plant extends AbstractPlants {
    public Plant() {
        super();
        maxAmount = 200;
        weight = 1;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PLANT;
    }
}
