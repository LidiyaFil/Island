package src.Threads;

import src.Island.IslandField;
import src.IslandLivingObject.AbstractFactory;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.Plant;

public class PlantGenerationThread extends Thread {
    private IslandField islandField;
    private boolean running;
    AbstractFactory abstractFactory = new AbstractFactory();

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
                    abstractFactory.createEntity(x, y, IslandEntityType.PLANT);
                    System.out.println("Выросло новое растение на клетке " + x + " " + y);
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