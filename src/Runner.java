package src;

import src.Island.IslandField;
import src.Threads.StatisticThread;
import src.Threads.GameSimulationThread;
import src.Threads.PlantGenerationThread;

public class Runner {

    public static void main(String[] args) {
        Thread thread = new GameSimulationThread();
        thread.start();

        PlantGenerationThread plantThread = new PlantGenerationThread();
        plantThread.start();

        StatisticThread statisticThread = new StatisticThread();
        statisticThread.start();
    }
}
