package src.Actions;

import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public interface Eateble extends IslandEntity {
    double getSaturation();
    void setSaturation(double newSaturation);
    Map<IslandEntityType, Integer> getEdibleSpecies();
}
