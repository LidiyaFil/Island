package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Fox extends Predators {
    public Fox() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.RABBIT, 70);
        edibleSpecies.put(IslandEntityType.MOUSE, 90);
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 40);
    }
    @Override
    public IslandEntityType getType() {
        return IslandEntityType.FOX;
    }
}
