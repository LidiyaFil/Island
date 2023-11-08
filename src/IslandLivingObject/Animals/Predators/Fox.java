package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Fox extends Predators {

    public Fox() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.RABBIT, 70);
        edibleSpecies.put(IslandEntityType.MOUSE, 90);
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 40);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.FOX;
    }
}
