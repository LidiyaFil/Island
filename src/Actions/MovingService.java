package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.stream.IntStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MovingService {
    IslandField islandField = IslandField.getInstance();
    private IslandEntity islandEntity;

    public MovingService(IslandEntity entity) {
        this.islandEntity = islandEntity;
    }

    public void move(IslandEntity islandEntity) {
        int steps = islandEntity.getType().getMaxMove();

        while (steps > 0) {
            // Генерируем случайное направление
            int direction = ThreadLocalRandom.current().nextInt(4);

            // Вычисляем новые координаты в соответствии с направлением
            // 0 - восток, 1 - запад
            int new_X = islandEntity.getX() + (direction == 0 ? 1 : (direction == 1 ? -1 : 0));
            // 2 - юг, 3 - север
            int new_Y = islandEntity.getY() + (direction == 2 ? 1 : (direction == 3 ? -1 : 0));

            // Проверяем, остаемся ли в пределах игрового поля
            if (isValidPosition(new_X, new_Y)) {
                moveEntity(islandEntity, new_X, new_Y);
            }

            //снова можно 18+
            islandEntity.setReproduced(false);
            //убавляем сытость на 25%
            setStarvation(islandEntity);

            // уменьшаем счетчик шагов
            steps--;
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < IslandField.getInstance().getNumRows() && y >= 0 && y < IslandField.getInstance().getNumColumns();
    }

    private void moveEntity(IslandEntity islandEntity, int x, int y) {
        List currentCellEntities = islandField.getGameField()[islandEntity.getX()][islandEntity.getY()];
        List newCellEntities = islandField.getGameField()[x][y];

        if (islandField.countOfEntityResolver(islandEntity.getX(), islandEntity.getY(), islandEntity.getClass()) < islandEntity.getType().getMaxAmount()) {
            // Удаляем животное из текущей клетки
            currentCellEntities.remove(islandEntity);

            // Обновляем текущие координаты
            islandEntity.setX(x);
            islandEntity.setY(y);
            // Добавляем животное в новую клетку
            newCellEntities.add(islandEntity);
        }
    }

    private void setStarvation(IslandEntity islandEntity) {
        //отнимаем по 25% от максимальной вместимости желудка
        if (islandEntity.getSaturation() > 0) {
            islandEntity.setSaturation(islandEntity.getSaturation() - islandEntity.getSaturation() / 4);
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            die(islandEntity);
        }
    }

    public void die(IslandEntity islandEntity) {
        islandField.getGameField()[islandEntity.getX()][islandEntity.getY()].remove(islandEntity);
    }
}
