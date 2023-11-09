package src.Actions;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NutritionService {
    private IslandEntity islandEntity;

    public NutritionService(IslandEntity islandEntity) {
        this.islandEntity = islandEntity;
    }

    public void eat(List<IslandEntity> entities) {
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник
        entities.stream().
                filter(eating -> eating instanceof Predators && ((Predators) eating).getSaturation() < eating.getSaturation())
                // если да, пробегаемся по списку ещё раз и пробуем скушать кого-то из списка getEdibleSpecies,
                // определенного в классе животного
                .forEach(eating -> entities.stream()
                        .filter(lunch -> eating.getEdibleSpecies().containsKey(lunch.getType()))
                        //если животное голодное (есть место для заполнения желудка) и если животное может съесть такой тип объектов
                        .forEach(lunch -> {
                            // попытка покушать
                            boolean result = tryToEat((Predators) eating, lunch);
                            // если результат положительный
                            if (result) {
                                double eaterSaturation = eating.getSaturation();
                                double lunchWeight = lunch.getType().getWeight();
                                //если вес съеденного объекта превышает размер желудка, то ставим полное насыщение
                                //если вес съеденного объекта не превышает размер желудка, то прибавляем насыщение
                                islandEntity.setSaturation(islandEntity.getSaturation() + Math.min(eaterSaturation, lunchWeight));
                                //удаляем съеденного из списка
                                entities.remove(lunch);
                            }
                        }));
        // пробегаемся по списку и проверяем животное на принадлежность к классу травоядное
        entities.stream()
                .filter(eating -> eating instanceof Herbivorous && ((Herbivorous) eating).getSaturation() < eating.getSaturation())
                .forEach(eating -> entities.stream()
                        .filter(lunch -> lunch instanceof AbstractPlant)
                        .forEach(lunch -> {
                            ((Herbivorous) eating).setSaturation(lunch.getType().getWeight());
                            entities.remove(lunch);
                        }));
    }

    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        int chance = ThreadLocalRandom.current().nextInt(100);
        return chance >= eating.getEdibleSpecies().get(lunch.getType());
    }
}
