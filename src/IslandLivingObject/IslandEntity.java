package src.IslandLivingObject;

import java.util.Map;

public interface IslandEntity {

    IslandEntityType getType();

    boolean isReproduced();

    void setReproduced(boolean b);
    Map<IslandEntityType, Integer> getEdibleSpecies();

    int getX();

    int getY();

    void die();
}
