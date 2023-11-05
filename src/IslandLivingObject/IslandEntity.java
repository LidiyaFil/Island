package src.IslandLivingObject;

public interface IslandEntity {
    IslandEntityType getType();

    public boolean isReproduced();

    public boolean setReproduced();

    int getX();

    int getY();
}
