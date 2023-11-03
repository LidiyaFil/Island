package src.Island;

import src.IslandLivingObject.IslandEntity;
import java.util.concurrent.ThreadLocalRandom;

public class MoveService {


    public void move(int steps, IslandEntity entity) {
        int x = Cell.getX();
        int y = Cell.getY();

        for (int i = 0; i < steps; i++) {
            // Генерируем случайное направление
            int direction = ThreadLocalRandom.current().nextInt(4);

            // Вычисляем новые координаты в соответствии с направлением
            int newX = getX();
            int newY = getY();
            if (direction == 0) {
                newX++; // Восток
            } else if (direction == 1) {
                newX--; // Запад
            } else if (direction == 2) {
                newY++; // Юг
            } else if (direction == 3) {
                newY--; // Север
            }

            // Проверяем, остаемся ли в пределах игрового поля
            if (newX >= 0 && newX < x && newY >= 0 && newY < y) {
                setX(newX);
                setY(newY);
            }
            // Если вышли за пределы игрового поля, остаемся на текущей позиции

            // Если не осталось шагов, завершаем движение
            if (i == steps - 1) {
                break;
            }
        }
    }
}
