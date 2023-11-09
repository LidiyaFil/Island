package src.IslandLivingObject.Plants;

import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
@Deprecated
public class PlantFactory implements EntityFactory {

    @Override
    public IslandEntity createEntity(int x, int y, IslandEntityType entityType) {
        return new Plant(x, y);
    }
}
