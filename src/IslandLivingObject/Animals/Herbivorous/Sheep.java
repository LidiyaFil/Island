package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Sheep extends Herbivorous {
    public Sheep(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.SHEEP;
    }
}
