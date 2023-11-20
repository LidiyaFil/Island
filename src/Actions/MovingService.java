package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MovingService {
    private final LiveAbilityValidator liveAbilityValidator = new LiveAbilityValidator();
    private final EntityRemover remover = new EntityRemover(liveAbilityValidator);





    public void move(AbstractAnimal abstractAnimal) {
        if (!liveAbilityValidator.checkLiveAbility(abstractAnimal)) {
            remover.removeOrStayAnimal(abstractAnimal);
            return;
        }

        if (abstractAnimal.getSaturation() < 0) {
            abstractAnimal.die();
            return;
        }

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

        }
        abstractAnimal.setReproduced(false);
        abstractAnimal.setReproduceTrying(3);
        abstractAnimal.doStarvation();
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

    public EntityRemover getRemover() {
        return remover;
    }
}
