package src.Island;

import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.LivingObjectFactory;
import src.IslandLivingObject.Plants.AbstractPlants;
import src.IslandLivingObject.Plants.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IslandFieldNew {
    private final int numRows;
    private final int numColumns;
    private final List[][] gameField;

    LivingObjectFactory livingObjectFactory = new LivingObjectFactory();

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
                createAnimals(x, y);
                createPlants(x, y);
            }
        }
    }

    public void createAnimals(int x, int y) {
        for (IslandEntityType type : IslandEntityType.values()) {
            //TODO будет выбирать юзер из заданного диапазона
            int amountOfOneAnimal = ThreadLocalRandom.current().nextInt(0, 10);
            while (amountOfOneAnimal > 0) {
                IslandEntity entity = livingObjectFactory.createObject(type);
                gameField[x][y].add(entity);
                amountOfOneAnimal--;
            }
        }
    }

    public void createPlants(int x, int y) {
        //TODO будет выбирать юзер из заданного диапазона
        int amountOfPlants = ThreadLocalRandom.current().nextInt(0, 5);
        while (amountOfPlants > 0) {
            AbstractPlants plant = new Plant();
            gameField[x][y].add(plant);
        }
    }

//    public int countOfEntityResolver(Class<?> targetClass) {
//        return (int) entities.stream().filter(targetClass::isInstance).count();
//    }

    public int countOfEntityResolver(Class<?> targetClass) {
        // подозреваю, тут подсчет идет по количеству конкретных животных всего поля, а не клетки
        return (int) Arrays.stream(gameField).filter(targetClass::isInstance).count();
    }
}
