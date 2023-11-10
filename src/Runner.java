package src;

import src.Island.IslandField;
import src.Threads.Coordinator;
import src.Threads.GameSimulationThread;
import src.Threads.PlantGenerationThread;

public class Runner {

    public static void main(String[] args) {
        IslandField islandField = IslandField.getInstance();
        Coordinator coordinator = new Coordinator();
        coordinator.start();
        Thread thread = new GameSimulationThread();
        thread.start();

      /*  PlantGenerationThread plantThread = new PlantGenerationThread(islandField);
        plantThread.start();*/
    }
}
