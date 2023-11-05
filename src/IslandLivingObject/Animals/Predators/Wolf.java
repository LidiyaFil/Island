package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Wolf extends Predators {
    public Wolf() {
        super();
        weight = 50;
        fullSaturation = 8;
    }


    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WOLF;
    }
}
