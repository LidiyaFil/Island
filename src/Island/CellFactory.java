package src.Island;

import com.fasterxml.jackson.core.JsonProcessingException;
import src.IslandLivingObject.LivingObjectFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CellFactory {
    public List<CopyOnWriteArrayList<Cell>> createCellLists(int numRows, int numCols) throws JsonProcessingException {
        List<CopyOnWriteArrayList<Cell>> cellLists = new CopyOnWriteArrayList<>();

        for (int x = 0; x < numRows; x++) {
            CopyOnWriteArrayList<Cell> row = new CopyOnWriteArrayList<>();
            cellLists.add(row);

            for (int y = 0; y < numCols; y++) {
                CopyOnWriteArrayList<IslandEntity> entities = createAnimalsAndPlants();
                row.add(new Cell(x, y, entities));
            }
        }
        return cellLists;
    }

    public CopyOnWriteArrayList<IslandEntity> createAnimalsAndPlants() throws JsonProcessingException {
        CopyOnWriteArrayList<IslandEntity> entities = new CopyOnWriteArrayList<>();
        LivingObjectFactory livingObjectFactory = new LivingObjectFactory();
        IslandEntityType[] values = IslandEntityType.values();

        for (IslandEntityType type : values) {
            int amountOfOneAnimal = ThreadLocalRandom.current().nextInt(0, 10);
            while (amountOfOneAnimal > 0) {
                IslandEntity entity = livingObjectFactory.createObject(type);
                entities.add(entity);
                amountOfOneAnimal--;
            }
        }
        return entities;
    }
}