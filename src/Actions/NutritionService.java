package src.Actions;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NutritionService {

    public void eat(List<IslandEntity> entities, AbstractAnimal abstractAnimal) {
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник

        //хищники и травоядные могут есть друг друга!!!
        for (IslandEntity lunch : entities) {

            double lunchWeight = lunch.getType().getWeight();
            if (abstractAnimal != lunch
                    && abstractAnimal.getSaturation() != abstractAnimal.getType().getFullSaturation()) {

                if (tryToEat(abstractAnimal, lunch)) {
                    abstractAnimal.setSaturation(
                            Math.min(abstractAnimal.getSaturation()
                                    + lunchWeight, abstractAnimal.getType().getFullSaturation()));
                    entities.remove(lunch);
                }
            }
        }
    }
        //TODO saturation!! check saturation
    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        int chance = ThreadLocalRandom.current().nextInt(100);
        if (eating.getEdibleSpecies().containsKey(lunch.getType())) {
            return chance >= eating.getEdibleSpecies().get(lunch.getType());
        } else {
            return false;
        }
    }
}
