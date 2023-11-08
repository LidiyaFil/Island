package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public class Duck extends Herbivorous {

    public Duck() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.CATERPILLAR, 90);
        edibleSpecies.put(IslandEntityType.PLANT, 40);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.DUCK;
    }
}
