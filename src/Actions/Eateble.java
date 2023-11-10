package src.Actions;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public interface Eateble {
    double getSaturation();
    void setSaturation(double newSaturation);
    Map<IslandEntityType, Integer> getEdibleSpecies();
}
