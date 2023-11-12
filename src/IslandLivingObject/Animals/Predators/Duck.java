package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Duck extends Herbivorous {
    public Duck(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.CATERPILLAR, 90);
        this.getEdibleSpecies().put(IslandEntityType.PLANT, 40);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DUCK;
    }
}
