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
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник
        for (IslandEntity eating : entities) {

            if (eating instanceof AbstractAnimal) {
                AbstractAnimal animal = (AbstractAnimal) eating;
                if (animal.getSaturation() <= 0) {
                    entities.remove(eating);
                    break;
                }
                if (eating instanceof Predators) {
                    // если хищник, пробегаемся по списку ещё раз и пробуем скушать кого-то из списка getEdibleSpecies,
                    // определенного в классе животного
                    feedPredator((Predators) eating, entities);

                } else if (eating instanceof Herbivorous) {
                    feedHerbivorous((Herbivorous) eating, entities);
                }
            }
        }
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
