package src;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Coordinator {
    IslandField islandField = IslandField.getInstance();
    IslandEntityType array[] = IslandEntityType.values();
    private boolean isGameFieldExsist;

    // TODO добавить статиску животных по всему острову
    public void start() {
        List[][] gameField = islandField.getGameField();

        //даем проинициализироваться всем обектам
        if (!isGameFieldExsist) {
            try {
                Thread.sleep(100);
                isGameFieldExsist = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
       /* for (int i = 0; i < islandField.getNumColumns(); i++) {
            for (int j = 0; j < islandField.getNumRows(); j++) {
                System.out.println("\n" + "Counts in cell (" + i + ", " + j + "):" + "\n");
                for (IslandEntityType entityType : array) {
                    long count = countEntitiesInCell(gameField[i][j], entityType);
                    System.out.println(entityType + ": " + count);
                }
            }
        }*/
      /*  while (true) {
            System.out.println(countEntitiesInGameField(islandField));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/

        System.out.println(countEntitiesInGameField(islandField));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
