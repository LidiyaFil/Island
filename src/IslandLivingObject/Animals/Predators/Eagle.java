package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Eagle extends Predators {

    public Eagle() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.FOX, 10);
        edibleSpecies.put(IslandEntityType.RABBIT, 90);
        edibleSpecies.put(IslandEntityType.MOUSE, 90);
        edibleSpecies.put(IslandEntityType.DUCK, 80);
    }


    @Override
    public IslandEntityType getType() {
        return IslandEntityType.EAGLE;
    }
}
