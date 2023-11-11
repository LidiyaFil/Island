package src.Threads;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.AbstractPlant;
import src.IslandLivingObject.Plants.Plant;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGenerationThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    private boolean running;
    private IslantEntityFactory abstractFactory = new IslantEntityFactory();

    public PlantGenerationThread() {
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            // Генерируем новые растения на каждой клетке
            for (int x = 0; x < islandField.getNumRows(); x++) {
                for (int y = 0; y < islandField.getNumColumns(); y++) {
                    if (countOfPlantOnCellResolver(x, y, IslandEntityType.PLANT) < IslandEntityType.PLANT.getMaxAmount()) {
                        int countOfNewPlants = ThreadLocalRandom.current().nextInt(0, 50);
                        while (countOfNewPlants > 0) {
                            abstractFactory.createEntity(x, y, IslandEntityType.PLANT);
                            countOfNewPlants--;
                        }
                    }
                }
            }
            if (countOfPlantOnFieldResolver(IslandEntityType.PLANT) <= 100) {
                stopPlantGeneration();
//                System.out.println("Генерация растений остановлена");
            }
        }
    }

    public void stopPlantGeneration() {
        running = false;
    }

    private int countOfPlantOnCellResolver(int x, int y, IslandEntityType islandEntityType) {
        List<IslandEntity> entitiesInCell = islandField.getGameField()[x][y];
        return (int) entitiesInCell.stream()
                .filter(entity -> entity.getType() == islandEntityType)
                .count();
    }

    private int countOfPlantOnFieldResolver(IslandEntityType islandEntityType) {
        return Arrays.stream(islandField.getGameField())
                .flatMap(Arrays::stream)  // объединяем все списки в один поток
                .flatMap(List::stream)    // объединяем все элементы в один поток
                .filter(entity -> ((IslandEntity) entity).getType() == islandEntityType)  // фильтруем по типу
                .mapToInt(entity -> 1)  // преобразуем каждый элемент в 1
                .sum();  // суммируем результаты
    }
}
