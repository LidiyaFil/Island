package src.IslandLivingObject.Plants;

import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

public class PlantFactory {

    public IslandEntity createEntity() {
        return new Plant();
    }
}
