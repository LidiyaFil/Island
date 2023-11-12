package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Sheep extends Herbivorous {
    public Sheep(int x, int y) {
        super(x, y);
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.PLANT, 99);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.SHEEP;
    }
}
