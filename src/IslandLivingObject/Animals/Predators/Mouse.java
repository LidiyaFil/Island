package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Mouse extends Predators {
    private Map<IslandEntityType, Integer> edibleSpecies;

    public Mouse() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 90);
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.MOUSE;
    }
}
