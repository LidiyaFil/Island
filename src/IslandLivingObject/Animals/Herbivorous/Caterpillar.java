package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.Plant;

import java.util.HashMap;
import java.util.Map;

public class Caterpillar extends Herbivorous {
    private Map<IslandEntityType, Integer> edibleSpecies;

    public Caterpillar() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.CATERPILLAR;
    }
}
