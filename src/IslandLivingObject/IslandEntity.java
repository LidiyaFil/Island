package src.IslandLivingObject;

import java.util.Map;

public interface IslandEntity {
    boolean isReproduced();

    void setReproduced(boolean b);

    IslandEntityType getType();

    Map<IslandEntityType, Integer> getEdibleSpecies();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void die();
}
