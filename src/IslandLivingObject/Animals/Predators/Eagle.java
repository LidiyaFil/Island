package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Eagle extends Predators {
    public Eagle(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.FOX, 10);
        this.getEdibleSpecies().put(IslandEntityType.RABBIT, 90);
        this.getEdibleSpecies().put(IslandEntityType.MOUSE, 90);
        this.getEdibleSpecies().put(IslandEntityType.DUCK, 80);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.EAGLE;
    }

}
