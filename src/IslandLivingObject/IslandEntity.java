package src.IslandLivingObject;

public interface IslandEntity {
    IslandEntityType getType();

    public boolean isReproduced();

    public void setReproduced(boolean b);

    int getX();

    int getY();
}
