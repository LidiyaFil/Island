package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Fox extends Predators {
    public Fox(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.RABBIT, 70);
        this.getEdibleSpecies().put(IslandEntityType.MOUSE, 90);
        this.getEdibleSpecies().put(IslandEntityType.CATERPILLAR, 40);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.FOX;
    }
}
