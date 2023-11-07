package src.IslandLivingObject.Animals;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static src.Island.IslandField.countOfEntityResolver;
import static src.Island.IslandField.getInstance;

public abstract class AbstractAnimal implements IslandEntity {

    IslandField islandField = IslandField.getInstance();

    private int X;
    private int Y;

    private boolean reprodused = false;

    protected int daysUntilDeathFromStarvation = 3;

    @Override
    public String toString() {
        return String.valueOf(this.getType());
    }

    public abstract Map<IslandEntityType, Integer> getEdibleSpecies();

    public boolean isReproduced() {
        return reprodused;
    }

    public void setReproduced(boolean reproduse) {
        this.reprodused = reproduse;
    }

    public AbstractAnimal(IslandEntityType type) {

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public AbstractAnimal() {
        //TODO добавить поле голодание
    }

    public void eat(List<IslandEntity> entities) {
        // пробегаемся по списку и проверяем животное на принадлежность к классу хищник
        for (IslandEntity eating : entities) {

            if (eating instanceof Predators) {
                // если хищник, пробегаемся по списку ещё раз и пробуем скушать кого-то из списка getEdibleSpecies,
                // определенного в классе животного
                for (IslandEntity lunch : entities) {
                    if (((Predators) eating).getEdibleSpecies((AbstractAnimal) eating).containsKey(lunch.getType())) {
                        // попытка покушать
                        boolean result = tryToEat((Predators) eating, lunch);
                        // если результат положительный
                        if (result) {
                            entities.remove(lunch);
                            //TODO нужно ли удалять ссылки или это лишнее?
                            lunch = null;
                            //TODO добавить инкрементацию насыщения
                        } else break;
                    }
                    break;
                }
            } else if (eating instanceof Herbivorous) {
                for (IslandEntity lunch : entities) {
                    if (lunch instanceof AbstractPlant) {
                        entities.remove(lunch);
                        // TODO добавить инкрементацию насыщения
                    }
                }

            }
            //TODO вынести проверку на травоядное и на растение
        }
    }

    public boolean tryToEat(AbstractAnimal eating, IslandEntity lunch) {
        boolean resultOfTryingToEat;
        int chance = ThreadLocalRandom.current().nextInt(100);
        resultOfTryingToEat = chance >= eating.getEdibleSpecies(eating).get(lunch.getType());
        return resultOfTryingToEat;
    }

    public void move(int x, int y) {

        IslandField gameField = getInstance();

        int current_X = x;
        int current_Y = y;

        int steps = this.getType().getMaxMove();

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
            if (new_X >= 0 && new_X < gameField.getNumRows() && new_Y >= 0 && new_Y < gameField.getNumColumns()) {

                List currentCellEntities = IslandField.getGameField()[current_X][current_Y];
                List newCellEntities = IslandField.getGameField()[new_X][new_Y];

                if (countOfEntityResolver(current_X, current_Y, this.getClass()) < this.getType().getMaxAmount()) {
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

            //снова можно 18+
            setReproduced(false);
        }
    }

    public void reproduce(List<IslandEntity> entities) {
        AnimalFactory animalFactory = new AnimalFactory();


        for (IslandEntity entity : entities) {
            int i = countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass());

            //если есть пара и достаточно места для данного типа животных
            if (i > 1 && i < entity.getType().getMaxAmount()) {

                for (IslandEntity reproducingAnimal : entities) {
                //TODO добавить проверку на repro
                    if (entity != reproducingAnimal && !entity.isReproduced()) {
                        if (entity.getType() == reproducingAnimal.getType()) {
                            double chanceToReproduce = Math.random() * 1;
                            if (chanceToReproduce > 0.5) {
                                IslandEntity newBornEntity = animalFactory.createEntity(entity.getType());
                                //запрещаем всем причастным трогать друг друга
                                newBornEntity.setReproduced(true);
                                entity.setReproduced(true);
                                reproducingAnimal.setReproduced(true);

                                //добавляем новенького
                                entities.add(newBornEntity);
                            }
                        }
                    }
                }
            }
        }
    }
}
