package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimals;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Sheep extends Herbivorous {

    protected Map<IslandEntityType, Integer> edibleSpecies;

    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
    }

    protected enum EatableType {
        PLANT
    }
    public Sheep() {
        super(140, 3,70,15);
        edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.SHEEP;
    }
}
