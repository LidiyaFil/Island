package src.Actions;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public interface MoveableReproducibleEatable {
    boolean isReproduced();
    void setReproduced(boolean reproduced);
    void setSaturation(double newSaturation);
    Map<IslandEntityType, Integer> getEdibleSpecies();
}
