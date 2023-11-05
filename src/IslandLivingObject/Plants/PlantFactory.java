package src.IslandLivingObject.Plants;

import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;

public class PlantFactory implements EntityFactory {
    @Override
    public IslandEntity createEntity() {
        return new Plant();
    }
}