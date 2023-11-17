package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Deer extends Herbivorous {
    public Deer(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.PLANT, 99);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DEER;
    }
}