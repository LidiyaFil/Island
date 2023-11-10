package src.Threads;

import src.Island.IslandField;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntityType;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGenerationThread extends Thread {
    private IslandField islandField;
    private boolean running;
    private IslantEntityFactory abstractFactory = new IslantEntityFactory();

    public PlantGenerationThread(IslandField islandField) {
        this.islandField = islandField;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            // Генерируем новые растения на каждой клетке
            for (int x = 0; x < islandField.getNumRows(); x++) {
                for (int y = 0; y < islandField.getNumColumns(); y++) {
                    //TODO откуда 10? Добавить проверку на максимальное количество имеющихся растений на клетке
                    int countOfNewPlants = ThreadLocalRandom.current().nextInt(0, 10);
                    while (countOfNewPlants > 0) {
                        abstractFactory.createEntity(x, y, IslandEntityType.PLANT);
//                        System.out.println("Выросло новое растение на клетке " + x + " " + y);
                        countOfNewPlants--;
                    }

                }
            }
            // Задержка между генерациями
            try {
                Thread.sleep(1000); // например, 1000 миллисекунд (1 секунда)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlantGeneration() {
        running = false;
    }
}
