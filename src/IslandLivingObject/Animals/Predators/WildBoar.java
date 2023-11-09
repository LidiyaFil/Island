package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class WildBoar extends Predators {
    public WildBoar() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.MOUSE, 50);
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 90);
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WILD_BOAR;
    }
}
