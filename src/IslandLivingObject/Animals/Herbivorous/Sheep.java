package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Sheep extends Herbivorous {
    public Sheep() {
        super();
        maxAmount = 140;
        maxMove = 3;
        weight = 70;
        fullSaturation = 15;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PLANT;
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }
}
