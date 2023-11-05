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
    //TODO сделать singleton и реализовать метод getInstance
    private final int numRows;

    private final int numColumns;
    private static List[][] gameField;

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    AnimalFactory animalFactory = new AnimalFactory();
    PlantFactory plantFactory = new PlantFactory();

    public IslandFieldNew(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        gameField = new CopyOnWriteArrayList[numRows][numColumns];
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

    public void createPlants(int x, int y, EntityFactory factory) {
        int amountOfPlants = ThreadLocalRandom.current().
                nextInt(0, IslandEntityType.Plant.getMaxAmount());
        while (amountOfPlants > 0) {
            gameField[x][y].add(factory.createEntity(IslandEntityType.Plant));
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
