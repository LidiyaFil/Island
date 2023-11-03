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
        LivingObjectFactory livingObjectFactory = new LivingObjectFactory();
        IslandEntityType[] values = IslandEntityType.values();

        for (int x = 0; x < numRows; x++) {
            CopyOnWriteArrayList<Cell> row = new CopyOnWriteArrayList<>();
            cellLists.add(row);

            for (int y = 0; y < numCols; y++) {
                CopyOnWriteArrayList<IslandEntity> animals = createAnimals();
                row.add(new Cell(x, y, animals));
            }
        }
        return cellLists;
    }

    public CopyOnWriteArrayList<IslandEntity> createAnimals() {
        CopyOnWriteArrayList<IslandEntity> entities = new CopyOnWriteArrayList<>();

        for (IslandEntityType type : IslandEntityType.values()) {
            // TODO Когда мы прикрутим треды, объекты будут создаваться в многопоточной среде.
            //  Или ты хочешь разделить первоначальное заполнение "карты" животными и создание новых объктов в ходе выполнения программы?
            //  10 - ради примера из головы, потом это число можем предложить выбрать из диапазона при запуске программы юзером.
            int amountOfOneAnimal = ThreadLocalRandom.current().nextInt(0, 10);
            // TODO Мне очень не нравилась эта чать кода - циклы в циклах и циклами погоняют.
            //  Вынесла часть логики в отдельный метод, возможно нужно разбить на большее количество отдельных методов, как думаешь?.
            while (amountOfOneAnimal > 0) {
                // TODO на обсуждение - метод createObject хочет быть статичным: "на нестатический метод нельзя ссылаться из статического контекста".
                //  А где в этом классе статика - не понимаю.
//                IslandEntity entity = LivingObjectFactory.createObject(type);
//                entities.add(entity);
                amountOfOneAnimal--;
            }
        }
        return entities;
    }
}
