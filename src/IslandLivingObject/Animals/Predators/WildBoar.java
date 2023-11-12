package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class WildBoar extends Predators {
    public WildBoar(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.MOUSE, 50);
        this.getEdibleSpecies().put(IslandEntityType.CATERPILLAR, 90);
        this.getEdibleSpecies().put(IslandEntityType.PLANT, 99);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WILD_BOAR;
    }
}
