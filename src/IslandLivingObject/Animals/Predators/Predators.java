package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.Animals.AbstractAnimals;
import src.IslandLivingObject.IslandEntityType;

import java.util.List;
import java.util.Map;

public abstract class Predators extends AbstractAnimals {



    public Predators(int maxAmount, int maxMove, int weight, int fullSaturation) {
        super(maxAmount, maxMove, weight, fullSaturation);
    }
}
