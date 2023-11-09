package src.Island;

import src.IslandLivingObject.Animals.AnimalFactory;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.PlantFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IslandField {
    // Экземпляр создается при загрузке класса
    //TODO надо сделать, чтобы пользователь мог установить размеры поля, пока по умолчанию 10х10
    private static final IslandField instance = new IslandField(10, 10);
    private final int numRows;
    private final int numColumns;
    private static List[][] gameField;

    AnimalFactory animalFactory = new AnimalFactory();
    PlantFactory plantFactory = new PlantFactory();

    public int getNumRows() {
        return numRows;
    }

    public IslandField(int x, int y) {
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

    public static IslandField getInstance() {
        return instance;
    }

    private void createField() {
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                //TODO будет выбирать юзер из заданного диапазона
                gameField[x][y] = new ArrayList<IslandEntity>();
                createAnimals(x, y, animalFactory);
                createPlants(x, y, plantFactory);
            }
        }
    }

    private void createAnimals(int x, int y, AnimalFactory factory) {
        for (IslandEntityType type : IslandEntityType.values()) {
            int amountOfOneAnimal = ThreadLocalRandom.current().nextInt(0, type.getMaxAmount() + 1);
            while (amountOfOneAnimal > 0) {
                IslandEntity entity = factory.createEntity(x, y, type);
                // установить новой сущности поля X и Y
                entity.setX(x);
                entity.setY(y);
                gameField[x][y].add(entity);
                amountOfOneAnimal--;
            }
        }
    }

    private void createPlants(int x, int y, PlantFactory factory) {
        //TODO поменять magic digit
        int amountOfPlants = ThreadLocalRandom.current().nextInt(0, 10);
        while (amountOfPlants > 0) {
            IslandEntity entity = factory.createEntity(x, y, IslandEntityType.PLANT);
            // по аналогии с созданием животных
            entity.setX(x);
            entity.setY(y);
            gameField[x][y].add(entity);
            amountOfPlants--;
        }
    }

    // TODO перенести в другой класс, здесь не должно быть этого метода
    public static int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandEntity> entitiesInCell = gameField[x][y];
        return (int) entitiesInCell.stream().filter(targetClass::isInstance).count();
    }

    public boolean areAllPredatorsDead() {
        List[][] gameField = IslandField.getGameField();
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                List<IslandEntity> entities = gameField[i][j];
                for (IslandEntity entity : entities) {
                    if (entity instanceof Predators) {
                        // Если найден живой хищник, вернуть false
                        return false;
                    }
                }
            }
        }
        // Вернуть true, если все хищники мертвы
        return true;
    }
}
