package src;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Coordinator {
    IslandField islandField = IslandField.getInstance();
    IslandEntityType array[] = IslandEntityType.values();

    // TODO иногда (~1 из 10 раз) падает на ошибке, думаю это связано с тем, что животные не успевают проинициализироваться и "встать" на клетку
    public void start() {
        List[][] gameField = IslandField.getGameField();
        for (int i = 0; i < islandField.getNumColumns(); i++) {
            for (int j = 0; j < islandField.getNumRows(); j++) {
                System.out.println("\n" + "Counts in cell (" + i + ", " + j + "):" + "\n");
                for (IslandEntityType entityType : array) {
                    long count = countEntitiesInCell(gameField[i][j], entityType);
                    System.out.println(entityType + ": " + count);
                }
            }
        }
    }

    private long countEntitiesInCell(List<IslandEntity> entities, IslandEntityType type) {
        return entities.stream()
                .filter(entity -> entity.getType() == type)
                .count();
    }


}
