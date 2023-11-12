package src.Actions.Servicies;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NutritionService {

    public void eat(List<IslandEntity> entities, AbstractAnimal abstractAnimal) {
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник

        double eaterSaturation = abstractAnimal.getSaturation();
        //хищники и травоядные могут есть друг друга!!!
        for (IslandEntity lunch : entities) {

            double lunchWeight = lunch.getType().getWeight();
            if (abstractAnimal != lunch) {

                if (tryToEat(abstractAnimal, lunch)) {
                    abstractAnimal.setSaturation(abstractAnimal.getSaturation() + Math.min(eaterSaturation, lunchWeight));
                    entities.remove(lunch);
                }
            }
        }
    }

    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        int chance = ThreadLocalRandom.current().nextInt(100);
        return chance >= eating.getEdibleSpecies().get(lunch.getType());
    }
}

   /* private void feedPredator(Predators predator, List<IslandEntity> entities, AbstractAnimal abstractAnimal) {
        entities.stream()
                .filter(lunch -> lunch instanceof AbstractAnimal && predator.getEdibleSpecies().containsKey(lunch.getType()))
                .forEach(lunch -> {
                    boolean result = tryToEat(predator, lunch);
                    if (result) {
                        double eaterSaturation = predator.getSaturation();
                        double lunchWeight = lunch.getType().getWeight();
                        abstractAnimal.setSaturation(abstractAnimal.getSaturation() + Math.min(eaterSaturation, lunchWeight));
//                        System.out.println("съели" + lunch);
                        entities.remove(lunch);
                    }
                });
    }

    private void feedHerbivorous(Herbivorous herbivorous, List<IslandEntity> entities) {
        entities.stream()
                .filter(lunch -> lunch instanceof AbstractPlant)
                .forEach(lunch -> {
                    herbivorous.setSaturation(lunch.getType().getWeight());
                    entities.remove(lunch);
                });
    }*/
