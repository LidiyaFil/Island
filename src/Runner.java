package src;

import src.Actions.Servicies.MovingService;
import src.Actions.Servicies.NutritionService;
import src.Actions.Servicies.ReproductionService;
import src.Island.IslandField;
import src.IslandLivingObject.IslantEntityFactory;
import src.Threads.StatisticThread;
import src.Threads.GameSimulationThread;
import src.Threads.PlantGenerationThread;

public class Runner {

    public static void main(String[] args) {

        //todo симуляция продолжает работать после того, как все умерли
        Thread thread = new GameSimulationThread(
                new NutritionService(),
                new ReproductionService(new IslantEntityFactory()),
                new MovingService());
        thread.start();

        PlantGenerationThread plantThread = new PlantGenerationThread();
        plantThread.start();

        StatisticThread statisticThread = new StatisticThread();
        statisticThread.start();
    }
}
