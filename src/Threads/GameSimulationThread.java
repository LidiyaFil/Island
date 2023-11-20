package src.Threads;


import src.Actions.MovingService;
import src.Actions.NutritionService;
import src.Actions.ReproductionService;
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
    private final StatisticThread thread = new StatisticThread();

//    private int coreCount;

    public GameSimulationThread(NutritionService nutrition, ReproductionService reproduction, MovingService moving) {
        this.nutrition = nutrition;
        this.reproduction = reproduction;
        this.moving = moving;
        this.running = true;
        // подсчет количества процессоров, для задания необходимого пула потоков
        // this.coreCount = Runtime.getRuntime().availableProcessors();
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
        try {
            //TODO wait -> notify
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (running) {
            actEntity();
        }
        //todo final showing statistic
    }

    private void actEntity() {

        Arrays.stream(islandField.getGameField())
                .parallel()
                .flatMap(Arrays::stream)
                .forEach(list -> {
                    list.parallelStream()
                            .filter(entity -> !(entity instanceof AbstractPlant))
                            .forEach(entity -> {
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
