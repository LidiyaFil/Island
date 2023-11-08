package src.IslandLivingObject;

import src.IslandLivingObject.Animals.AbstractAnimal;

import java.util.Map;

public interface IslandEntity {

    IslandEntityType getType();

    boolean isReproduced();

    void setReproduced(boolean b);
    Map<IslandEntityType, Integer> getEdibleSpecies();

    int getX();

    int getY();
}
