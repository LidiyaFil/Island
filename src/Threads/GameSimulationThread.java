package src.Threads;

import src.Actions.MovingService;
import src.Actions.NutritionService;
import src.Actions.ReproductionService;
import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class GameSimulationThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    NutritionService nutrition;
    ReproductionService reproduction;
    MovingService moving;
    private boolean running;

    private int coreCount;

    public GameSimulationThread() {
        this.running = true;
        this.coreCount = Runtime.getRuntime().availableProcessors();
    }
//TODO надо сделать такски, которые будут делаться посписочно, взависимоти от того, сколько процессоров в системе
    @Override
    public void run() {
        while (running) {
            for (List[] lists : islandField.getGameField()) {
                for (List list : lists) {
                    // cначала все питаются
                    System.out.println("пытаемся поесть");
                    list.stream().forEach(entity -> new NutritionService((IslandEntity) entity).eat(list));
                    System.out.println("успешно поели");
                    //запуск статистики для сравнения
                    Coordinator coordinator = new Coordinator();
//                    coordinator.start();
                    // затем пробуют размножиться
                    System.out.println("пытаемся размножиться");
                    list.stream().forEach(entity -> new ReproductionService((IslandEntity) entity).reproduce(list));
                    System.out.println("успешно размножились");
//                    coordinator.start();
                    // затем пробуют переместиться
                    System.out.println("пытаемся пойти");
                    list.stream().forEach(entity -> new MovingService((IslandEntity) entity).move((IslandEntity) entity));
                    System.out.println("успешно сделали ход");
                }
            }

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
        return islandField.areAllPredatorsDead();
    }
}
