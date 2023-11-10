package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Rabbit extends Herbivorous {
    public Rabbit(int x, int y) {
        super(x, y);
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.RABBIT;
    }
}
