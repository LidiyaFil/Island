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
                    if (countOfPlantOnCellResolver(x, y, IslandEntityType.PLANT) < IslandEntityType.PLANT.getMaxAmount()) {
                        int countOfNewPlants = ThreadLocalRandom.current().nextInt(0, (IslandEntityType.PLANT.getMaxAmount() - 1) / 2);
//                        System.out.println("добавляем вот столько деревьев на клетку " + countOfNewPlants);
                        while (countOfNewPlants > 0) {
                            IslandEntity plant = abstractFactory.createEntity(x, y, IslandEntityType.PLANT);
                            IslandField.getInstance().getGameField()[x][y].add(plant);
                            countOfNewPlants--;
//                            System.out.println("add plant" + counter++);
                        }
                    }
                }
            }
            //todo
            if (countOfPlantOnFieldResolver(IslandEntityType.PLANT) <= IslandEntityType.PLANT.getMaxAmount()) {
                stopPlantGeneration();
                System.out.println("Генерация растений остановлена");
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
        return (int) Arrays.stream(islandField.getGameField())
                .flatMap(Arrays::stream)  // объединяем все списки в один поток
                .flatMap(List::stream)    // объединяем все элементы в один поток
                .filter(entity -> (entity).getType() == islandEntityType)  // фильтруем по типу
                .count();
    }
}
