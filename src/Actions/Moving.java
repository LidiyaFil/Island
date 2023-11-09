package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static src.Island.IslandField.countOfEntityResolver;
import static src.Island.IslandField.getInstance;

public class Moving {
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
    public boolean isReproduced() {
        return reprodused;
    }
    public void setReproduced(boolean reproduse) {
        this.reprodused = reproduse;
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
}
