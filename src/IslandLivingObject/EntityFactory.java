package src.IslandLivingObject;

public interface EntityFactory {
    IslandEntity createEntity(int x, int y, IslandEntityType entityType);
}
