package src.Threads;


import src.Actions.*;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.*;
import src.IslandLivingObject.Animals.Herbivorous.Caterpillar;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.*;

import java.util.Arrays;
import java.util.List;


public class GameSimulationThread extends Thread {
    private final IslandField islandField = IslandField.getInstance();
    private final NutritionService nutrition;
    private final ReproductionService reproduction;
    private final MovingService moving;
    protected boolean running;
    private EntityRemover remover;


    public GameSimulationThread(NutritionService nutrition,
                                ReproductionService reproduction,
                                MovingService moving,
                                EntityRemover remover) {
        this.nutrition = nutrition;
        this.reproduction = reproduction;
        this.moving = moving;
        this.running = true;
        this.remover = remover;
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
        int counter = 0;

        //todo чтото тут не так
        while (running) {
            actEntity();
            System.out.println("цикл " + counter++);
            try {
                this.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void actEntity() {

        Arrays.stream(islandField.getGameField())
                .parallel()
                .flatMap(Arrays::stream)
                .forEach(list -> {
                    list.parallelStream()
                            .filter(entity -> !(entity instanceof AbstractPlant))
                            .forEachOrdered(entity -> {
                                nutrition.eat(list, (AbstractAnimal) entity);
                                reproduction.reproduceAllAnimalOnCell(list, entity);
                                moving.move((AbstractAnimal) entity);
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
}
