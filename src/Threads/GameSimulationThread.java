package src.Threads;

import src.Actions.*;
import src.Actions.Servicies.*;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.*;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameSimulationThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    NutritionService nutrition;
    ReproductionService reproduction;
    MovingService moving;
    protected static boolean running;
//    private int coreCount;

    private boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    private void stopSimulation() {
        running = false;
    }

    public GameSimulationThread() {
        this.running = true;
        // подсчет количества процессоров, для задания необходимого пула потоков
        // this.coreCount = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public void run() {
        while (running) {
            actEntity();
            if (areAllPredatorsDead() || areAllHerbivorousDead()) {
                stopSimulation();
            }
        }
        StatisticThread thread = new StatisticThread();
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Симуляция завершена, хищников или травоядных не осталось на карте");
    }

    private void actEntity() {
        for (List[] lists : islandField.getGameField()) {
            for (List list : lists) {
                for (Object entity : list) {
                    if (entity instanceof AbstractPlant) {
                        continue;
                    } else {
                        new NutritionService((Eateble) entity).eat(list);
                    }
                }
                for (Object entity : list) {
                    if (entity instanceof AbstractPlant) {
                        continue;
                    } else {
                        new ReproductionService((AbstractAnimal) entity).reproduce(list);
                    }
                }
                for (Object entity : list) {
                    if (!(entity instanceof AbstractPlant)) {
                        new MovingService((AbstractAnimal) entity).move((AbstractAnimal) entity);
                    } else {
                        continue;
                    }
                }
            }
        }
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
        return true;
    }

    // условие выхода из симуляции - все травоядные мертвы
    private boolean areAllHerbivorousDead() {
        for (int i = 0; i < islandField.getNumRows(); i++) {
            for (int j = 0; j < islandField.getNumColumns(); j++) {
                List<IslandEntity> entities = islandField.getGameField()[i][j];
                for (IslandEntity entity : entities) {
                    if (entity instanceof Herbivorous) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
