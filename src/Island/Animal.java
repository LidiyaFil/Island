package src.Island;

import src.IslandLivingObject.IslandEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.CopyOnWriteArrayList;

public class Animal implements IslandEntity {

    private final int steps;
    private final int maxCapacity;

    public int getSteps() {
        return steps;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    List<CopyOnWriteArrayList<Cell>> islandField = IslandField.getCells();

    public Animal(int steps, int capacity) {
        this.steps = steps;
        this.maxCapacity = capacity;
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

                if (islandField.get(new_X).get(new_Y).countOfEntityResolver(this.getClass()) < this.getMaxCapacity()) {
                    // Удаляем животное из текущей клетки
                    Cell currentCell = islandField.get(current_X).get(current_Y);
                    currentCell.getEntities().remove(this);

                    // Обновляем текущие координаты
                    current_X = new_X;
                    current_Y = new_Y;

                    // Добавляем животное в новую клетку
                    Cell newCell = islandField.get(new_X).get(new_Y);
                    newCell.getEntities().add(this);
                }
// Если вышли за пределы игрового поля или достигнута максимальная вместимость на клетке , то остаемся на текущей позиции
            }


            // Если не осталось шагов, завершаем движение
            if (i == steps - 1) {
                break;
            }
        }
    }
}