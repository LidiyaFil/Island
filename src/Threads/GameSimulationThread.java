package src.Threads;

import src.Actions.MovingService;
import src.Actions.NutritionService;
import src.Actions.ReproductionService;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.Animals.Predators.Predators;
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
        // подсчет количества процессоров, для задания необходимого пула потоков
        this.coreCount = Runtime.getRuntime().availableProcessors();
    }

    // TODO надо сделать таски, которые будут делаться посписочно, взависимоти от того, сколько процессоров в системе
    @Override
    public void run() {

        while (running) {
            for (List[] lists : islandField.getGameField()) {
                for (List list : lists) {
                    // cначала все питаются
                    System.out.println("пытаемся поесть");
                    list.forEach(entity -> new NutritionService((AbstractAnimal)entity.eat(list)));
                    System.out.println("успешно поели");

                    Coordinator coordinator = new Coordinator();
                    // coordinator.start();

                    System.out.println("пытаемся размножиться");
                    list.forEach(entity -> new ReproductionService(AbstractAnimal.reproduce(list)));
                    System.out.println("успешно размножились");
                    // coordinator.start();

                    System.out.println("пытаемся пойти");
                    list.forEach(entity -> new MovingService(AbstractAnimal.move((AbstractAnimal) entity)));
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
        return areAllPredatorsDead();
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
