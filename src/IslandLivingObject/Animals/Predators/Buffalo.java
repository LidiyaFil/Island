package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Buffalo extends Predators {
    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.BUFFALO;
    }
}
