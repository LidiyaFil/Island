package src.Island;

import src.Initializer;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.IslandEntityFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IslandField {
    private static final int sizeOfField = Initializer.initSizeOfField();
    private static final IslandField instance = new IslandField(sizeOfField, new IslandEntityFactory());
    private final IslandEntityFactory islandEntityFactory;
    private final List<IslandEntity>[][] gameField;
    private final int numRows;
    private final int numColumns;

    @SuppressWarnings("unchecked")
    private IslandField(int x, IslandEntityFactory islandEntityFactory) {
        numRows = x;
        numColumns = x;
        gameField = new CopyOnWriteArrayList[numRows][numColumns];
        this.islandEntityFactory = islandEntityFactory;
        createField();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public List<IslandEntity>[][] getGameField() {
        return gameField;
    }

    public static IslandField getInstance() {
        return instance;
    }

    private void createField() {
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                gameField[x][y] = new CopyOnWriteArrayList<>();
                firstInitEntities(x, y);
            }
        }
    }

    public void firstInitEntities(int x, int y) {
        for (IslandEntityType type : IslandEntityType.values()) {
            int amountOfOneTypeOfEntity = ThreadLocalRandom.current().nextInt(1, type.getMaxAmount() + 1);
            while (amountOfOneTypeOfEntity > 0) {
                IslandEntity entity = islandEntityFactory.createEntity(x, y, type);
                if (entity instanceof AbstractAnimal) {
                    ((AbstractAnimal) entity).setSaturation(entity.getType().getFullSaturation() * 0.5);
                }
                gameField[x][y].add(entity);
                amountOfOneTypeOfEntity--;
            }
        }
    }
}
