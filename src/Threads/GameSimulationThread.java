package src.Threads;

import src.Actions.Eateble;
import src.Actions.Reproducible;
import src.Actions.Servicies.MovingService;
import src.Actions.Servicies.NutritionService;
import src.Actions.Servicies.ReproductionService;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.Plants.AbstractPlant;
import src.IslandLivingObject.Plants.Plant;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameSimulationThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    NutritionService nutrition;
    ReproductionService reproduction;
    MovingService moving;
    private boolean running;

    private int coreCount;

    public GameSimulationThread() {
        this.running = true;
        // подсчет количества процессоров, для задания необходимого пула потоков
        this.coreCount = Runtime.getRuntime().availableProcessors();
    }

    // TODO надо сделать таски, которые будут делаться посписочно, взависимоти от того, сколько процессоров в системе
    @Override
    public void run() {

        while (running) {
            actEntity();

            if (checkEndCondition()) {
                running = false;
            }
        }
        System.out.println("нить отработала");
    }

    public void stopSimulation() {
        running = false;
    }

    private boolean checkEndCondition() {
        return areAllPredatorsDead();
    }

    private void actEntity() {
        
        for (List[] lists : islandField.getGameField()) {
            for (List list : lists) {
//                System.out.println("пытаемся поесть");
                for (Object entity : list) {
                    if (entity instanceof AbstractPlant) {
                        continue;
                    } else {
                        new NutritionService((Eateble) entity).eat(list);
                    }
                }
//                System.out.println("успешно поели");
//                System.out.println("пытаемся размножиться");
                for (Object entity : list) {
                    if (entity instanceof AbstractPlant) {
                        continue;
                    } else {
                        new ReproductionService((AbstractAnimal) entity).reproduce(list);
                    }
                }
//                System.out.println("успешно размножились");
//                System.out.println("пытаемся пойти");
                for (Object entity : list) {
                    if (!(entity instanceof AbstractPlant)) {
                        new MovingService((AbstractAnimal) entity).move((AbstractAnimal) entity);
                    } else {
                        continue;
                    }
                }
//                System.out.println("успешно сделали ход");
            }
        }
    }

    //    одно из возможных условий выхода из симуляции
    public boolean areAllPredatorsDead() {
        for (int i = 0; i < islandField.getNumRows(); i++) {
            for (int j = 0; j < islandField.getNumColumns(); j++) {
                List<IslandEntity> entities = islandField.getGameField()[i][j];
                for (IslandEntity entity : entities) {
                    if (entity instanceof Predators) {
                        // Если найден живой хищник, вернуть false
                        return false;
                    }
                }
            }
        }
        // Вернуть true, если все хищники мертвы
        return true;
    }
}