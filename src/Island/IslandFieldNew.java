package src.Island;

import src.IslandLivingObject.Animals.AnimalFactory;
import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.PlantFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IslandFieldNew {
    // Экземпляр создается при загрузке класса
    //TODO надо сделать, чтобы пользователь мог установить размеры поля, пока по умолчанию 10х10
    private static final IslandFieldNew instance = new IslandFieldNew(10,10);
    private final int numRows;
    private final int numColumns;

    private static List[][] gameField;

    AnimalFactory animalFactory = new AnimalFactory();
    PlantFactory plantFactory = new PlantFactory();
    public int getNumRows() {
        return numRows;
    }

    private IslandFieldNew(int x, int y) {
        numRows = x;
        numColumns = y;
        gameField = new List[numRows][numColumns];
        createField();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public static List[][] getGameField() {
        return gameField;
    }

    public static IslandFieldNew getInstance() {
        return instance;
    }

    public void createField() {
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                //TODO будет выбирать юзер из заданного диапазона
                gameField[x][y] = new ArrayList<IslandEntity>();
                createAnimals(x, y, animalFactory);
                createPlants(x, y, plantFactory);
            }
        }
    }

    public void createAnimals(int x, int y, EntityFactory factory) {
        for (IslandEntityType type : IslandEntityType.values()) {
            int amountOfOneAnimal = ThreadLocalRandom.current().nextInt(0, type.getMaxAmount() + 1);
            while (amountOfOneAnimal > 0) {
                gameField[x][y].add(factory.createEntity(type));
                amountOfOneAnimal--;
            }
        }
    }

    public void createPlants(int x, int y, PlantFactory factory) {
        int amountOfPlants = ThreadLocalRandom.current().
                //TODO поменять magic digit
                nextInt(0, 10);
        while (amountOfPlants > 0) {
            gameField[x][y].add(factory.createEntity());
            amountOfPlants--;
        }
    }

//    public int countOfEntityResolver(Class<?> targetClass) {
//        return (int) entities.stream().filter(targetClass::isInstance).count();
//    }

    public static int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandEntity> entitiesInCell = gameField[x][y];
        return (int) entitiesInCell.stream()
                .filter(targetClass::isInstance)
                .count();
    }
}
