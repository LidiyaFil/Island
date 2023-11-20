package src.Threads;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityFactory;
import src.IslandLivingObject.IslandEntityType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGenerationThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    private boolean running;
    private IslandEntityFactory abstractFactory = new IslandEntityFactory();

    public PlantGenerationThread() {
        this.running = true;
    }

    @Override
    public void run() {
//        long counter = 0;
        while (running) {
            // Генерируем новые растения на каждой клетке
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int x = 0; x < islandField.getNumRows(); x++) {
                for (int y = 0; y < islandField.getNumColumns(); y++) {
                    if (countOfPlantOnCellResolver(x, y) < IslandEntityType.PLANT.getMaxAmount()) {
                        int countOfNewPlants = ThreadLocalRandom.current().nextInt(0, (IslandEntityType.PLANT.getMaxAmount() - 1) / 5);
                        while (countOfNewPlants > 0) {
                            IslandEntity plant = abstractFactory.createEntity(x, y, IslandEntityType.PLANT);
                            IslandField.getInstance().getGameField()[x][y].add(plant);
                            countOfNewPlants--;
                        }
                    }
                }
            }
            if (countOfPlantOnFieldResolver() <= IslandEntityType.PLANT.getMaxAmount()) {
                stopPlantGeneration();
                System.out.println("Генерация растений остановлена");
            }
        }
    }

    public void stopPlantGeneration() {
        running = false;
    }

    private int countOfPlantOnCellResolver(int x, int y) {
        List<IslandEntity> entitiesInCell = islandField.getGameField()[x][y];
        return (int) entitiesInCell.stream()
                .filter(entity -> entity.getType() == IslandEntityType.PLANT)
                .count();
    }

    private int countOfPlantOnFieldResolver() {
        return (int) Arrays.stream(islandField.getGameField())
                .flatMap(Arrays::stream)  // объединяем все списки в один поток
                .flatMap(List::stream)    // объединяем все элементы в один поток
                .filter(entity -> (entity).getType() == IslandEntityType.PLANT)  // фильтруем по типу
                .count();
    }
}
