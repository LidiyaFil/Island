package src.IslandLivingObject.Plants;

import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

public class PlantFactory implements EntityFactory {
    @Override
    public IslandEntity createEntity(IslandEntityType entityType) {
        return new Plant();
    }
}