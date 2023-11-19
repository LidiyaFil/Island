package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Mouse extends Predators {
    public Mouse(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.CATERPILLAR, 90);
        this.getEdibleSpecies().put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.MOUSE;
    }
}
