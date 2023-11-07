package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class WildBoar extends Predators {
    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WILD_BOAR;
    }
}
