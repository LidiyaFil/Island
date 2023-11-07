package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Rabbit extends Herbivorous {
    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.RABBIT;
    }
}
