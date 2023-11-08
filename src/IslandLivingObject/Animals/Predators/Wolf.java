package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predators {
    public Wolf() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.HORSE, 10);
        edibleSpecies.put(IslandEntityType.DEER, 15);
        edibleSpecies.put(IslandEntityType.RABBIT, 60);
        edibleSpecies.put(IslandEntityType.MOUSE, 80);
        edibleSpecies.put(IslandEntityType.GOAT, 60);
        edibleSpecies.put(IslandEntityType.SHEEP, 70);
        edibleSpecies.put(IslandEntityType.WILD_BOAR, 15);
        edibleSpecies.put(IslandEntityType.BUFFALO, 10);
        edibleSpecies.put(IslandEntityType.DUCK, 40);
    }


    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WOLF;
    }
}
