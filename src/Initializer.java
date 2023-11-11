package src;

import java.util.Scanner;

public class Initializer {
    public static int initSizeOfField() {
        Scanner scanner = new Scanner(System.in);
        int sizeOfField;
        System.out.println("Добро пожаловать в симуляцию жизни острова!");
        System.out.println("Введите желаемый размер поля в диапазоне от 10 до 20 клеток");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Введите число от 10 до 20:");
                scanner.next();
            }
            sizeOfField = scanner.nextInt();
            if (sizeOfField < 10 || sizeOfField > 20) {
                System.out.println("Попробуйте снова, напоминаю: введите число от 10 до 20:");
            }
        } while (sizeOfField < 10 || sizeOfField > 20);
        System.out.println("Симуляция запущена, поле размером " + sizeOfField + " x " + sizeOfField + ", наслаждайтесь:)");
        return sizeOfField;
    }
}
