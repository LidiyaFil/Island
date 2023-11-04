package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimals;
import src.IslandLivingObject.IslandEntityType;

public class Wolf extends Predators {
    public Wolf() {
        super();
        maxAmount = 30;
        maxMove = 3;
        weight = 50;
        fullSaturation = 8;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PLANT;
    }
}
