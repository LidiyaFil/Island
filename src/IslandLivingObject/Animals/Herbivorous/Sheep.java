package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimals;
import src.IslandLivingObject.IslandEntityType;

public class Sheep extends Herbivorous {
    protected enum EatableType {
        PLANT
    }
    public Sheep() {
        super(140, 3,70,15);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.SHEEP;
    }
}
