package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Python extends Predators {
    private Map<IslandEntityType, Integer> edibleSpecies;

    public Python() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.FOX, 14);
        edibleSpecies.put(IslandEntityType.RABBIT, 20);
        edibleSpecies.put(IslandEntityType.MOUSE, 40);
        edibleSpecies.put(IslandEntityType.DUCK, 10);
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PYTHON;
    }
}
