package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Fox extends Predators {

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.FOX;
    }
}
