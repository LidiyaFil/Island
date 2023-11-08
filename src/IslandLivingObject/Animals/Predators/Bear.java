package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Bear extends Predators {
    public Bear() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.PYTHON, 80);
        edibleSpecies.put(IslandEntityType.HORSE, 40);
        edibleSpecies.put(IslandEntityType.DEER, 80);
        edibleSpecies.put(IslandEntityType.RABBIT, 80);
        edibleSpecies.put(IslandEntityType.MOUSE, 90);
        edibleSpecies.put(IslandEntityType.GOAT, 70);
        edibleSpecies.put(IslandEntityType.SHEEP, 70);
        edibleSpecies.put(IslandEntityType.WILD_BOAR, 50);
        edibleSpecies.put(IslandEntityType.BUFFALO, 20);
        edibleSpecies.put(IslandEntityType.DUCK, 10);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.BEAR;
    }
}
