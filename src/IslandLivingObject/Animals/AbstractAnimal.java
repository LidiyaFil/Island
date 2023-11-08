package src.IslandLivingObject.Animals;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static src.Island.IslandField.countOfEntityResolver;
import static src.Island.IslandField.getInstance;

public abstract class AbstractAnimal implements IslandEntity {
    protected int X;

    protected int Y;

    private boolean reprodused = false;

    private double saturation;
    private final Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    private void doStarvation() {
        //отнимаем по 25% от максимальной вместимости желудка
        if (getSaturation() > 0) {
            this.saturation = getSaturation() - this.getType().getFullSaturation() / 4;
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            die();
        }
    }

    public void die() {
        IslandField.getGameField()[this.getX()][this.getY()].remove(this);
    }

    public boolean isReproduced() {
        return reprodused;
    }

    public void setReproduced(boolean reproduse) {
        this.reprodused = reproduse;
    }
    public AbstractAnimal() {
        //инициализируем заполненность желудка 50% от максимально вместимости
        this.saturation = this.getType().getFullSaturation() / 2;
    }

    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(this.getType()));
        builder.append(" (").append(getX()).append(", ").append(getY()).append(")");
        return  builder.toString();
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return edibleSpecies;
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
                                    setSaturation(eaterSaturation);
                                } else {
                                    //если вес съеденного объекта не превышает размер желудка, то прибавляем насыщение
                                    setSaturation(getSaturation() + lunchWeight);
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
            } else {
                new_Y--; // Север
            }

            // Проверяем, остаемся ли в пределах игрового поля
            if (new_X >= 0 && new_X < gameField.getNumRows() && new_Y >= 0 && new_Y < gameField.getNumColumns()) {

                var currentCellEntities = IslandField.getGameField()[current_X][current_Y];
                var newCellEntities = IslandField.getGameField()[new_X][new_Y];

                if (countOfEntityResolver(current_X, current_Y, this.getClass()) < this.getType().getMaxAmount()) {
                    // Удаляем животное из текущей клетки
                    currentCellEntities.remove(this);

                    // Обновляем текущие координаты
                    current_X = new_X;
                    current_Y = new_Y;
                    this.setX(new_X);
                    this.setY(new_Y);
                    // Добавляем животное в новую клетку
                    newCellEntities.add(this);
                }
                // Если вышли за пределы игрового поля или достигнута максимальная вместимость на клетке, то остаемся на текущей позиции
            }

            // Если не осталось шагов, завершаем движение
            if (i == steps - 1) {
                break;
            }
            //снова можно 18+
            setReproduced(false);
            //убавляем сытость на 25%
            doStarvation();
        }
    }

    public void reproduce(List<IslandEntity> entities) {
        AnimalFactory animalFactory = new AnimalFactory();


        for (IslandEntity entity : entities) {
            int i = countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass());

            //если есть пара и достаточно места для данного типа животных
            if (i > 1 && i < entity.getType().getMaxAmount()) {

                for (IslandEntity reproducingAnimal : entities) {
                    if (entity != reproducingAnimal && !entity.isReproduced()) {
                        if (entity.getType() == reproducingAnimal.getType()) {
                            double chanceToReproduce = Math.random() * 1;
                            if (chanceToReproduce > 0.5) {
                                IslandEntity newBornEntity = animalFactory.createEntity(getX(), getY(), entity.getType());
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
