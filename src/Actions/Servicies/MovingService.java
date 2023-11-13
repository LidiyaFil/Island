package src.Actions.Servicies;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MovingService {

    public void move(AbstractAnimal abstractAnimal) {
        int steps = abstractAnimal.getType().getMaxMove();
        int newX = abstractAnimal.getX();
        int newY = abstractAnimal.getY();

        while (steps-- > 0) {
            // Генерируем случайное направление
            int direction = ThreadLocalRandom.current().nextInt(4);

            // Вычисляем новые координаты в соответствии с направлением
            if (direction == 0) {
                newX++; // Восток
            } else if (direction == 1) {
                newX--; // Запад
            } else if (direction == 2) {
                newY++; // Юг
            } else {
                newY--; // Север
            }

            // Проверяем, остаемся ли в пределах игрового поля
            if (isValidPosition(newX, newY)) {
                moveEntity(abstractAnimal, newX, newY);
            }

            //снова можно размножаться
            abstractAnimal.setReproduced(false);
            //убавляем сытость
            abstractAnimal.doStarvation();
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
        int oldIslandEntityX = islandEntity.getX();
        int oldIslandEntityY = islandEntity.getY();

        // проверяем, есть ли свободные места для движения в новой клетке
        if (countOfEntityResolver(x, y, islandEntity.getClass()) < islandEntity.getType().getMaxAmount()) {

            // Удаляем животное из текущей клетки
            IslandField.getInstance().getGameField()[oldIslandEntityX][oldIslandEntityY].remove(islandEntity);
//            System.out.println("вот этого удалили из списка");
//            System.out.println(islandEntity);
            // Обновляем текущие координаты
//            System.out.println("сейчас добавим вот его");
            islandEntity.setX(x);
            islandEntity.setY(y);
//            System.out.println(islandEntity + " " + islandEntity.getX() + " " + islandEntity.getY());
//            System.out.println("с новыми координатами " + x + " " + y);
            // Добавляем животное в новую клетку
//            System.out.println("try add " + islandEntity);
//            System.out.println(IslandField.getInstance().getGameField()[x][y]);
            IslandField.getInstance().getGameField()[x][y].add(islandEntity);
//            System.out.println("added");
//            System.out.println(IslandField.getInstance().getGameField()[x][y]);
        }
    }

    private int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandEntity> entitiesInCell = IslandField.getInstance().getGameField()[x][y];
        return (int) entitiesInCell.stream().filter(targetClass::isInstance).count();
    }
}
