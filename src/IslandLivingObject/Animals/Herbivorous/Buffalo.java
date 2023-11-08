package src.IslandLivingObject.Animals.Herbivorous;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;

public class Buffalo extends Predators {


    public Buffalo() {
        Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();
        edibleSpecies.put(IslandEntityType.PLANT, 100);
    }


    @Override
    public IslandEntityType getType() {
        return IslandEntityType.BUFFALO;
    }




}
