package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MovingService {
    IslandField islandField = IslandField.getInstance();

    public void move(IslandEntity islandEntity) {
        int current_X = islandEntity.getX();
        int current_Y = islandEntity.getY();

        int steps = islandEntity.getType().getMaxMove();

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
            if (new_X >= 0
                    && new_X < IslandField.getInstance().getNumRows()
                    && new_Y >= 0
                    && new_Y < IslandField.getInstance().getNumColumns()) {

                List currentCellEntities = islandField.getGameField()[current_X][current_Y];
                List newCellEntities = islandField.getGameField()[new_X][new_Y];

                if (islandField.countOfEntityResolver(current_X, current_Y, islandEntity.getClass()) < islandEntity.getType().getMaxAmount()) {
                    // Удаляем животное из текущей клетки
                    currentCellEntities.remove(islandEntity);

                    // Обновляем текущие координаты
                    current_X = new_X;
                    current_Y = new_Y;
                    islandEntity.setX(new_X);
                    islandEntity.setY(new_Y);
                    // Добавляем животное в новую клетку
                    newCellEntities.add(islandEntity);
                }
                // Если вышли за пределы игрового поля или достигнута максимальная вместимость на клетке, то остаемся на текущей позиции
            }

            // Если не осталось шагов, завершаем движение
            if (i == steps - 1) {
                break;
            }
            //снова можно 18+
            islandEntity.setReproduced(false);
            //убавляем сытость на 25%
            doStarvation(islandEntity);
        }
    }

    private void doStarvation(IslandEntity islandEntity) {
        //отнимаем по 25% от максимальной вместимости желудка
        if (islandEntity.getSaturation() > 0) {
            islandEntity.setSaturation(islandEntity.getSaturation() - islandEntity.getType().getFullSaturation() / 4);
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            die(islandEntity);
        }
    }

    public void die(IslandEntity islandEntity) {
        islandField.getGameField()[islandEntity.getX()][islandEntity.getY()].remove(this);
    }
}
