package src.Island;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IslandField {
    // Экземпляр создается при загрузке класса
    //TODO надо сделать, чтобы пользователь мог установить размеры поля, пока по умолчанию 10х10
    private static final IslandField instance = new IslandField(10, 10);
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
                //TODO будет выбирать юзер из заданного диапазона
                gameField[x][y] = new CopyOnWriteArrayList<IslandEntity>();
                createEntity(x, y, factory);
            }
        }
    }

    // первоначальное заполнение поля животными (и растением)
    private void createEntity(int x, int y, IslantEntityFactory factory) {
        for (IslandEntityType type : IslandEntityType.values()) {
            int amountOfOneTypeOfEntity = ThreadLocalRandom.current().nextInt(0, type.getMaxAmount() + 1);
            while (amountOfOneTypeOfEntity > 0) {
                IslandEntity entity = factory.createEntity(x, y, type);
                // установить новой сущности поля X и Y
                entity.setX(x);
                entity.setY(y);
                if (entity instanceof AbstractAnimal) {
                    ((AbstractAnimal) entity).setSaturation(entity.getType().getFullSaturation() / 2);
                }
                if ((x > 4) && (y > 5)) {
//                    System.out.println("Попытка добавить сущность на клетку " + x + " " + y);
//                    System.out.println("добавляем животное " + entity.getType() + " на клетку " + entity.getX() + " " + entity.getY());
                }
                gameField[x][y].add(entity);
                amountOfOneTypeOfEntity--;
            }
        }
    }

    // TODO перенести в другой класс, здесь не должно быть этого метода
    public int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandEntity> entitiesInCell = gameField[x][y];
        return (int) entitiesInCell.stream().filter(targetClass::isInstance).count();
        // закоментированная запись работала на 10.11. Попробовала переписать через стримы.
//        int count = 0;
//        for (IslandEntity entity: entitiesInCell) {
//            if (targetClass.isInstance(entity)) {
//                count++;
//            }
//        }
//        return count;
    }
}
