package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MovingService {

    public void move(AbstractAnimal abstractAnimal) {
        int steps = abstractAnimal.getType().getMaxMove();
        int newX = abstractAnimal.getX();
        int newY = abstractAnimal.getY();

        while (steps-- > 0) {
            int direction = ThreadLocalRandom.current().nextInt(4);

            if (direction == 0) {
                newX++; // east
            } else if (direction == 1) {
                newX--; // west
            } else if (direction == 2) {
                newY++; // south
            } else {
                newY--; // north
            }

            if (isValidPosition(newX, newY)) {
                moveEntity(abstractAnimal, newX, newY);
            }

            abstractAnimal.setReproduced(false);
            abstractAnimal.doStarvation();
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0
                && x < IslandField.getInstance().getNumRows()
                && y >= 0
                && y < IslandField.getInstance().getNumColumns();
    }

    private void moveEntity(AbstractAnimal islandEntity, int x, int y) {


        int oldIslandEntityX = islandEntity.getX();
        int oldIslandEntityY = islandEntity.getY();

        if (countOfEntityResolver(x, y, islandEntity.getClass()) < islandEntity.getType().getMaxAmount()) {

            IslandField.getInstance().getGameField()[oldIslandEntityX][oldIslandEntityY].remove(islandEntity);

            islandEntity.setX(x);
            islandEntity.setY(y);
            IslandField.getInstance().getGameField()[x][y].add(islandEntity);
        }
    }

    private int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandEntity> entitiesInCell = IslandField.getInstance().getGameField()[x][y];
        return (int) entitiesInCell.stream().filter(targetClass::isInstance).count();
    }
}
