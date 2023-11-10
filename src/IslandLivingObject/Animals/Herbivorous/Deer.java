package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Deer extends Herbivorous {
    public Deer(int x, int y) {
        super(x, y);
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DEER;
    }
}
