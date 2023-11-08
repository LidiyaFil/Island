package src.IslandLivingObject;

import java.util.Map;

public interface IslandEntity {

    IslandEntityType getType();

    boolean isReproduced();

    void setReproduced(boolean b);
    Map<IslandEntityType, Integer> getEdibleSpecies();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void die();
}
