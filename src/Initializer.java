package src;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntityFactory;

import java.util.Scanner;

public class Initializer {
    private final IslandEntityFactory islantEntityFactory;
    private final IslandField islandField = IslandField.getInstance();
    public Initializer(IslandEntityFactory islantEntityFactory) {
        this.islantEntityFactory = islantEntityFactory;
    }

    public static int initSizeOfField() {
        Scanner scanner = new Scanner(System.in);
        int sizeOfField;
        System.out.println("Добро пожаловать в симуляцию жизни острова!");
        System.out.println("Введите желаемый размер поля в диапазоне от 15 до 30 клеток");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Введите число от 15 до 30:");
                scanner.next();
            }
            sizeOfField = scanner.nextInt();
            if (sizeOfField < 15 || sizeOfField > 30) {
                System.out.println("Попробуйте снова, напоминаю: введите число от 15 до 30:");
            }
        } while (sizeOfField < 15 || sizeOfField > 30);
        System.out.println("Симуляция запущена, поле размером " + sizeOfField + " x " + sizeOfField + ", наслаждайтесь:)");
        return sizeOfField;
    }
}
