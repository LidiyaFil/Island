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
    private final IslandField islandField = IslandField.getInstance();
    private final NutritionService nutrition;
    private final ReproductionService reproduction;
    private  MovingService moving;
    protected boolean running;
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
        while (running) {
            actEntity();
            if (areAllPredatorsDead() || areAllHerbivorousDead()) {
                stopSimulation();
            }
        }
        StatisticThread thread = new StatisticThread();
        thread.start();
        try {
            //TODO wait -> notify
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void actEntity() {
        for (List<IslandEntity>[] lists : islandField.getGameField()) {
            for (List<IslandEntity> list : lists) {
                for (IslandEntity entity : list) {
                    if (!(entity instanceof AbstractPlant)) {
                        nutrition.eat(list, (AbstractAnimal) entity);
                    }
                }
                for (Object entity : list) {
                    if (!(entity instanceof AbstractPlant)) {
                        reproduction.reproduce(list);
                    }
                }
                for (Object entity : list) {
                    if (!(entity instanceof AbstractPlant)) {
                        this.moving = new MovingService();
                        moving.move((AbstractAnimal) entity);
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
                        return false;
                    }
                }
            }
        }
        System.out.println("Симуляция завершена, травоядных не осталось на карте");
        return true;
    }
}
