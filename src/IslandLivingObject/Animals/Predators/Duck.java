package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Duck extends Herbivorous {
    private Map<IslandEntityType, Integer> edibleSpecies;

    public Duck() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 90);
        edibleSpecies.put(IslandEntityType.PLANT, 40);
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DUCK;
    }
}
