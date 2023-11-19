package src;

import src.Actions.MovingService;
import src.Actions.NutritionService;
import src.Actions.ReproductionService;
import src.IslandLivingObject.IslandEntityFactory;
import src.Threads.StatisticThread;
import src.Threads.GameSimulationThread;
import src.Threads.PlantGenerationThread;

public class Runner {

    public static void main(String[] args) {
        //todo симуляция продолжает работать после того, как все умерли

        // ну да, потому что ты удалил условие остановки runnera - когда все животные умерли
        Thread thread = new GameSimulationThread(
                new NutritionService(),
                new ReproductionService(new IslandEntityFactory()),
                new MovingService());
        thread.start();

        PlantGenerationThread plantThread = new PlantGenerationThread();
        plantThread.start();

        StatisticThread statisticThread = new StatisticThread();
        statisticThread.start();
    }
}
