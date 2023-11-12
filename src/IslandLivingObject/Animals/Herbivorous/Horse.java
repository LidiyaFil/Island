package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Horse extends Herbivorous {
    public Horse(int x, int y) {
        super(x, y);
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.PLANT, 99);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.HORSE;
    }
}
