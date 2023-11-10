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
                    for (IslandEntity lunch : entities) {
                        //если животное голодное (есть место для заполнения желудка)
                        if (((Predators) eating).getSaturation() < eating.getType().getFullSaturation()) {
                            //если животное может съесть такой тип объектов

                            if (animal.getEdibleSpecies().containsKey(lunch.getType())) {
                                // попытка покушать
                                boolean result = tryToEat((Predators) eating, lunch);

                                // если результат положительный
                                if (result) {
                                    double eaterSaturation = eating.getType().getFullSaturation();
                                    double lunchWeight = lunch.getType().getWeight();
                                    if (lunchWeight > eaterSaturation) {
                                        //если вес съеденного объекта превышает размер желудка, то ставим полное насыщение
                                        animal.setSaturation(eaterSaturation);
                                    } else {
                                        //если вес съеденного объекта не превышает размер желудка, то прибавляем насыщение
                                        animal.setSaturation(animal.getSaturation() + lunchWeight);
                                    }
                                    //удаляем съеденного из списка
                                    entities.remove(lunch);
                                }
                            }
                        }
                    }

                } else if (eating instanceof Herbivorous) {
                    for (IslandEntity lunch : entities) {
                        //если животное голодное (есть место для заполнения желудка)
                        if (((Herbivorous) eating).getSaturation() < eating.getType().getFullSaturation()) {
                            if (lunch instanceof AbstractPlant) {
                                ((Herbivorous) eating).setSaturation(lunch.getType().getWeight());
                                entities.remove(lunch);
                            }
                        }
                    }
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
