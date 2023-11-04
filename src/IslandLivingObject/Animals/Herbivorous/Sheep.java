package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimals;
import src.IslandLivingObject.IslandEntityType;

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
}
