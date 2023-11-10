package src.Threads;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.Threads.PlantGenerationThread;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Coordinator extends Thread {
    IslandField islandField = IslandField.getInstance();
    IslandEntityType array[] = IslandEntityType.values();
    private boolean isGameFieldExsist;

    // TODO добавить статистику животных по всему острову
    public void run() {
        List[][] gameField = islandField.getGameField();

        //даем проинициализироваться всем обектам
        if (!isGameFieldExsist) {
            try {
                Thread.sleep(5000);
                isGameFieldExsist = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
// TODO убрать бесконечный цикл (когда все растения или хищники мертвы
        while (true) {
            System.out.println(countEntitiesInGameField(islandField));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long countEntitiesInCell(List<IslandEntity> entities, IslandEntityType type) {
        return entities.stream()
                .filter(entity -> entity.getType() == type)
                .count();
    }

    private HashMap countEntitiesInGameField(IslandField field) {
        HashMap<IslandEntityType, Integer> countOfEntityInGameField = new HashMap<>();

        for (IslandEntityType type : array) {
            countOfEntityInGameField.put(type, 0);
        }

        for (int i = 0; i < IslandField.getInstance().getNumRows() - 1; i++) {
            for (int j = 0; j < IslandField.getInstance().getNumRows() - 1; j++) {
                for (IslandEntityType type : array) {
                    long entitiesInCell = countEntitiesInCell(islandField.getGameField()[i][j], type);
                    int entitiesInMap = countOfEntityInGameField.get(type);
                    countOfEntityInGameField.put(type, (int) (entitiesInMap + entitiesInCell));
                }
            }
        }
        return countOfEntityInGameField;
    }
}
