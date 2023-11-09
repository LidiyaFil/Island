package src;

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
            for (List[] lists : IslandField.getGameField()) {
                System.out.println("зашли в цикл");
                for (List list : lists) {
                    System.out.println("зашли во 2 цикл");
                    // cначала все питаются
                    System.out.println("пытаемся поесть");
                    for (int i = 0; i < list.size(); i++) {
                        Object o = list.get(i);
                        IslandEntity entity = (IslandEntity) o;
                        nutrition = new NutritionService(entity);
                        nutrition.eat(list);
                    }
                    System.out.println("успешно поели");
                    Coordinator coordinator = new Coordinator();
                    coordinator.start();
                    // затем пробуют размножиться
                    System.out.println("пытаемся размножиться");
                    list.stream().forEach(rep -> reproduction.reproduce(list));
                    // затем пробуют переместиться
                    System.out.println("пытаемся пойти");
                    list.stream().forEach(move -> moving.move());
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
