package src.Actions.Servicies;

import src.Actions.Moveable;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntity;

import java.util.stream.IntStream;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MovingService {
    IslandField islandField = IslandField.getInstance();
    private AbstractAnimal abstractAnimal;

    public MovingService(Moveable moveable) {
        this.abstractAnimal = (AbstractAnimal) moveable;
    }

    public void move(AbstractAnimal abstractAnimal) {
        int steps = abstractAnimal.getType().getMaxMove();
        int new_X = abstractAnimal.getX();
        int new_Y = abstractAnimal.getY();

        while (steps > 0) {
            // Генерируем случайное направление
            int direction = ThreadLocalRandom.current().nextInt(4);

            // Вычисляем новые координаты в соответствии с направлением
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
            if (isValidPosition(new_X, new_Y)) {
                moveEntity(abstractAnimal, new_X, new_Y);
            }

            //снова можно размножаться
            abstractAnimal.setReproduced(false);
            //убавляем сытость
            abstractAnimal.doStarvation();
            // уменьшаем счетчик шагов
            steps--;
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0
                && x < IslandField.getInstance().getNumRows()
                && y >= 0
                && y < IslandField.getInstance().getNumColumns();
    }

    private void moveEntity(IslandEntity islandEntity, int x, int y) {
        // лист где сейчас животное
        List currentCellEntities = islandField.getGameField()[islandEntity.getX()][islandEntity.getY()];
        // лист, куда планирует переместиться
        List newCellEntities = islandField.getGameField()[x][y];
        // если макс количество в клетке не превышено
        if (countOfEntityResolver(x, y, islandEntity.getClass()) < islandEntity.getType().getMaxAmount()) {
            // Добавляем животное в новую клетку
            newCellEntities.add(islandEntity);
            // Обновляем текущие координаты
            islandEntity.setX(x);
            islandEntity.setY(y);
            // Удаляем животное из текущей клетки
            currentCellEntities.remove(islandEntity);
        }
    }

    public int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandEntity> entitiesInCell = islandField.getGameField()[x][y];
        return (int) entitiesInCell.stream().filter(targetClass::isInstance).count();
    }
}
