package src.IslandLivingObject.Animals;

import src.Island.IslandField;
import src.Island.IslandFieldNew;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static src.Island.IslandFieldNew.countOfEntityResolver;

public abstract class AbstractAnimal implements IslandEntity {

    IslandFieldNew islandFieldNew = new IslandFieldNew(10, 10);

    private boolean isReproduced;

    public boolean isReproduced() {
        return isReproduced;
    }

    public void setReproduced(boolean reproduced) {
        isReproduced = reproduced;
    }
    protected int daysUntilDeathFromStarvation = 3;

    private int X;
    private int Y;

    public AbstractAnimal(IslandEntityType type) {

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public abstract Map<IslandEntityType, Integer> getEdibleSpecies();

    public AbstractAnimal() {
        //TODO добавить поле голодание
    }

    public int getWeight() {
        return weight;
    }

    public int getFullSaturation() {
        return fullSaturation;
    }

    public void eat(List<IslandEntity> entities) {
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник
        for (IslandEntity eating : entities) {

            if (eating instanceof Predators) {
                // если хищник, пробегаемся по списку ещё раз и пробуем скушать кого-то из списка getEdibleSpecies,
                // определенного в классе животного
                for (IslandEntity lunch : entities) {
                    if (((Predators) eating).getEdibleSpecies().containsKey(lunch.getType())) {
                        // попытка покушать
                        boolean result = tryToEat((Predators) eating, lunch);
                        // если результат положительный
                        if (result) {
                            entities.remove(lunch);
                            //TODO нужно ли удалять ссылки или это лишнее?
                            lunch = null;
                            //TODO добавить инкрементацию насыщения
                        }
                    } else ((Herbivorous) eating).getEdibleSpecies().containsKey(lunch.getType());
                    {
                        entities.remove(lunch);
                        lunch = null;
                    }

                }
                break;
            }

            //TODO вынести проверку на травоядное и на растение
        }
    }

    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        boolean resultOfTryingToEat;
        int chance = ThreadLocalRandom.current().nextInt(100);
        resultOfTryingToEat = chance >= eating.getEdibleSpecies().get(lunch.getType());
        return resultOfTryingToEat;
    }

    public void move(int steps, int x, int y) {

        int current_X = x;
        int current_Y = y;

        for (int i = 0; i < steps; i++) {
            // Генерируем случайное направление
            int direction = ThreadLocalRandom.current().nextInt(4);

            // Вычисляем новые координаты в соответствии с направлением
            int new_X = current_X;
            int new_Y = current_Y;
            if (direction == 0) {
                new_X++; // Восток
            } else if (direction == 1) {
                new_X--; // Запад
            } else if (direction == 2) {
                new_Y++; // Юг
            } else if (direction == 3) {
                new_Y--; // Север
            }

            // Проверяем, остаемся ли в пределах игрового поля
            if (new_X >= 0 && new_X < IslandField.getPlayingFieldWidth() && new_Y >= 0 && new_Y < IslandField.getPlayingFieldWidth()) {

                List<IslandEntity> currentCellEntities = gameField[current_X][current_Y];
                List<IslandEntity> newCellEntities = gameField[new_X][new_Y];

                if (countOfEntityResolver(current_X, current_Y, this.getClass()) < this.getMaxCapacity()) {
                    // Удаляем животное из текущей клетки
                    currentCellEntities.remove(this);

                    // Обновляем текущие координаты
                    current_X = new_X;
                    current_Y = new_Y;

                    // Добавляем животное в новую клетку
                    newCellEntities.add(this);
                }
                // Если вышли за пределы игрового поля или достигнута максимальная вместимость на клетке , то остаемся на текущей позиции
            }

            // Если не осталось шагов, завершаем движение
            if (i == steps - 1) {
                break;
            }
            //TODO декремент насыщения
        }
    }

    public void reproduce(List<IslandEntity> entities) {
        AnimalFactory animalFactory = new AnimalFactory();
        //TODO понять как достать конкретное животное

        for (IslandEntity entity : entities) {
            int i = countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass());

            if (i > 1 && i < entity.getType().getMaxAmount()) {

                for (IslandEntity reproducingAnimal : entities) {

                    if (entity != reproducingAnimal) {
                        if (entity.getType() == reproducingAnimal.getType()) {
                            double v = Math.random() * 1;
                            if (v > 0.5) {
                                entities.add(animalFactory.createEntity(entity.getType()));

                            }
                        }
                    }
                }
            }
        }
    }
}
