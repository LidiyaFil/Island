package src.Island;

import com.fasterxml.jackson.core.JsonProcessingException;
import src.IslandLivingObject.CreatorLivingObject;
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
                for (IslandEntityType type : IslandEntityType.values()) {
                    int amountOfOneAnimal = ThreadLocalRandom.current().nextInt(0, 10);
                    while (amountOfOneAnimal > 0) {
                        IslandEntity entity = CreatorLivingObject.createObject(type);
                        row.add((entity);
                        amountOfOneAnimal--;
                    }

                }

//TODO тут надо сделать фабрику, которая будет генерить рандомом животных на данной клетке и передавать в виде new CopyOnWriteArrayList<>()
                //то есть мы создаем фабрику перед циклом и каждый раз обращаемся к ней для генерации животных и растений на клетке
                //таким образом, КМК мы получаем слабую связанность
                row.add(new Cell(x, y, new CopyOnWriteArrayList<>()));
            }
        }
        return cellLists;
    }
}
