package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wolf extends Predators {
    protected Map<IslandEntityType, Integer> edibleSpecies;

    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
    }

    public Wolf() {
        super(30, 3, 50, 8);
        edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.SHEEP, 70);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WOLF;
    }
}
