package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Deer extends Herbivorous {
    private Map<IslandEntityType, Integer> edibleSpecies;

    public Deer() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DEER;
    }
}
