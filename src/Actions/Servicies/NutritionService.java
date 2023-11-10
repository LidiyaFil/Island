package src.Actions.Servicies;

import src.Actions.Eateble;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NutritionService {
    private AbstractAnimal abstractAnimal;

    public NutritionService(Eateble eateble) {
        this.abstractAnimal = (AbstractAnimal) eateble;
    }

    public void eat(List<IslandEntity> entities) {
        entities.stream()
                .filter(entity -> entity instanceof AbstractAnimal)
                .forEach(eating -> {
                    if (eating instanceof Predators && ((Predators) eating).getSaturation() < ((Predators) eating).getSaturation()) {
                        feedPredator((Predators) eating, entities);
                    } else if (eating instanceof Herbivorous && ((Herbivorous) eating).getSaturation() < ((Herbivorous) eating).getSaturation()) {
                        feedHerbivorous((Herbivorous) eating, entities);
                    }
                });
    }

    private void feedPredator(Predators predator, List<IslandEntity> entities) {
        entities.stream()
                .filter(lunch -> lunch instanceof AbstractAnimal && predator.getEdibleSpecies().containsKey(lunch.getType()))
                .forEach(lunch -> {
                    boolean result = tryToEat(predator, lunch);
                    if (result) {
                        double eaterSaturation = predator.getSaturation();
                        double lunchWeight = lunch.getType().getWeight();
                        abstractAnimal.setSaturation(abstractAnimal.getSaturation() + Math.min(eaterSaturation, lunchWeight));
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
    }

    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        int chance = ThreadLocalRandom.current().nextInt(100);
        return chance >= eating.getEdibleSpecies().get(lunch.getType());
    }
}
