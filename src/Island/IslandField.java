package src.Island;

import src.Initializer;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.Runner;

import javax.print.attribute.standard.Fidelity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IslandField {
    private static int sizeOfField = Initializer.initSizeOfField();
    private static final IslandField instance = new IslandField(sizeOfField, sizeOfField);
    private final int numRows;
    private final int numColumns;
    private List[][] gameField;

    private IslantEntityFactory factory = new IslantEntityFactory();

    private IslandField(int x, int y) {
        numRows = x;
        numColumns = y;
        gameField = new CopyOnWriteArrayList[numRows][numColumns];
        createField();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public List[][] getGameField() {
        return gameField;
    }

    public static IslandField getInstance() {
        return instance;
    }

    private void createField() {
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                gameField[x][y] = new CopyOnWriteArrayList<IslandEntity>();
                firstCreateEntity(x, y, factory);
            }
        }
    }

    // первоначальное заполнение поля животными и растениями
    private void firstCreateEntity(int x, int y, IslantEntityFactory factory) {
        for (IslandEntityType type : IslandEntityType.values()) {
            int amountOfOneTypeOfEntity = ThreadLocalRandom.current().nextInt(5, type.getMaxAmount() + 1);
            while (amountOfOneTypeOfEntity > 0) {
                IslandEntity entity = factory.createEntity(x, y, type);
                entity.setX(x);
                entity.setY(y);
                if (entity instanceof AbstractAnimal) {
                    ((AbstractAnimal) entity).setSaturation(entity.getType().getFullSaturation() / 0.75);
                }
                gameField[x][y].add(entity);
                amountOfOneTypeOfEntity--;
            }
        }
    }
}
