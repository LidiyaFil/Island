package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Mouse extends Predators {
    public Mouse() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 90);
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }
    @Override
    public IslandEntityType getType() {
        return IslandEntityType.MOUSE;
    }
}
