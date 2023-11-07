package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Duck extends Herbivorous {
    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DUCK;
    }
}
