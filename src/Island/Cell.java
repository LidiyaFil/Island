package src.Island;

import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;

import java.util.List;
@Deprecated
public class Cell {
    public int X;

    public int Y;

    public List<IslandEntity> getEntities() {
        return entities;
    }

    public List<IslandEntity> entities;

    public Cell(int x, int y, List<IslandEntity> entities) {
        X = x;
        Y = y;
        this.entities = entities;
    }

    public void eating() {
        entities.forEach(entity -> {
            if (entity instanceof Predators) {
                ((Predators) entity).eat(entities);
            } else if (entity instanceof Herbivorous) {
                ((Herbivorous) entity).eat(entities);
            }
        });
    }

    public void multiplying() {
        entities.forEach(entity -> {
            if (entity instanceof Predators) {
                ((Predators) entity).reproduce(entities);
            } else if (entity instanceof Herbivorous) {
                ((Herbivorous) entity).reproduce(entities);
            }
        });
    }

    public void moving() {
        entities.forEach(entity -> {
            if (entity instanceof Predators) {
                //TODO надо добавить поле животным (только вот куда?) и геттер для количества шагов
                ((Predators) entity).move(((Predators) entity).getMaxMove(), X, Y);
            } else if (entity instanceof Herbivorous) {
                ((Herbivorous) entity).move(((Herbivorous) entity).getMaxMove(), X, Y);
            }
        });
    }

 /*   public void growing() {
        entities.forEach(entity -> {
            if (entity instanceof Plant) {
                ((Plant) entity).grow();
            }
        });
    }*/

    // добавить метод DIE()

    /**
     * Метод countOfEntityResolver выполняет подсчет количества объектов в списке entities,
     * которые являются экземплярами указанного класса targetClass.
     *
     * @param targetClass Класс, экземпляры которого нужно подсчитать в списке entities.
     */
    public int countOfEntityResolver(Class<?> targetClass) {
        return (int) entities.stream().filter(targetClass::isInstance).count();
    }
}
