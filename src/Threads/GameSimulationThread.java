package src.Threads;


import src.Actions.*;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.*;
import src.IslandLivingObject.Animals.Herbivorous.Caterpillar;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameSimulationThread extends Thread {
    private final IslandField islandField = IslandField.getInstance();
    private final NutritionService nutrition;
    private final ReproductionService reproduction;
    private final MovingService moving;
    protected boolean running;
    int counter = 0;
    IslandEntityType[] array = IslandEntityType.values();

    public GameSimulationThread(NutritionService nutrition,
                                ReproductionService reproduction,
                                MovingService moving) {
        this.nutrition = nutrition;
        this.reproduction = reproduction;
        this.moving = moving;
        this.running = true;

    }

    private boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    private void stopSimulation() {
        running = false;
    }

    @Override
    public void run() {


        //todo что-то тут не так. Почему они перестают есть размножаться и умирать
        //почему то остаются те, кто уже должен был погибнуть от голода
        while (running) {
            System.out.println(countEntitiesInGameField());
            actEntity();
            System.out.println("цикл " + counter++);
        /*    try {
                System.out.println("зашли в ожидание" + this);
                this.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(countEntitiesInGameField() + " после");*/
        }
    }

    private void actEntity() {

        Arrays.stream(islandField.getGameField())
                .parallel()
                .flatMap(Arrays::stream)
                .forEachOrdered(list -> {
                    list.parallelStream()
                            .filter(entity -> !(entity instanceof AbstractPlant))
                            .forEachOrdered(entity -> {
                                if (entity instanceof AbstractAnimal) {
                                    if (((AbstractAnimal) entity).getSaturation() < 0) {
                                        ((AbstractAnimal) entity).die();
                                        return;
                                    }
                                    nutrition.eat(list, (AbstractAnimal) entity);
                                    reproduction.reproduceAllAnimalOnCell(list, entity);
                                    moving.move((AbstractAnimal) entity);
                                }
                            });
                });
    }

    // условие выхода из симуляции - все хищники мертвы
    private boolean areAllPredatorsDead() {
        for (int i = 0; i < islandField.getNumRows(); i++) {
            for (int j = 0; j < islandField.getNumColumns(); j++) {
                List<IslandEntity> entities = islandField.getGameField()[i][j];
                for (IslandEntity entity : entities) {
                    if (entity instanceof Predators) {
                        return false;
                    }
                }
            }
        }
        System.out.println("Симуляция завершена, хищников не осталось на карте");
        return true;
    }

    // условие выхода из симуляции - все травоядные мертвы
    private boolean areAllHerbivorousDead() {
        for (int i = 0; i < islandField.getNumRows(); i++) {
            for (int j = 0; j < islandField.getNumColumns(); j++) {
                List<IslandEntity> entities = islandField.getGameField()[i][j];
                for (IslandEntity entity : entities) {
                    if (entity instanceof Herbivorous) {
                        if ((entity instanceof Caterpillar)) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        System.out.println("Симуляция завершена, травоядных не осталось на карте");
        return true;
    }

    public Map<String, Integer> countEntitiesInGameField() {
        HashMap<String, Integer> countOfEntityInGameField = new HashMap<>();


        //todo возможно добавлять в мапу не по типу, а по конкретному животному?
        for (IslandEntityType type : array) {
            countOfEntityInGameField.put(type.getIcon(), 0);
        }

        for (int i = 0; i < IslandField.getInstance().getNumRows() - 1; i++) {
            for (int j = 0; j < IslandField.getInstance().getNumRows() - 1; j++) {
                for (IslandEntityType type : array) {
                    long entitiesInCell = countEntitiesInCell(islandField.getGameField()[i][j], type);
                    int entitiesInMap = countOfEntityInGameField.get(type.getIcon());
                    countOfEntityInGameField.put(type.getIcon(), (int) (entitiesInMap + entitiesInCell));
                }
            }
        }
        return countOfEntityInGameField;
    }

    public long countEntitiesInCell(List<IslandEntity> entities, IslandEntityType type) {
        return entities.stream()
                .filter(entity -> entity.getType() == type)
                .count();
    }
}
