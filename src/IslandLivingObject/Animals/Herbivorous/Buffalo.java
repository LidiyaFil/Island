package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Buffalo extends Herbivorous {
    public Buffalo(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.PLANT, 99);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.BUFFALO;
    }
}
