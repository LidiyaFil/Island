package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Rabbit extends Herbivorous {
    public Rabbit() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }
    @Override
    public IslandEntityType getType() {
        return IslandEntityType.RABBIT;
    }
}
