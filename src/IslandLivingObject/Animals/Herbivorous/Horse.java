package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Horse extends Herbivorous {
    private Map<IslandEntityType, Integer> edibleSpecies;

    public Horse() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.HORSE;
    }
}
