package src.Threads;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.*;

public class StatisticThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    IslandEntityType array[] = IslandEntityType.values();
    private boolean isGameFieldExsist;
    private boolean running;

    public StatisticThread() {
        this.running = true;
    }

    public void run() {
        List[][] gameField = islandField.getGameField();

        //даем проинициализироваться всем обектам
        if (!isGameFieldExsist) {
            try {
                Thread.sleep(2000);
                isGameFieldExsist = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (running) {
            System.out.println(countEntitiesInGameField(islandField));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (GameSimulationThread.running == false) {
                stopStatisticThread();
            }
        }
    }

    private long countEntitiesInCell(List<IslandEntity> entities, IslandEntityType type) {
        return entities.stream()
                .filter(entity -> entity.getType() == type)
                .count();
    }

    private HashMap countEntitiesInGameField(IslandField field) {
        HashMap<String, Integer> countOfEntityInGameField = new HashMap<>();

        for (IslandEntityType type : array) {
            countOfEntityInGameField.put(type.getIcon(), 0);
        }

        for (int i = 0; i < IslandField.getInstance().getNumRows() - 1; i++) {
            for (int j = 0; j < IslandField.getInstance().getNumRows() - 1; j++) {
                for (IslandEntityType type : array) {
                    long entitiesInCell = countEntitiesInCell(islandField.getGameField()[i][j], type);
                    int entitiesInMap = countOfEntityInGameField.get(type.getIcon());
                    countOfEntityInGameField.put(type.getIcon(), (int) (entitiesInMap + entitiesInCell));
                }
            }
        }
        return countOfEntityInGameField;
    }

    public void stopStatisticThread() {
        running = false;
    }
}
