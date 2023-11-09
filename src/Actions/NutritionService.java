package src.Actions;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NutritionService {
    //TODO этого здесь быть не должно - переменной и конструктора, переделать
    private IslandEntity islandEntity;

    public NutritionService(IslandEntity islandEntity) {
        this.islandEntity = islandEntity;
    }

    public void eat(List<IslandEntity> entities) {
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник
        for (IslandEntity eating : entities) {
            if (eating instanceof Predators) {
                // если хищник, пробегаемся по списку ещё раз и пробуем скушать кого-то из списка getEdibleSpecies,
                // определенного в классе животного
                for (IslandEntity lunch : entities) {
                    //если животное голодное (есть место для заполнения желудка)
                    if (((Predators) eating).getSaturation() < eating.getType().getFullSaturation()) {
                        //если животное может съесть такой тип объектов
                        if ((eating).getEdibleSpecies().containsKey(lunch.getType())) {
                            // попытка покушать
                            boolean result = tryToEat((Predators) eating, lunch);

                            // если результат положительный
                            if (result) {
                                double eaterSaturation = eating.getType().getFullSaturation();
                                double lunchWeight = lunch.getType().getWeight();
                                if (lunchWeight > eaterSaturation) {
                                    //если вес съеденного объекта превышает размер желудка, то ставим полное насыщение
                                    islandEntity.setSaturation(eaterSaturation);
                                } else {
                                    //если вес съеденного объекта не превышает размер желудка, то прибавляем насыщение
                                    islandEntity.setSaturation(islandEntity.getSaturation() + lunchWeight);
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

    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        boolean resultOfTryingToEat;
        int chance = ThreadLocalRandom.current().nextInt(100);
        resultOfTryingToEat = chance >= eating.getEdibleSpecies().get(lunch.getType());
        return resultOfTryingToEat;
    }
}
